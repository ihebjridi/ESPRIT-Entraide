<?php

namespace TutoratBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;
use Doctrine\Common\Collections\ArrayCollection;
use Gedmo\Mapping\Annotation as Gedmo;

/**
 * @ORM\Entity(repositoryClass="TutoratBundle\Repository\QuestionRepository")
 * @ORM\Table(name="question")
 */
class Question
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
    private $title;

    /**
     * @ORM\Column(type="text", length=255)
     * @Assert\NotBlank()
     */
    private $content;

    /**
     * @ORM\OneToMany(targetEntity="TutoratBundle\Entity\Answer", mappedBy="question", cascade={"remove"})
     */
    private $answers;

    /**
     * @ORM\ManyToOne(targetEntity="UserBundle\Entity\User", inversedBy="questions")
     * @ORM\JoinColumn(name="user_id", referencedColumnName="id")
     */
    private $user;

    /**
     * The owning side has to use the "inversedBy" attribute
     *
     * @ORM\ManyToMany(targetEntity="TutoratBundle\Entity\Tag", inversedBy="questions", cascade={"persist"})
     * @ORM\JoinTable(name="questions_tags",
     *      joinColumns={@ORM\JoinColumn(name="question_id", referencedColumnName="id")},
     *      inverseJoinColumns={@ORM\JoinColumn(name="tag_id", referencedColumnName="id")}
     *      )
     */
    private $tags;

    /**
     * @Gedmo\Timestampable(on="create")
     * @ORM\Column(name="created_at", type="datetime", nullable=false)
     */
    private $created_at;
    
    public function __construct()
    {
        $this->answers = new ArrayCollection();
        $this->tags = new ArrayCollection();
    }

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
     * @return Question
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
     * Add answer
     *
     * @param \TutoratBundle\Entity\Answer $answer
     *
     * @return Question
     */
    public function addAnswer(\TutoratBundle\Entity\Answer $answer)
    {
        $this->answers[] = $answer;

        return $this;
    }

    /**
     * Remove answer
     *
     * @param \TutoratBundle\Entity\Answer $answer
     */
    public function removeAnswer(\TutoratBundle\Entity\Answer $answer)
    {
        $this->answers->removeElement($answer);
    }

    /**
     * Get answers
     *
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getAnswers()
    {
        return $this->answers;
    }

    /**
     * Set title
     *
     * @param string $title
     *
     * @return Question
     */
    public function setTitle($title)
    {
        $this->title = $title;

        return $this;
    }

    /**
     * Get title
     *
     * @return string
     */
    public function getTitle()
    {
        return $this->title;
    }

    /**
     * Set user
     *
     * @param \UserBundle\Entity\User $user
     *
     * @return Question
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
     * Add tag
     *
     * @param \TutoratBundle\Entity\Tag $tag
     *
     * @return Question
     */
    public function addTag(\TutoratBundle\Entity\Tag $tag)
    {
        $tag->addQuestion($this);
        $this->tags[] = $tag;

        return $this;
    }

    /**
     * Remove tag
     *
     * @param \TutoratBundle\Entity\Tag $tag
     */
    public function removeTag(\TutoratBundle\Entity\Tag $tag)
    {
        $this->tags->removeElement($tag);
    }

    /**
     * Get tags
     *
     * @return \Doctrine\Common\Collections\Collection
     */
    public function getTags()
    {
        return $this->tags;
    }

    /**
     * Set createdAt
     *
     * @param \DateTime $createdAt
     *
     * @return Question
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
