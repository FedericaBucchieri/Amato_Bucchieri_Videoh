package services;

import entities.Question;
import entities.Video;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;

public class QuestionService {

    @PersistenceContext(unitName="default")
    private EntityManager em;

    public QuestionService(EntityManager em) {
        this.em = em;
    }

    public Question findQuestionById(int questionId) {
        Question question = em.find(Question.class, questionId);
        return question;
    }

    public List<Question> findQuestionsByVideo(int videoId) {
        Video video = em.find(Video.class, videoId);
        return video.getQuestionList();
    }

    public void createQuestion(String text, String student, int videoId){
        Video video = em.find(Video.class, videoId);
        Question question = new Question(text, student, video);
        em.getTransaction().begin();
        em.persist(question);
        em.getTransaction().commit();
    }
}
