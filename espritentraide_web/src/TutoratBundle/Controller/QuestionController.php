<?php

namespace TutoratBundle\Controller;

use Sensio\Bundle\FrameworkExtraBundle\Configuration\Route;
use Symfony\Bundle\FrameworkBundle\Controller\Controller;
use Symfony\Component\HttpFoundation\Request;
use Doctrine\Common\Persistence\ObjectManager;
use Knp\Component\Pager\Paginator;
use TutoratBundle\Entity\Question;
use TutoratBundle\Entity\Answer;
use TutoratBundle\Form\QuestionType;
use TutoratBundle\Form\AnswerType;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\ParamConverter;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Security;
use Sensio\Bundle\FrameworkExtraBundle\Configuration\Method;

class QuestionController extends Controller
{

    /**
     * @Route("/questions/{page}", name="view_questions")
     */
    public function viewQuestionsAction(Paginator $paginator, ObjectManager $em, $page = 1)
    {

        $query = $em->createQuery('SELECT q FROM TutoratBundle:Question q ORDER BY q.created_at DESC');
        $pagination = $paginator->paginate($query, $page);

        return $this->render('TutoratBundle::question/view_questions.html.twig',[
            'pagination'        => $pagination,
        ]);
    }

    /**
     * @Route("/question/{id}", name="view_question")
     * @ParamConverter("question", class="TutoratBundle:Question")
     */
    public function viewQuestionAction(Question $question, Request $request, ObjectManager $em)
    {

        $form = $this->createForm(AnswerType::class);
        $form->handleRequest($request);
 
        if ($form->isSubmitted() && $form->isValid()) {
    
            if (!$this->get('security.authorization_checker')->isGranted('IS_AUTHENTICATED_REMEMBERED')) {
                $this->addFlash('error', 'You must be connected to answer');
                return $this->redirectToRoute('view_question', ['id' => $question->getId()]);
            } else {

                $answer = $form->getData();
                $answer->setUser($this->getUser());
                $answer->setQuestion($question);
                $em->persist($answer);
                $em->flush();
 
                $this->addFlash('success', 'Your answer has been submitted');
 
                return $this->redirectToRoute('view_question', [
                    'id' => $question->getId(),
                ]);
            }
        }

        return $this->render('TutoratBundle::question/view_question.html.twig', [
            'question'      => $question,
            'form'          => $form->createView(),
        ]);
    }

    /**
     * @Route("/add/question", name="add_question")
     * @Security("has_role('ROLE_USER')")
     */
    public function addQuestionAction(Request $request, ObjectManager $em)
    {
        $add_question_form = $this->createForm(QuestionType::class);
        $add_question_form->handleRequest($request);

        if ($add_question_form->isSubmitted() && $add_question_form->isValid()) {

            $question = $add_question_form->getData();
            $question->setUser($this->getUser());
            $em->persist($question);
            $em->flush();
 
            $this->addFlash('success', 'Your question has been submitted');

            return $this->redirectToRoute('view_questions');
        }

        return $this->render('TutoratBundle::question/add_question.html.twig', [
            'add_question_form' => $add_question_form->createView(),
        ]);
    }

    /**
     * @Route("/question/{id}/edit", name="edit_question")
     * @Method({"GET", "POST"})
     * @Security("is_granted('edit', question)")
     * @ParamConverter("question", class="TutoratBundle:Question")
     */
    public function editQuestionAction(Request $request, Question $question, ObjectManager $em)
    {
        $edit_question_form = $this->createForm('TutoratBundle\Form\QuestionType', $question);
        $edit_question_form->handleRequest($request);

        if ($edit_question_form->isSubmitted() && $edit_question_form->isValid()) {
            $em->flush();

            $this->addFlash('success', 'Question edited');
            return $this->redirectToRoute('view_question', array('id' => $question->getId()));
        }

        return $this->render('TutoratBundle::question/edit_question.html.twig', [
            'question'              => $question,
            'edit_question_form'    => $edit_question_form->createView(),
        ]);
    }

    /**
     * @Route("/question/{id}/delete", name="delete_question")
     * @Security("is_granted('delete', question)")
     * @ParamConverter("question", class="TutoratBundle:Question")
     */
    public function deleteQuestionAction(ObjectManager $em, $id, Question $question)
    {

        $rep = $this->getDoctrine()->getRepository('TutoratBundle:Question');
        $question = $rep->find($id);

        $em->remove($question);
        $em->flush();

        $this->addFlash('success', 'Question deleted');
        return $this->redirectToRoute('view_questions');
    }
}
