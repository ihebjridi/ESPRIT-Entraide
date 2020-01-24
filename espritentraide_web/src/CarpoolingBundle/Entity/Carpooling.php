<?php

namespace CarpoolingBundle\Entity;

use Doctrine\ORM\Mapping as ORM;
use Symfony\Component\Validator\Constraints as Assert;


/**
 * Carpooling
 *
 * @ORM\Table(name="carpooling")
 * @ORM\Entity(repositoryClass="CarpoolingBundle\Repository\CarpoolingRepository")
 */
class Carpooling
{
    /**
     * @var int
     *
     * @ORM\Column(name="id", type="integer")
     * @ORM\Id
     * @ORM\GeneratedValue(strategy="AUTO")
     */
    private $id;

    /**
     * @var string
     *
     * @ORM\Column(name="title", type="string", length=255)
     */
    private $title;

    /**
     * @var string
     *
     * @ORM\Column(name="description", type="string", length=255)
     */
    private $description;
    /**
     * @var string
     *
     * @ORM\Column(name="type", type="string", length=255,nullable=true)
     * @Assert\NotBlank()
     */
    private $type;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="created_at", type="datetime",nullable=true)
     */
    private $createdAt;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="published_at", type="datetime", nullable=true)
     */
    private $publishedAt;

    /**
     * @var \DateTime
     *
     * @ORM\Column(name="canceled_at", type="datetime", nullable=true)
     */
    private $canceledAt;

    /**
     * @var bool
     *
     * @ORM\Column(name="enabled", type="boolean",nullable=true)
     */
    private $enabled;

    /**
     * @var bool
     *
     * @ORM\Column(name="cancled", type="boolean",nullable=true)
     */
    private $cancled;
    /**
     * @var int
     *
     * @ORM\Column(name="part", type="integer", nullable=true)
     */

    private $part;
    /**
     * @ORM\ManyToOne(targetEntity="UserBundle\Entity\User")
     * @ORM\JoinColumn(name="id_user",referencedColumnName="id")
     */
    private $createdBy;


    /**
     * Get id
     *
     * @return int
     */
    public function getId()
    {
        return $this->id;
    }

    /**
     * Set titre
     *
     * @param string $titre
     *
     * @return Carpooling
     */
    public function setTitle($title)
    {
        $this->title = $title;

        return $this;
    }

    /**
     * Get titre
     *
     * @return string
     */
    public function getTitle()
    {
        return $this->title;
    }



    /**
     * Set description
     *
     * @param string $description
     *
     * @return Carpooling
     */
    public function setDescription($description)
    {
        $this->description = $description;

        return $this;
    }

    /**
     * Get description
     *
     * @return string
     */
    public function getDescription()
    {
        return $this->description;
    }

    /**
     * Set type
     *
     * @param string $type
     *
     * @return Carpooling
     */
    public function setType($type)
    {
        $this->type = $type;

        return $this;
    }

    /**
     * Get type
     *
     * @return string
     */
    public function getType()
    {
        return $this->type;
    }

    /**
     * Set createdAt
     *
     * @param \DateTime $createdAt
     *
     * @return Carpooling
     */
    public function setCreatedAt($createdAt)
    {
        $this->createdAt = $createdAt;

        return $this;
    }

    /**
     * Get createdAt
     *
     * @return \DateTime
     */
    public function getCreatedAt()
    {
        return $this->createdAt;
    }

    /**
     * Set publishedAt
     *
     * @param \DateTime $publishedAt
     *
     * @return Carpooling
     */
    public function setPublishedAt($publishedAt)
    {
        $this->publishedAt = $publishedAt;

        return $this;
    }

    /**
     * Get publishedAt
     *
     * @return \DateTime
     */
    public function getPublishedAt()
    {
        return $this->publishedAt;
    }

    /**
     * Set canceledAt
     *
     * @param \DateTime $canceledAt
     *
     * @return Carpooling
     */
    public function setCanceledAt($canceledAt)
    {
        $this->canceledAt = $canceledAt;

        return $this;
    }

    /**
     * Get canceledAt
     *
     * @return \DateTime
     */
    public function getCanceledAt()
    {
        return $this->canceledAt;
    }

    /**
     * Set enabled
     *
     * @param boolean $enabled
     *
     * @return Carpooling
     */
    public function setEnabled($enabled)
    {
        $this->enabled = $enabled;

        return $this;
    }

    /**
     * Get enabled
     *
     * @return boolean
     */
    public function getEnabled()
    {
        return $this->enabled;
    }

    /**
     * Set cancled
     *
     * @param boolean $cancled
     *
     * @return Carpooling
     */
    public function setCancled($cancled)
    {
        $this->cancled = $cancled;

        return $this;
    }

    /**
     * Get cancled
     *
     * @return boolean
     */
    public function getCancled()
    {
        return $this->cancled;
    }

    /**
     * Set part
     *
     * @param integer $part
     *
     * @return Carpooling
     */
    public function setPart($part)
    {
        $this->part = $part;

        return $this;
    }

    /**
     * Get part
     *
     * @return integer
     */
    public function getPart()
    {
        return $this->part;
    }



    /**
     * Set createdBy
     *
     * @param \UserBundle\Entity\User $createdBy
     *
     * @return Carpooling
     */
    public function setCreatedBy(\UserBundle\Entity\User $createdBy = null)
    {
        $this->createdBy = $createdBy;

        return $this;
    }

    /**
     * Get createdBy
     *
     * @return \UserBundle\Entity\User
     */
    public function getCreatedBy()
    {
        return $this->createdBy;
    }
}
