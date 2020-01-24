<?php

namespace FlatshareBundle\Controller;

use Symfony\Bundle\FrameworkBundle\Controller\Controller;

class DefaultController extends Controller
{
    public function indexAction()
    {
        return $this->render('FlatshareBundle:Default:index.html.twig');
    }
}
