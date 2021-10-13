package services;

import entities.Interaction;
import entities.Question;
import entities.Video;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class QuestionService {
    @PersistenceContext(unitName="default")
    private EntityManager em;
    private EntityManagerFactory entityManagerFactory;


    public QuestionService() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("default");
        this.em = entityManagerFactory.createEntityManager();
    }

    public Question findQuestionById(int questionId) {
        Question question = em.find(Question.class, questionId);
        return question;
    }

    public List<Question> findQuestionsByVideo(int videoId) {
        List<Question> questions = em
                .createQuery("Select question from Question question where question.video.id = :videoId", Question.class)
                .setParameter("videoId", videoId).getResultList();
        return questions;
    }

    public Question createQuestion(String text, String student, int videoId, int timestamp){
        Video video = em.find(Video.class, videoId);
        Question question = new Question(text, student, video, timestamp);
        em.getTransaction().begin();
        em.persist(question);
        em.getTransaction().commit();

        return question;
    }


    public void updateQuestionTimestamp(Question question, int timestamp){
        question.setTimestamp(timestamp);

        em.getTransaction().begin();
        em.merge(question);
        em.getTransaction().commit();
    }

    public void updateQuestionBody(Question question, String text){
        question.setText(text);

        em.getTransaction().begin();
        em.merge(question);
        em.getTransaction().commit();
    }

    public void deleteQuestion(int questionId){
        Question toBeDeleted = em.find(Question.class, questionId);

        Video video = toBeDeleted.getVideo();
        video.removeQuestion(toBeDeleted);

        em.getTransaction().begin();
        em.remove(toBeDeleted);
        em.getTransaction().commit();
    }

}
