<?php

namespace TutoratBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('TutoratBundle:Default:index.html.twig');
    }
}
