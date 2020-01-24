<?php

namespace CarpoolingBundle\Controller;


use CarpoolingBundle\Form\CarpoolingType;
use CMEN\GoogleChartsBundle\GoogleCharts\Charts\PieChart;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Bundle\SecurityBundle\Tests\Functional\Bundle\AclBundle\Entity\Car;
use Symfony\Component\Config\Definition\Exception\Exception;
use Symfony\Component\HttpFoundation\Request;
use CarpoolingBundle\Entity\Carpooling;



class DefaultController extends Controller
{
    /*public function indexAction()
    {
        return $this->render('CarpoolingBundle:Default:EspritienCovoiturage.html.twig');
    }*/

    public function annonceAction()
    {
        return $this->render('CarpoolingBundle:Default:EspritienCovoiturageAnnonce.html.twig');
    }

    public function demandeAction()
    {
        return $this->render('CarpoolingBundle:Default:EspritienCovoiturageDemande.html.twig');
    }

    /**
     * @return bool
     */
    /*public function uploadAction()
    {
        return $this->json(array('error' => 'false'));
    }*/

    public function addRequestAction(Request $request)
    {
        if ($this->container->get('security.authorization_checker')->isGranted('IS_AUTHENTICATED_FULLY')) {
            $user = $this->container->get('security.token_storage')->getToken()->getUser();
            $title = $request->get('title');
            $description = $request->get('description');
            $part = $request->get('part');
            if ($title && $description) {
                $cov = new Carpooling();
                try {
                    $em = $this->getDoctrine()->getManager();


                    $cov->setTitle($title);
                    $cov->setDescription($description);
                    $cov->setType("demande");
                    $current = new \DateTime();
                    $cov->setCreatedAt($current);
                    $cov->setEnabled(0);
                    $cov->setCancled(0);
                    $cov->setCreatedBy($user);
                    $cov->setPart($part);


                    $em->persist($cov);
                    $em->flush();
                    return $this->json(array('error' => false, 'test' => $user));
                } catch (Exception $e) {
                    return $this->json(array('error' => true));
                }

            } else {
                return $this->json(array('error' => true));
            }
        }
        return $this->json(array('error' => true));

    }

    public function addAdsAction(Request $request)
    {

        if ($this->container->get('security.authorization_checker')->isGranted('IS_AUTHENTICATED_FULLY')) {
            $user = $this->container->get('security.token_storage')->getToken()->getUser();
            $title = $request->get('title');
            $description = $request->get('description');
            $part = $request->get('part');
            if ($title && $description) {
                $cov = new Carpooling();
                try {
                    $em = $this->getDoctrine()->getManager();


                    $cov->setTitle($title);
                    $cov->setDescription($description);
                    $cov->setType("annonce");
                    $current = new \DateTime();
                    $cov->setCreatedAt($current);
                    $cov->setEnabled(0);
                    $cov->setCancled(0);
                    $cov->setCreatedBy($user);
                    $cov->setPart($part);


                    $em->persist($cov);
                    $em->flush();
                    return $this->json(array('error' => false, 'test' => $user));
                } catch (Exception $e) {
                    return $this->json(array('error' => true));
                }

            } else {
                return $this->json(array('error' => true));
            }
        }
        return $this->json(array('error' => true));
    }


    public function listAction(Request $request)
    {
        try {
            $em = $this->getDoctrine()->getManager();
            $cov = $em->getRepository("CarpoolingBundle:Carpooling")
                ->findBy(
                    array('cancled' => false, 'enabled' => true),
                    array('createdAt' => 'DESC')
                );
            $_cov = array();

            for ($i = 0; $i < sizeof($cov); $i++) {
                $data = array(
                    'id' => $cov[$i]->getId(),
                    'title' => $cov[$i]->getTitle(),
                    'description' => $cov[$i]->getDescription(),
                    'CreatedAt' => $cov[$i]->getCreatedAt(),
                    'type' => $cov[$i]->getType()
                );
                $_cov[$i] = $data;
            }

            return $this->json(array('error' => false, 'cov' => $_cov));
        } catch (Exception $e) {
            return $this->json(array('error' => true));
        }


    }

    public function lesAnnoncesAction(Request $request)
    {

        if ($this->container->get('security.authorization_checker')->isGranted('IS_AUTHENTICATED_FULLY')) {
            $user = $this->container->get('security.token_storage')->getToken()->getUser();
            $em = $this->getDoctrine()->getManager();
            $cov = $em->getRepository("CarpoolingBundle:Carpooling")->findBy(
                array('type' => 'annonce','cancled' => false,'enabled' => true ,'createdBy' =>$user),
                array('createdAt' => 'DESC')
            );
            return $this->render('CarpoolingBundle:Default:EspritienCovoiturageAnnonce.html.twig', array('covs' => $cov));
        }



    }

    public function lesDemandesAction(Request $request)
    {
        if ($this->container->get('security.authorization_checker')->isGranted('IS_AUTHENTICATED_FULLY')) {
            $user = $this->container->get('security.token_storage')->getToken()->getUser();
            $em = $this->getDoctrine()->getManager();
            $cov = $em->getRepository("CarpoolingBundle:Carpooling")->findBy(
                array('type' => 'demande','cancled' => false, 'enabled' => true, 'createdBy' =>$user),
                array('createdAt' => 'DESC')
            );
            return $this->render('CarpoolingBundle:Default:EspritienCovoiturageDemande.html.twig', array('covs' => $cov));
        }

    }
    public function AdminDemandeAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $cov=$em->getRepository(Carpooling::class)->findBy(
            array('type' => 'demande', 'enabled' => false),
            array('createdAt' => 'DESC')
        );
        return $this->render('CarpoolingBundle:Default:AdminCovoiturageDemande.html.twig', array('covs'=>$cov ));

    }
    public function AdminAnnonceAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $cov=$em->getRepository(Carpooling::class)->findBy(
            array('type' => 'annonce', 'enabled' => false),
            array('createdAt' => 'DESC')
        );
        return $this->render('CarpoolingBundle:Default:AdminCovoiturageAnnonce.html.twig', array('covs'=>$cov
            // ...
        ));

    }
    public function DemandesValideAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $cov=$em->getRepository(Carpooling::class)->findBy(
            array('type' => 'demande', 'enabled' => true),
            array('createdAt' => 'DESC')
        );
        return $this->render('CarpoolingBundle:Default:DemandeCovoiturageValide.html.twig', array('covs'=>$cov
            // ...
        ));

    }
    public function AnnonceValideAction(Request $request)
    {
        $em=$this->getDoctrine()->getManager();
        $cov=$em->getRepository(Carpooling::class)->findBy(
            array('type' => 'annonce', 'enabled' => true),
            array('createdAt' => 'DESC')
        );
        return $this->render('CarpoolingBundle:Default:AnnonceCovoiturageValide.html.twig', array('covs'=>$cov
            // ...
        ));

    }


    public function espritienCovoiturageAction()
    {
        //return $this->render('IndexBundle:public:LandingPage.html.twig');
        return $this->render('CarpoolingBundle:Default:EspritienCovoiturage.html.twig');
    }
    public function espritien_modif_annonceAction(Request $request, $id)
    {
        $em=$this->getDoctrine()->getManager();
        $cov=$em->getRepository("CarpoolingBundle:Carpooling")->find($id);
        $Form=$this->createForm(CarpoolingType::class,$cov);
        $Form->handleRequest($request);
        if ($Form->isValid())
        {
            $em=$this->getDoctrine()->getManager();
            $em->persist($cov);
            $em->flush();
            return $this->redirectToRoute('Esprit_cov_my_ads');

        }
        return $this->render("CarpoolingBundle:Default:EspritienModifAnnonceCov.html.twig",
            array('form'=>$Form->createView()));

    }
    public function espritien_modif_demandeAction(Request $request, $id)
    {
        $em=$this->getDoctrine()->getManager();
        $cov=$em->getRepository("CarpoolingBundle:Carpooling")->find($id);
        $Form=$this->createForm(CarpoolingType::class,$cov);
        $Form->handleRequest($request);
        if ($Form->isValid())
        {
            $em=$this->getDoctrine()->getManager();
            $em->persist($cov);
            $em->flush();
            return $this->redirectToRoute('Esprit_cov_my_request');

        }
        return $this->render("CarpoolingBundle:Default:EspritienModifAnnonceCov.html.twig",
            array('form'=>$Form->createView()));

    }
    public function valider_demandeAction(Request $request, $id)
    {
        $em=$this->getDoctrine()->getManager();
        $cov=$em->getRepository("CarpoolingBundle:Carpooling")->find($id);
        $cov->setEnabled(1);
        $em->persist($cov);
        $em->flush();
        return $this->redirectToRoute('demande_cov_valide');


    }
    public function valider_annonceAction(Request $request, $id)
    {
        $em=$this->getDoctrine()->getManager();
        $cov=$em->getRepository("CarpoolingBundle:Carpooling")->find($id);
        $cov->setEnabled(1);
        $em->persist($cov);
        $em->flush();
        return $this->redirectToRoute('annonce_cov_valide');


    }
    public function annulerAnnonceAction(Request $request, $id)
    {
        $em=$this->getDoctrine()->getManager();
        $cov=$em->getRepository("CarpoolingBundle:Carpooling")->find($id);
        $cov->setCancled(1);
        $em->persist($cov);
        $em->flush();
        return $this->redirectToRoute('Admin_cov_the_offers');


    }

    public function annulerDemandeAction(Request $request, $id)
    {
        $em=$this->getDoctrine()->getManager();
        $cov=$em->getRepository("CarpoolingBundle:Carpooling")->find($id);
        $cov->setCancled(1);
        $em->persist($cov);
        $em->flush();
        return $this->redirectToRoute('Admin_cov_the_request');

    }

    public  function supprimer_annonceAction(Request $request, $id)
    {
        $em=$this->getDoctrine()->getManager();
        $cov=$em->getRepository("CarpoolingBundle:Carpooling")->find($id);
        $em->remove($cov);
        $em->flush();
        return $this->redirectToRoute('Esprit_cov_my_ads');

    }
    public  function supprimer_demandeAction(Request $request, $id)
    {
        $em=$this->getDoctrine()->getManager();
        $cov=$em->getRepository("CarpoolingBundle:Carpooling")->find($id);
        $em->remove($cov);
        $em->flush();
        return $this->redirectToRoute('Esprit_cov_my_request');

    }
    public function statCovAction()
    {
        $piechart= new PieChart();
        $em = $this->getDoctrine();
        $annonce_offre=$em->getRepository(Carpooling::class)->findBy(array('type'=>'annonce'));
        $annonce_demande=$em->getRepository(Carpooling::class)->findBy(array('type'=>'demande'));
        $piechart->getData()->setArrayToDataTable(
            [['publication', 'Nombre '],

                ['annonce',     count($annonce_offre)],
                ['demande',  count($annonce_demande)]
            ]
        );
        $piechart->getOptions()->setTitle('Pourcentage des annonces et des demandes');
        $piechart->getOptions()->setHeight(500);
        $piechart->getOptions()->setWidth(900);
        $piechart->getOptions()->getTitleTextStyle()->setBold(true);
        $piechart->getOptions()->getTitleTextStyle()->setColor('#009900');
        $piechart->getOptions()->getTitleTextStyle()->setItalic(true);
        $piechart->getOptions()->getTitleTextStyle()->setFontName('Arial');
        $piechart->getOptions()->getTitleTextStyle()->setFontSize(20);
        return $this->render('CarpoolingBundle:Default:stat.html.twig',array('piechart'=>$piechart));




    }
}
