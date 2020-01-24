<?php

namespace LostObjectBundle\Controller;

use AppBundle\Entity\Files;
use LostObjectBundle\Entity\Favoris;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\Config\Definition\Exception\Exception;
use Symfony\Component\HttpFoundation\Request;
use LostObjectBundle\Entity\LostObject;
use Symfony\Component\Validator\Constraints\DateTime;
use UserBundle\Entity\User;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('LostObjectBundle:Default:index.html.twig');
    }

    public function annonceAction()
    {
        return $this->render('LostObjectBundle::EspritienOpAds.html.twig');
    }

    public function demandeAction()
    {
        return $this->render('LostObjectBundle::EspritienOpDmd.html.twig');
    }

    /**
     * @return bool
     */
    public function uploadAction()
    {
        return $this->json(array('error' => 'false'));
    }

    public function addRequestAction(request $request)
    {

        if ($this->container->get('security.authorization_checker')->isGranted('IS_AUTHENTICATED_FULLY')) {
            $user = $this->container->get('security.token_storage')->getToken()->getUser();
//            $username = $user->getUsername();
//            $userId = $user->getId();
//            $userEmail = $user->getEmail();


            $title = $request->get('title');
            $description = $request->get('description');
            if ($title && $description) {
                try {
                    $OP = new LostObject();
                    $OP->setTitle($title);
                    $OP->setDescription($description);
                    $OP->setType("demande");
                    $current = new \DateTime();
                    $OP->setCreatedAt($current);
                    $OP->setEnabled(0);
                    $OP->setCancled(0);
                    $OP->setCreatedBy($user);
                    $em = $this->getDoctrine()->getManager();
                    $em->persist($OP);
                    $em->flush();
                    return $this->json(array('error' => false));
                } catch (Exception $e) {
                    return $this->json(array('error' => true));
                }

            } else {
                return $this->json(array('error' => true));
            }

        }
        return $this->json(array('error' => true));

    }

    public function addAdsAction(request $request)
    {

        $title = $request->get('title');
        $description = $request->get('description');
        if ($title && $description) {
            try {
                $OP = new LostObject();
                $OP->setTitle($title);
                $OP->setDescription($description);
                $OP->setType("annonce");
                $current = new \DateTime();
                $OP->setCreatedAt($current);
                $OP->setEnabled(0);
                $OP->setCancled(0);
                $em = $this->getDoctrine()->getManager();
                $em->persist($OP);
                $em->flush();
                return $this->json(array('error' => false, 'adsID' => $OP->getId()));
            } catch (Exception $e) {
                return $this->json(array('error' => true));
            }

        } else {
            return $this->json(array('error' => true));
        }
    }

    public function savePictureAction(request $request)
    {
        $media = $request->files->get('pictures');
        $adsID = $request->request->get('adsID');
        try {
            if ($media) {
                $em = $this->getDoctrine()->getManager();
                $files = new Files();
                $files->setAdsID($adsID);
                $files->setFile($media);
                $files->setPath();
                $files->setCreatedAt(new \DateTime());
                $files->setName($media->getClientOriginalName());
                $files->upload();
                $em->persist($files);
                $em->flush();
                return $this->json(array('error' => false));
            }
        } catch (Exception $e) {
            return $this->json(array('error' => true));
        }
        return $this->json(array('error' => true));
    }

    public function listAction(request $request)
    {
        if ($this->container->get('security.authorization_checker')->isGranted('IS_AUTHENTICATED_FULLY')) {
            $user = $this->container->get('security.token_storage')->getToken()->getUser();
            $em = $this->getDoctrine()->getManager();
            $OP = $em->getRepository("LostObjectBundle:LostObject")
                ->findBy(
                    array('cancled' => false, 'enabled' => true),
                    array('createdAt' => 'DESC')
                );

            $_op = array();

            for ($i = 0; $i < sizeof($OP); $i++) {
                $favoris = $em->getRepository("LostObjectBundle:Favoris")->findBy(array('user' => $user, 'lostobject' => $OP[$i]));
                $isFavoris = false;
                if ($favoris)
                    $isFavoris = true;

                $data = array(
                    'id' => $OP[$i]->getId(),
                    'title' => $OP[$i]->getTitle(),
                    'description' => $OP[$i]->getDescription(),
                    'CreatedAt' => $OP[$i]->getCreatedAt(),
                    'type' => $OP[$i]->getType(),
                    'user' => $OP[$i]->getCreatedBy()->getUsername(),
                    'favoris' => $isFavoris,
                );
                $_op[$i] = $data;
            }

            return $this->json(array('error' => false, 'op' => $_op));
        }
        return $this->json(array('error' => true));

    }


    public function listannonceAction(request $request)
    {
        if ($this->container->get('security.authorization_checker')->isGranted('IS_AUTHENTICATED_FULLY')) {
            $user = $this->container->get('security.token_storage')->getToken()->getUser();
            $em = $this->getDoctrine()->getManager();
            $OP = $em->getRepository("LostObjectBundle:LostObject")
                ->findBy(
                    array('cancled' => false, 'enabled' => true, 'type' => 'annonce'),
                    array('createdAt' => 'DESC')
                );
            $_op = array();

            for ($i = 0; $i < sizeof($OP); $i++) {
                $favoris = $em->getRepository("LostObjectBundle:Favoris")->findBy(array('user' => $user, 'lostobject' => $OP[$i]));
                $isFavoris = false;
                if ($favoris)
                    $isFavoris = true;

                $data = array(
                    'id' => $OP[$i]->getId(),
                    'title' => $OP[$i]->getTitle(),
                    'description' => $OP[$i]->getDescription(),
                    'CreatedAt' => $OP[$i]->getCreatedAt(),
                    'type' => $OP[$i]->getType(),
                    'user' => $OP[$i]->getCreatedBy()->getUsername(),
                    'favoris' => $isFavoris,
                );
                $_op[$i] = $data;
            }

            return $this->json(array('error' => false, 'op' => $_op));
        }
        return $this->json(array('error' => true));
    }

    public
    function cancelannonceAction(request $request)
    {
        try {
            $id = $request->get('id');
            $em = $this->getDoctrine()->getManager();
            $OP = $em->getRepository("LostObjectBundle:LostObject")->find($id);
            $em->remove($OP);
            $em->flush();
//            $OP->setCancled(1);
//            $em->persist($OP);
//            $em->flush();
            return $this->json(array('error' => false));
        } catch (Exception $ex) {
            return $this->json(array('error' => true));
        }


    }

    public function getadsByIdAction(request $request)
    {
        try {
            $id = $request->get('id');
            $em = $this->getDoctrine()->getManager();
            $OP = $em->getRepository("LostObjectBundle:LostObject")->find($id);
            $_op = array();


            $data = array(
                'id' => $OP->getId(),
                'title' => $OP->getTitle(),
                'description' => $OP->getDescription(),
                'CreatedAt' => $OP->getCreatedAt(),
                'type' => $OP->getType()
            );

//            $OP->setCancled(1);
//            $em->persist($OP);
//            $em->flush();
            return $this->json(array('error' => false, "op" => $data));
        } catch (Exception $ex) {
            return $this->json(array('error' => true));
        }


    }


    public function updateadsByIdAction(request $request)
    {
        try {
            $id = $request->get('id');
            $title = $request->get('title');
            $description = $request->get('description');
            $em = $this->getDoctrine()->getManager();
            $OP = $em->getRepository("LostObjectBundle:LostObject")->find($id);
            $OP->setTitle($title);
            $OP->setDescription($description);
            $OP->setEnabled(0);
            $OP->setCancled(0);
            $OP->setCreatedAt(new \DateTime());

            $em->persist($OP);
            $em->flush();
            return $this->json(array('error' => false));
        } catch (Exception $ex) {
            return $this->json(array('error' => true));
        }


    }

    public
    function listdemandeAction(request $request)
    {
        if ($this->container->get('security.authorization_checker')->isGranted('IS_AUTHENTICATED_FULLY')) {
            $user = $this->container->get('security.token_storage')->getToken()->getUser();
//            $username = $user->getUsername();
//            $userId = $user->getId();
//            $userEmail = $user->getEmail();

            $em = $this->getDoctrine()->getManager();
            $OP = $em->getRepository("LostObjectBundle:LostObject")
                ->findBy(
                    array('cancled' => false, 'enabled' => true, 'type' => 'demande',),
                    array('createdAt' => 'DESC')
                );
            $_op = array();

            for ($i = 0; $i < sizeof($OP); $i++) {
                $favoris = $em->getRepository("LostObjectBundle:Favoris")->findBy(array('user' => $user, 'lostobject' => $OP[$i]));
                $isFavoris = false;
                if ($favoris)
                    $isFavoris = true;

                $data = array(
                    'id' => $OP[$i]->getId(),
                    'title' => $OP[$i]->getTitle(),
                    'description' => $OP[$i]->getDescription(),
                    'CreatedAt' => $OP[$i]->getCreatedAt(),
                    'type' => $OP[$i]->getType(),
                    'user' => $OP[$i]->getCreatedBy()->getUsername(),
                    'favoris' => $isFavoris,
                );
                $_op[$i] = $data;
            }

            return $this->json(array('error' => false, 'op' => $_op));
        }
        return $this->json(array('error' => true));

    }

    public
    function mydemandeAction(request $request)
    {
        if ($this->container->get('security.authorization_checker')->isGranted('IS_AUTHENTICATED_FULLY')) {
            $user = $this->container->get('security.token_storage')->getToken()->getUser();
//            $username = $user->getUsername();
//            $userId = $user->getId();
//            $userEmail = $user->getEmail();

            $em = $this->getDoctrine()->getManager();
            $OP = $em->getRepository("LostObjectBundle:LostObject")
                ->findBy(
                    array('type' => 'demande', 'createdBy' => $user),
                    array('createdAt' => 'DESC')
                );
            $_op = array();

            for ($i = 0; $i < sizeof($OP); $i++) {
                $data = array(
                    'id' => $OP[$i]->getId(),
                    'title' => $OP[$i]->getTitle(),
                    'description' => $OP[$i]->getDescription(),
                    'CreatedAt' => $OP[$i]->getCreatedAt(),
                    'type' => $OP[$i]->getType()
                );
                $_op[$i] = $data;
            }

            return $this->json(array('error' => false, 'op' => $_op));
        }
        return $this->json(array('error' => true));

    }


    public function add_favorisAction(request $request)
    {
        if ($this->container->get('security.authorization_checker')->isGranted('IS_AUTHENTICATED_FULLY')) {
            $user = $this->container->get('security.token_storage')->getToken()->getUser();
            try {
                $id = $request->get('id');

                $em = $this->getDoctrine()->getManager();
                $op = $em->getRepository("LostObjectBundle:LostObject")->find($id);

                if ($op) {

                    $favoris = new Favoris();
                    $favoris->setLostObject($op);
                    $favoris->setUser($user);

                    $em->persist($favoris);
                    $em->flush();
                    return $this->json(array('error' => false));


                } else {
                    return $this->json(array('error' => true));
                }
            } catch (Exception $ex) {
                return $this->json(array('error' => true));
            }
        }
        return $this->json(array('error' => true));

    }


    public function set_favorisAction(request $request)
    {
        if ($this->container->get('security.authorization_checker')->isGranted('IS_AUTHENTICATED_FULLY')) {
            $user = $this->container->get('security.token_storage')->getToken()->getUser();
            try {
                $id = $request->get('id');

                $em = $this->getDoctrine()->getManager();

                $op = $em->getRepository("LostObjectBundle:LostObject")->find($id);
                $favoris = $em->getRepository("LostObjectBundle:Favoris")->findOneBy(array('lostobject' => $op, 'user' => $user));
                if ($favoris) {
                    $em->remove($favoris);
                    $em->flush();
                    return $this->json(array('error' => false, 'favoris' => false));
                } else {
                    $favoris = new Favoris();
                    $favoris->setLostObject($op);
                    $favoris->setUser($user);

                    $em->persist($favoris);
                    $em->flush();
                    return $this->json(array('error' => false, 'favoris' => true));
                }
                return $this->json(array('error' => true));


            } catch (Exception $ex) {
                return $this->json(array('error' => true));
            }
        }
        return $this->json(array('error' => true));

    }


    public
    function getdemandeattenteAction(request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $OP = $em->getRepository("LostObjectBundle:LostObject")
            ->findBy(
                array('cancled' => false, 'enabled' => false, 'type' => 'demande'),
                array('createdAt' => 'DESC')
            );
        $_op = array();

        for ($i = 0; $i < sizeof($OP); $i++) {
            $data = array(
                'id' => $OP[$i]->getId(),
                'title' => $OP[$i]->getTitle(),
                'description' => $OP[$i]->getDescription(),
                'CreatedAt' => $OP[$i]->getCreatedAt(),
                'type' => $OP[$i]->getType()
            );
            $_op[$i] = $data;
        }

        return $this->json(array('error' => false, 'op' => $_op));

    }

    public
    function myadsAction(request $request)
    {
        $em = $this->getDoctrine()->getManager();
        $OP = $em->getRepository("LostObjectBundle:LostObject")
            ->findBy(
                array('type' => 'annonce'),
                array('createdAt' => 'DESC')
            );
        $_op = array();

        for ($i = 0; $i < sizeof($OP); $i++) {
            $data = array(
                'id' => $OP[$i]->getId(),
                'title' => $OP[$i]->getTitle(),
                'description' => $OP[$i]->getDescription(),
                'CreatedAt' => $OP[$i]->getCreatedAt(),
                'type' => $OP[$i]->getType()
            );
            $_op[$i] = $data;
        }
        if ($this->container->get('security.authorization_checker')->isGranted('IS_AUTHENTICATED_FULLY')) {
            $user = $this->container->get('security.token_storage')->getToken()->getUser();
            $username = $user->getUsername();
            $userId = $user->getId();
            $userEmail = $user->getEmail();
        }
        return $this->json(array('error' => false, 'op' => $_op, 'user' => $userEmail));

    }

    public
    function espritienOpAction()
    {
        //return $this->render('IndexBundle:public:LandingPage.html.twig');
        return $this->render('LostObjectBundle::EspritienOp.html.twig');
    }

    public function favorispageAction()
    {
        $user = $this->container->get('security.token_storage')->getToken()->getUser();
        $em = $this->getDoctrine()->getManager();
        $favoris = $em->getRepository("LostObjectBundle:Favoris")->findAll(array('user' => $user));


        return $this->render('LostObjectBundle::EspritienOpFav.html.twig', array('favoris' => $favoris));
    }

    public function cancelFavorisAction(Request $request, $idfavoris)
    {
        $user = $this->container->get('security.token_storage')->getToken()->getUser();
        $em = $this->getDoctrine()->getManager();
        $favoris = $em->getRepository("LostObjectBundle:Favoris")->find($idfavoris);
        $em->remove($favoris);
        $em->flush();
        return $this->redirectToRoute('favorisOP_page');
    }

    public function searchAjaxFavAction(Request $request){
        $user = $this->container->get('security.token_storage')->getToken()->getUser();
        $key = $request->get('key');
        $em = $this->getDoctrine()->getManager();
        $result = $em->getRepository('LostObjectBundle:Favoris')->findBy(array('user'=>$user));
        $_result = array();
        $cpt = 0;
        for ($i = 0; $i < sizeof($result); $i++) {
            $op = $em->getRepository('LostObjectBundle:LostObject')->findBy(array('title'=>$key));
            if($op){
                for ($j = 0; $j < sizeof($op); $j++) {
                    $data = array(
                        'id' => $op[$j]->getId(),
                        'title' => $op[$j]->getTitle(),
                        'description' => $op[$j]->getDescription(),
                        'CreatedAt' => $op[$j]->getCreatedAt(),
                        'type' => $op[$j]->getType()
                    );
                    $_result[$cpt] = $data;
                    $cpt++;
                }
            }

        }

        return $this->json(array('error'=>false, 'result'=>$_result));
    }
}
