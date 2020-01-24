<?php
namespace TutoratBundle\Service;

use TutoratBundle\Entity\Answer;
use FOS\UserBundle\Model\UserInterface;

class VoteChecker 
{

    public function check(Answer $answer, UserInterface $user)
    {
        return $answer->getUser() != $user;
    }

}