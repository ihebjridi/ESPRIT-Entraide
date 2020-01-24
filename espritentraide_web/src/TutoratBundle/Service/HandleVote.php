<?php
namespace TutoratBundle\Service;

use TutoratBundle\Entity\Answer;
use Doctrine\Common\Persistence\ObjectManager;

class HandleVote 
{

    private $em;

    public function __construct(ObjectManager $em, \Symfony\Bundle\FrameworkBundle\Routing\Router $router)
    {
        $this->em = $em;
    }

    public function handle(Answer $answer, $vote) {
        $current_vote = $answer->getVote();
        $new_vote = $vote == "▲" ? ++$current_vote : --$current_vote ;
        $answer->setVote($new_vote);
        
        $this->em->persist($answer);
        $this->em->flush();
    }

}