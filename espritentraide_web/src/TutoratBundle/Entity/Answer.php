<?php

namespace TutoratBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Gedmo\Mapping\Annotation as Gedmo;

/**
 * @ORM\Entity(repositoryClass="TutoratBundle\Repository\AnswerRepository")
 * @ORM\Table(name="answer")
 */
class Answer
{
    /**
     * @ORM\Id
     * @ORM\Column(type="integer")
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @ORM\Column(type="text", length=255)
     * @Assert\NotBlank()
     */
    private $content;

    /**
     * @ORM\ManyToOne(targetEntity="TutoratBundle\Entity\Question", inversedBy="answers")
     * @ORM\JoinColumn(name="question_id", referencedColumnName="id")
     */
    private $question;

    /**
     * @ORM\Column(name="vote", type="integer", nullable=true, options={"default":0})
     */
    private $vote = 0;

    /**
     * @ORM\Column(name="valid", type="boolean", options={"default":false})
     */
    private $valid = false;

    /**
     * @ORM\ManyToOne(targetEntity="UserBundle\Entity\User", inversedBy="answers")
     * @ORM\JoinColumn(name="user_id", referencedColumnName="id")
     */
    private $user;

    /**
     * @Gedmo\Timestampable(on="create")
     * @ORM\Column(name="created_at", type="datetime", nullable=false)
     */
    private $created_at;

    /**
     * Get id
     *
     * @return integer
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set content
     *
     * @param string $content
     *
     * @return Answer
     */
    public function setContent($content)
    {
        $this->content = $content;

        return $this;
    }

    /**
     * Get content
     *
     * @return string
     */
    public function getContent()
    {
        return $this->content;
    }

    /**
     * Set vote
     *
     * @param \integer $vote
     *
     * @return Answer
     */
    public function setVote($vote)
    {
        $this->vote = $vote;

        return $this;
    }

    /**
     * Get vote
     *
     * @return \integer
     */
    public function getVote()
    {
        return $this->vote;
    }

    /**
     * Set question
     *
     * @param \TutoratBundle\Entity\Question $question
     *
     * @return Answer
     */
    public function setQuestion(\TutoratBundle\Entity\Question $question = null)
    {
        $this->question = $question;

        return $this;
    }

    /**
     * Get question
     *
     * @return \TutoratBundle*\Entity\Question
     */
    public function getQuestion()
    {
        return $this->question;
    }

    /**
     * Set user
     *
     * @param \UserBundle\Entity\User $user
     *
     * @return Answer
     */
    public function setUser(\UserBundle\Entity\User $user = null)
    {
        $this->user = $user;

        return $this;
    }

    /**
     * Get user
     *
     * @return \UserBundle\Entity\User
     */
    public function getUser()
    {
        return $this->user;
    }

    /**
     * Set valid
     *
     * @param boolean $valid
     *
     * @return Answer
     */
    public function setValid($valid)
    {
        $this->valid = $valid;

        return $this;
    }

    /**
     * Get valid
     *
     * @return boolean
     */
    public function getValid()
    {
        return $this->valid;
    }

    /**
     * Set createdAt
     *
     * @param \DateTime $createdAt
     *
     * @return Answer
     */
    public function setCreatedAt($createdAt)
    {
        $this->created_at = $createdAt;

        return $this;
    }

    /**
     * Get createdAt
     *
     * @return \DateTime
     */
    public function getCreatedAt()
    {
        return $this->created_at;
    }
}
