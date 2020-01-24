<?php

namespace TutoratBundle\Controller;

use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Doctrine\Common\Persistence\ObjectManager;
use Knp\Component\Pager\Paginator;
use TutoratBundle\Entity\Tag;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;

class TagController extends Controller
{

	/**
	 * @Route("/tags", name="view_tags")
	 */
	public function viewTagsAction(ObjectManager $em)
	{

		$tags = $em->getRepository('TutoratBundle:Tag')->findAll();

		return $this->render('TutoratBundle::tag/view_tags.html.twig', [
			'tags' => $tags,
		]);
	}

	/**
     * @Route("/questions/tagged/{name}", name="view_tagged_questions")
     */
    public function viewTaggedQuestionsAction(Paginator $paginator, ObjectManager $em, Tag $tag, $page = 1)
    {

    	$query = $em->createQuery('SELECT q FROM TutoratBundle:Question q JOIN q.tags tag WHERE tag = :tag ORDER BY q.created_at DESC');
    	$query->setParameter('tag', $tag);
    	$pagination = $paginator->paginate($query, $page);

        return $this->render('TutoratBundle::tag/view_tagged_questions.html.twig', [
        	'tag' 			=> $tag,
        	'pagination'	=> $pagination,
        ]);
    }

}