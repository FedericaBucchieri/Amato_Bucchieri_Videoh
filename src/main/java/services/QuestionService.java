package services;

import entities.Question;
import entities.Video;
import jakarta.persistence.*;

import java.util.List;

public class QuestionService {
    @PersistenceContext(unitName="default")
    private EntityManager em;
    private EntityManagerFactory entityManagerFactory;


    public QuestionService() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("default");
        this.em = entityManagerFactory.createEntityManager();
    }

    /**
     * This method retrieves the list of questions associated by a video from a database
     * @param videoId the video id
     * @return the list of questions retrieved
     */
    public List<Question> findQuestionsByVideo(int videoId) {
        List<Question> questions = em
                .createQuery("Select question from Question question where question.video.id = :videoId", Question.class)
                .setParameter("videoId", videoId).getResultList();
        return questions;
    }

    /**
     * This method creates a new question instance and stores it into the database
     * @param text the text of the question
     * @param student the username of the student who wrote the question
     * @param videoId the video id the question relates to
     * @param timestamp the timestamp of the question
     * @return the new question created
     */
    public Question createQuestion(String text, String student, int videoId, int timestamp){
        Video video = em.find(Video.class, videoId);
        Question question = new Question(text, student, video, timestamp);
        em.getTransaction().begin();
        em.persist(question);
        em.getTransaction().commit();

        return question;
    }

    /**
     * This method updates the timestamp of an instance of Question from the database
     * @param question the question to be updated
     * @param timestamp the new timestamp to be set
     */
    public void updateQuestionTimestamp(Question question, int timestamp){
        question.setTimestamp(timestamp);

        em.getTransaction().begin();
        em.merge(question);
        em.getTransaction().commit();
    }

    /**
     * This method updates the text of an instance of Question from the database
     * @param question the question to be updated
     * @param text the text to be set
     */
    public boolean updateQuestionBody(Question question, String text){

        try {
            question.setText(text);

            em.getTransaction().begin();
            em.merge(question);
            em.getTransaction().commit();
            return true;
        } catch (PersistenceException e){
            return false;
        }
    }

    /**
     * This method deletes a question from the database
     * @param questionId the id of the question to be deleted
     */
    public void deleteQuestion(int questionId){
        Question toBeDeleted = em.find(Question.class, questionId);

        Video video = toBeDeleted.getVideo();
        video.removeQuestion(toBeDeleted);

        em.getTransaction().begin();
        em.remove(toBeDeleted);
        em.getTransaction().commit();
    }

}
