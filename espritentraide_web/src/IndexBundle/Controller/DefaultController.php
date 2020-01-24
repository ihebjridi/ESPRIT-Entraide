<?php

namespace IndexBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {

        //User is logged in
        if (($this->container->get('security.authorization_checker')->isGranted('ROLE_USER')) &&
        ($this->container->get('security.authorization_checker')->isGranted('ROLE_ADMIN') == false))
        {
            return $this->render('IndexBundle:public:EspritienHomePage.html.twig');
        } else if ($this->container->get('security.authorization_checker')->isGranted('ROLE_ADMIN')) {
            return $this->render('AdminBundle::AdminDashboard.html.twig');
        } else if ($this->container->get('security.authorization_checker')->isGranted('IS_AUTHENTICATED_ANONYMOUSLY')) {
            return $this->render('IndexBundle:public:home.html.twig');
        }

    }

    public function settingsAction()
    {
        //return $this->render('IndexBundle:public:LandingPage.html.twig');
        return $this->render('IndexBundle:public:EspritienSettings.html.twig');
    }

    public function espritienColAction()
    {
        //return $this->render('IndexBundle:public:LandingPage.html.twig');
        return $this->render('IndexBundle:public:EspritienCol.html.twig');
    }

    public function espritienCovAction()
    {
        //return $this->render('IndexBundle:public:LandingPage.html.twig');
        return $this->render('IndexBundle:public:EspritienCov.html.twig');
    }

    public function espritienTutoAction()
    {
        //return $this->render('IndexBundle:public:LandingPage.html.twig');
        return $this->render('IndexBundle:public:EspritienTuto.html.twig');
    }

    public function espritienEventAction()
    {
        //return $this->render('IndexBundle:public:LandingPage.html.twig');
        return $this->render('IndexBundle:public:EspritienEvent.html.twig');
    }

}
