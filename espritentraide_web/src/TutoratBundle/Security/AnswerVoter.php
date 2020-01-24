<?php

namespace TutoratBundle\Security;

use TutoratBundle\Entity\Answer;
use UserBundle\Entity\User;
use Symfony\Component\Security\Core\Authentication\Token\TokenInterface;
use Symfony\Component\Security\Core\Authorization\Voter\Voter;
use Symfony\Component\Security\Core\Authorization\AccessDecisionManagerInterface;

class AnswerVoter extends Voter
{

    const EDIT      = 'edit';
    const DELETE    = 'delete';

    private $decisionManager;

    public function __construct(AccessDecisionManagerInterface $decisionManager)
    {
        $this->decisionManager = $decisionManager;
    }

    protected function supports($attribute, $subject)
    {

        if (!in_array($attribute, array(self::EDIT, self::DELETE))) {
            return false;
        }

        if (!$subject instanceof Answer) {
            return false;
        }

        return true;
    }

    protected function voteOnAttribute($attribute, $subject, TokenInterface $token)
    {
        $user = $token->getUser();

        if (!$user instanceof User) {
            return false;
        }

        /* Admin can edit / delete all answers. UNLIMITED POWER !! */
        if ($this->decisionManager->decide($token, array('ROLE_ADMIN'))) {
            return true;
        }

        /** @var Answer $answer */
        $answer = $subject;

        switch ($attribute) {
            case self::EDIT:
                return $this->canEdit($answer, $user);
            case self::DELETE:
                return $this->canDelete($answer, $user);
        }

        throw new \LogicException('This code should not be reached!');
    }

    private function canEdit(Answer $answer, User $user)
    {
        return $user === $answer->getUser();
    }

    private function canDelete(Answer $answer, User $user)
    {
        return $user === $answer->getUser();
    }
}