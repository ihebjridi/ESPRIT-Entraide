<?php

namespace LostObjectBundle\Entity;

use Doctrine\ORM\Mapping as ORM;

/**
 * Event
 *
 * @ORM\Table(name="favoris_lostobject")
 * @ORM\Entity(repositoryClass="LostObjectBundle\Repository\LostObjectRepository")
 */
class Favoris
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
     * @ORM\ManyToOne(targetEntity="UserBundle\Entity\User")
     * @ORM\JoinColumn(name="id_user",referencedColumnName="id")
     */
    private $user;


    /**
     * @ORM\ManyToOne(targetEntity="LostObjectBundle\Entity\LostObject")
     * @ORM\JoinColumn(name="id_lostobject",referencedColumnName="id")
     */
    private $lostobject;

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
     * @return mixed $user
     */
    public function getUser()
    {
        return $this->user;
    }

    /**
     * @param mixed $user
     */
    public function setUser($user)
    {
        $this->user = $user;
    }


    /**
     * @return mixed $lostobject
     */
    public function getLostObject()
    {
        return $this->lostobject;
    }

    /**
     * @param mixed $lostobject
     */
    public function setLostObject($lostobject)
    {
        $this->lostobject = $lostobject;
    }
}

