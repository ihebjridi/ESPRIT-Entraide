<?php

namespace EvenementBundle\Controller;

use EvenementBundle\Entity\Event;
use EvenementBundle\Entity\Event_user;
use EvenementBundle\Entity\Favoris;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\Config\Definition\Exception\Exception;
use Symfony\Component\HttpFoundation\Request;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('EvenementBundle::EspritienEvent.html.twig');
    }

    public function myEventAction()
    {
        return $this->render('EvenementBundle::EspritienMyEvent.html.twig');
    }

    public function myAdsAction()
    {
        return $this->render('EvenementBundle::EspritienMyAds.html.twig');
    }


    public function getMyEventAction(request $request)
    {
        if ($this->container->get('security.authorization_checker')->isGranted('IS_AUTHENTICATED_FULLY')) {
            $user = $this->container->get('security.token_storage')->getToken()->getUser();
            $em = $this->getDoctrine()->getManager();
            $event_user = $em->getRepository("EvenementBundle:Event_user")->findBy(
                array('user' => $user)
            );

            $_event = array();
            $compteur = 0;
            for ($j = 0; $j < sizeof($event_user); $j++) {
                $event = $em->getRepository("EvenementBundle:Event")->findBy(array('id'=>$event_user[$j]->getEvent()->getId()));
                for ($i = 0; $i < sizeof($event); $i++) {
                    $data = array(
                        'id' => $event[$i]->getId(),
                        'title' => $event[$i]->getTitle(),
                        'description' => $event[$i]->getDescritpion(),
                        'CreatedAt' => $event[$i]->getCreatedAt(),
                        'lieu' => $event[$i]->getLieu(),
                        'date' => $event[$i]->getDate(),
                    );
                    $_event[$compteur] = $data;
                    $compteur++;
                }
            }

            return $this->json(array('error' => false, 'event' => $_event));
        }
        return $this->json(array('error' => true));
    }

    public function getALLEventAction(request $request)
    {
        if ($this->container->get('security.authorization_checker')->isGranted('IS_AUTHENTICATED_FULLY')) {
            $user = $this->container->get('security.token_storage')->getToken()->getUser();
            $em = $this->getDoctrine()->getManager();
            $event = $em->getRepository("EvenementBundle:Event")
                ->findBy(
                    array('cancled' => false, 'enabled' => true),
                    array('createdAt' => 'DESC')
                );


            $_event = array();

            for ($i = 0; $i < sizeof($event); $i++) {
                $event_user = $em->getRepository("EvenementBundle:Event_user")->findBy(
                    array('user' => $user,'event'=>$event[$i])
                );

                if (sizeof($event_user) != 0) {
                    for ($j = 0; $j < sizeof($event_user); $j++) {
                        $data2 = array('id_event' => $event_user[$j]->getEvent()->getId());
                    }
                    $data = array(
                        'id' => $event[$i]->getId(),
                        'title' => $event[$i]->getTitle(),
                        'description' => $event[$i]->getDescritpion(),
                        'CreatedAt' => $event[$i]->getCreatedAt(),
                        'lieu' => $event[$i]->getLieu(),
                        'date' => $event[$i]->getDate(),
                        'events' => $data2,

                    );
                    $_event[$i] = $data;
                } else {
                    $data = array(
                        'id' => $event[$i]->getId(),
                        'title' => $event[$i]->getTitle(),
                        'description' => $event[$i]->getDescritpion(),
                        'CreatedAt' => $event[$i]->getCreatedAt(),
                        'lieu' => $event[$i]->getLieu(),
                        'date' => $event[$i]->getDate(),


                    );
                    $_event[$i] = $data;
                }

            }
                return $this->json(array('error' => false, 'event' => $_event));

        }
        return $this->json(array('error' => true));
    }


    public function getMyAdsAction(request $request)
    {
        if ($this->container->get('security.authorization_checker')->isGranted('IS_AUTHENTICATED_FULLY')) {
            $user = $this->container->get('security.token_storage')->getToken()->getUser();
            $em = $this->getDoctrine()->getManager();
            $event = $em->getRepository("EvenementBundle:Event")
                ->findBy(
                    array('createdBy' => $user),
                    array('createdAt' => 'DESC')
                );
            $_event = array();

            for ($i = 0; $i < sizeof($event); $i++) {
                $data = array(
                    'id' => $event[$i]->getId(),
                    'title' => $event[$i]->getTitle(),
                    'description' => $event[$i]->getDescritpion(),
                    'CreatedAt' => $event[$i]->getCreatedAt(),
                    'lieu' => $event[$i]->getLieu(),
                    'date' => $event[$i]->getDate(),
                );
                $_event[$i] = $data;
            }

            return $this->json(array('error' => false, 'event' => $_event));
        }
        return $this->json(array('error' => true));
    }

    public function cancelMyAdsAction(request $request)
    {
        if ($this->container->get('security.authorization_checker')->isGranted('IS_AUTHENTICATED_FULLY')) {
            $user = $this->container->get('security.token_storage')->getToken()->getUser();
            try {
                $id = $request->get('id');
                $em = $this->getDoctrine()->getManager();
                $event = $em->getRepository("EvenementBundle:Event")->find($id);
                $event_user = $em->getRepository("EvenementBundle:Event_user")->findOneBy(array('event'=>$event));
                if($event_user)
                $em->remove($event_user);

                $em->remove($event);
                $em->flush();
//            $OP->setCancled(1);
//            $em->persist($OP);
//            $em->flush();
                return $this->json(array('error' => false));
            } catch (Exception $ex) {
                return $this->json(array('error' => true));
            }


        }
        return $this->json(array('error' => true));
    }


    public function getEventByIdAction(request $request)
    {
        try {
            $id = $request->get('id');
            $em = $this->getDoctrine()->getManager();
            $event = $em->getRepository("EvenementBundle:Event")->find($id);


            $data = array(
                'id' => $event->getId(),
                'title' => $event->getTitle(),
                'description' => $event->getDescritpion(),
                'CreatedAt' => $event->getCreatedAt(),
                'lieu' => $event->getLieu(),
                'date' => $event->getDate(),
            );

            return $this->json(array('error' => false, "event" => $data));
        } catch (Exception $ex) {
            return $this->json(array('error' => true));
        }


    }


    public function updateEventadsByIdAction(request $request)
    {
        try {
            $id = $request->get('id');
            $title = $request->get('title');
            $description = $request->get('description');
            $date = $request->get('date');
            $lieu = $request->get('lieu');

            $em = $this->getDoctrine()->getManager();
            $event = $em->getRepository("EvenementBundle:Event")->find($id);
            $event->setTitle($title);
            $event->setDescritpion($description);
            $event->setLieu($lieu);
            $event->setDate(new \DateTime($date));
            $event->setEnabled(0);
            $event->setCancled(0);
            $event->setCreatedAt(new \DateTime());

            $em->persist($event);
            $em->flush();
            return $this->json(array('error' => false));
        } catch (Exception $ex) {
            return $this->json(array('error' => true));
        }


    }



    public function addEventAction(request $request)
    {
        if ($this->container->get('security.authorization_checker')->isGranted('IS_AUTHENTICATED_FULLY')) {
            $user = $this->container->get('security.token_storage')->getToken()->getUser();
            $title = $request->get('title');
            $description = $request->get('description');
            $lieu = $request->get('lieu');
            $date = $request->get('date');

            if ($title && $description && $lieu && $date) {
                try {
                    $event = new Event();
                    $event->setTitle($title);
                    $event->setDescritpion($description);
                    $event->setLieu($lieu);
                    $event->setDate(new \DateTime($date));

                    $event->setCreatedAt(new \DateTime());
                    $event->setEnabled(0);
                    $event->setCancled(0);
                    $event->setCreatedBy($user);
                    $em = $this->getDoctrine()->getManager();
                    $em->persist($event);
                    $em->flush();
                    return $this->json(array('error' => false, 'adsID' => $event->getId()));
                } catch (Exception $e) {
                    return $this->json(array('error' => true));
                }

            } else {
                return $this->json(array('error' => true));
            }
        }
        return $this->json(array('error' => true));


    }

    public function ParticperEvntAction(request $request)
    {
        if ($this->container->get('security.authorization_checker')->isGranted('IS_AUTHENTICATED_FULLY')) {
            $user = $this->container->get('security.token_storage')->getToken()->getUser();
            $id = $request->get('id');
            $em = $this->getDoctrine()->getManager();
            $event = $em->getRepository("EvenementBundle:Event")->find($id);

            if ($event) {
                try {
                    $event_user = new Event_user();
                    $event_user->setEvent($event);
                    $event_user->setUser($user);

                    $em->persist($event_user);
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
}
