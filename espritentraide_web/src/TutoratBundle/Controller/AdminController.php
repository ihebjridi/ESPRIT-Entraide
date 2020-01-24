<?php

namespace TutoratBundle\Controller;

use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Doctrine\Common\Persistence\ObjectManager;
use Knp\Component\Pager\Paginator;
use TutoratBundle\Entity\Answer;
use TutoratBundle\Form\TagType;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Security;

class AdminController extends Controller
{

    /**
     * @Route("/admin/add/tag", name="add_tag")
     * @Security("has_role('ROLE_ADMIN')")
     */
    public function addTagAction(Request $request, ObjectManager $em)
    {
        $add_tag_form = $this->createForm(TagType::class);
        $add_tag_form->handleRequest($request);

        if ($add_tag_form->isSubmitted() && $add_tag_form->isValid()) {

            $tag = $add_tag_form->getData();
            $em->persist($tag);
            $em->flush();
 
            $this->addFlash('success', 'Tag created');
            return $this->redirectToRoute('view_tags');
        }

        return $this->render('TutoratBundle::admin/add_tag.html.twig', [
            'add_tag_form' => $add_tag_form->createView(),
        ]);
    }

    /**
     * @Route("/admin/answer/{id}/valid", name="answer_valid")
     * @Security("has_role('ROLE_ADMIN')")
     * @ParamConverter("answer", class="TutoratBundle:Answer")
     */
    public function validAnswerAction(Answer $answer, ObjectManager $em)
    {

    	if ($answer->getValid() == 1) {
    		$answer->setValid(false);
    		$em->flush();

    		$this->addFlash('info', 'Answer disabled');
    	} else {
    		$answer->setValid(true);
    		$em->flush();

    		$this->addFlash('info', 'Answer enabled');
    	}

        return $this->redirectToRoute('answers_listing_admin');
    }

    /**
     * @Route("/admin/answers/{page}", name="answers_listing_admin")
     * @Security("has_role('ROLE_ADMIN')")
     */
    public function answersListingAdminAction(Paginator $paginator, ObjectManager $em, $page = 1)
    {

        $query = $em->createQuery('SELECT a FROM TutoratBundle:Answer a ORDER BY a.content ASC');
        $pagination = $paginator->paginate($query, $page);

        return $this->render('TutoratBundle::admin/answers_listing_admin.html.twig', [
            'pagination' => $pagination,
        ]);
    }
}
