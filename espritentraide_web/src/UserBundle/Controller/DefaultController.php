<?php

namespace UserBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('UserBundle:Default:index.html.twig');
    }

    protected function renderLogin(array $data)
    {
        return $this->render('IndexBundle:public:LandingPage.html.twig', $data);
    }
}
