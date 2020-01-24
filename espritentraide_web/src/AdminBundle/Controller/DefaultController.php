<?php

namespace AdminBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;

class DefaultController extends Controller
{
    public function adminDashAction()
    {
        //return $this->render('IndexBundle:public:LandingPage.html.twig');
        return $this->render('AdminBundle::AdminDashboard.html.twig');
    }
    public function adminCovAction()
    {
        //return $this->render('IndexBundle:public:LandingPage.html.twig');
        return $this->render('AdminBundle::AdminCov.html.twig');
    }
    public function adminOpAction()
    {
        //return $this->render('IndexBundle:public:LandingPage.html.twig');
        return $this->render('AdminBundle::AdminOP.html.twig');
    }
    public function adminEventAction()
    {
        //return $this->render('IndexBundle:public:LandingPage.html.twig');
        return $this->render('AdminBundle::AdminEvent.html.twig');
    }
    public function adminTutoAction()
    {
        //return $this->render('IndexBundle:public:LandingPage.html.twig');
        return $this->render('AdminBundle::AdminTuto.html.twig');
    }

    public function getallAction()
    {
        $em = $this->getDoctrine()->getManager();
        $OP = $em->getRepository("LostObjectBundle:LostObject")
            ->findBy(
                array('cancled' => false, 'enabled' => false),
                array('createdAt' => 'DESC')
            );
        $_op = array();

        for ($i = 0; $i < sizeof($OP); $i++) {
            $data = array(
                'id' => $OP[$i]->getId(),
                'title' => $OP[$i]->getTitle(),
                'description' => $OP[$i]->getDescription(),
                'CreatedAt' => $OP[$i]->getCreatedAt(),
                'type' => $OP[$i]->getType(),
                'typeAd' => 'objet-perdu'
            );
            $_op[$i] = $data;
        }

        return $this->json(array('error' => false, 'op' => $_op));

    }

    public function getLostFoundAction()
    {
        $em = $this->getDoctrine()->getManager();
        $OP = $em->getRepository("LostObjectBundle:LostObject")
            ->findBy(
                array(),
                array('createdAt' => 'DESC')
            );
        $_op = array();

        for ($i = 0; $i < sizeof($OP); $i++) {
            $data = array(
                'id' => $OP[$i]->getId(),
                'title' => $OP[$i]->getTitle(),
                'description' => $OP[$i]->getDescription(),
                'CreatedAt' => $OP[$i]->getCreatedAt(),
                'type' => $OP[$i]->getType(),
                'enabled' => $OP[$i]->getEnabled(),
                'cancled' => $OP[$i]->getCancled(),
            );
            $_op[$i] = $data;
        }

        return $this->json(array('error' => false, 'op' => $_op));

    }

    public function getEventAction()
    {
        $em = $this->getDoctrine()->getManager();
        $event = $em->getRepository("EvenementBundle:Event")
            ->findBy(
                array(),
                array('createdAt' => 'DESC')
            );
        $_event = array();

        for ($i = 0; $i < sizeof($event); $i++) {
            $data = array(
                'id' => $event[$i]->getId(),
                'title' => $event[$i]->getTitle(),
                'description' => $event[$i]->getDescritpion(),
                'date' => $event[$i]->getDate(),
                'lieu' => $event[$i]->getLieu(),
                'CreatedAt' => $event[$i]->getCreatedAt(),
                'enabled' => $event[$i]->getEnabled(),
                'cancled' => $event[$i]->getCancled(),
            );
            $_event[$i] = $data;
        }

        return $this->json(array('error' => false, 'event' => $_event));

    }


    public function LostFoundActionAttAction()
    {
        $em = $this->getDoctrine()->getManager();
        $OP = $em->getRepository("LostObjectBundle:LostObject")
            ->findBy(
                array('cancled' => false, 'enabled' => false),
                array('createdAt' => 'DESC')
            );
        $_op = array();

        for ($i = 0; $i < sizeof($OP); $i++) {
            $data = array(
                'id' => $OP[$i]->getId(),
                'title' => $OP[$i]->getTitle(),
                'description' => $OP[$i]->getDescription(),
                'CreatedAt' => $OP[$i]->getCreatedAt(),
                'type' => $OP[$i]->getType(),
                'enabled' => $OP[$i]->getEnabled(),
                'cancled' => $OP[$i]->getCancled(),
            );
            $_op[$i] = $data;
        }

        return $this->json(array('error' => false, 'op' => $_op));

    }

    public function getLostFoundActionPubAction()
    {
        $em = $this->getDoctrine()->getManager();
        $OP = $em->getRepository("LostObjectBundle:LostObject")
            ->findBy(
                array('cancled' => false, 'enabled' => true),
                array('createdAt' => 'DESC')
            );
        $_op = array();

        for ($i = 0; $i < sizeof($OP); $i++) {
            $data = array(
                'id' => $OP[$i]->getId(),
                'title' => $OP[$i]->getTitle(),
                'description' => $OP[$i]->getDescription(),
                'CreatedAt' => $OP[$i]->getCreatedAt(),
                'type' => $OP[$i]->getType(),
                'enabled' => $OP[$i]->getEnabled(),
                'cancled' => $OP[$i]->getCancled(),
            );
            $_op[$i] = $data;
        }

        return $this->json(array('error' => false, 'op' => $_op));

    }

    public function getLostFoundActionAnnuleAction()
    {
        $em = $this->getDoctrine()->getManager();
        $OP = $em->getRepository("LostObjectBundle:LostObject")
            ->findBy(
                array('cancled' => true, 'enabled' => false),
                array('createdAt' => 'DESC')
            );
        $_op = array();

        for ($i = 0; $i < sizeof($OP); $i++) {
            $data = array(
                'id' => $OP[$i]->getId(),
                'title' => $OP[$i]->getTitle(),
                'description' => $OP[$i]->getDescription(),
                'CreatedAt' => $OP[$i]->getCreatedAt(),
                'type' => $OP[$i]->getType(),
                'enabled' => $OP[$i]->getEnabled(),
                'cancled' => $OP[$i]->getCancled(),
            );
            $_op[$i] = $data;
        }

        return $this->json(array('error' => false, 'op' => $_op));

    }


    public function AcceptAdsOpAction(request $request)
    {
        try {
            $id = $request->get('id');
            $em = $this->getDoctrine()->getManager();
            $OP = $em->getRepository("LostObjectBundle:LostObject")->find($id);

            $OP->setEnabled(1);
            $OP->setCancled(0);
            $OP->setPublishedAt(new \DateTime());

            $em->persist($OP);
            $em->flush();
            return $this->json(array('error' => false ));
        } catch (Exception $ex) {
            return $this->json(array('error' => true));
        }


    }

    public function CancelAdsOpAction(request $request)
    {
        try {
            $id = $request->get('id');
            $em = $this->getDoctrine()->getManager();
            $OP = $em->getRepository("LostObjectBundle:LostObject")->find($id);

            $OP->setEnabled(0);
            $OP->setCancled(1);
            $OP->setCanceledAt(new \DateTime());

            $em->persist($OP);
            $em->flush();
            return $this->json(array('error' => false ));
        } catch (Exception $ex) {
            return $this->json(array('error' => true));
        }


    }


    public function CancelEventAction(request $request)
    {
        try {
            $id = $request->get('id');
            $em = $this->getDoctrine()->getManager();
            $OP = $em->getRepository("EvenementBundle:Event")->find($id);

            $OP->setEnabled(0);
            $OP->setCancled(1);
            $OP->setCanceledAt(new \DateTime());

            $em->persist($OP);
            $em->flush();
            return $this->json(array('error' => false ));
        } catch (Exception $ex) {
            return $this->json(array('error' => true));
        }


    }

    public function AcceptEventAction(request $request)
    {
        try {
            $id = $request->get('id');
            $em = $this->getDoctrine()->getManager();
            $OP = $em->getRepository("EvenementBundle:Event")->find($id);

            $OP->setEnabled(1);
            $OP->setCancled(0);
            $OP->setCanceledAt(new \DateTime());

            $em->persist($OP);
            $em->flush();
            return $this->json(array('error' => false ));
        } catch (Exception $ex) {
            return $this->json(array('error' => true));
        }


    }

}
