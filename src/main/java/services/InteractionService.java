package services;

import entities.*;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;

import java.util.List;


public class InteractionService {
    @PersistenceContext(unitName="default")
    private EntityManager em;
    private EntityManagerFactory entityManagerFactory;


    public InteractionService() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("default");
        this.em = entityManagerFactory.createEntityManager();
    }

    /**
     * This method return the list of interactions associated to a video from the database
     * @param videoId the id of the video to retrieve the interaction list of
     * @return the interaction list of the video
     */
    public List<Interaction> findInteractionsByVideo(int videoId) {
        List<Interaction> interactions = em
                .createQuery("Select interaction from Interaction interaction where interaction.video.id = :videoId", Interaction.class)
                .setParameter("videoId", videoId).getResultList();
        return interactions;
    }

    /**
     * This method create a new instance of Interaction and adds it to the database
     * @param type the integer value corresponding to the type of interaction
     * @param videoId the id of the video the interaction is related to
     * @param timestamp the timestamp of the interaction
     * @return the new instance of interaction created
     */
    public Interaction createInteraction(int type, int videoId, int timestamp){
        Video video = em.find(Video.class, videoId);
        Interaction interaction = new Interaction(type, video, timestamp);
        em.getTransaction().begin();
        em.persist(interaction);
        em.getTransaction().commit();

        return interaction;
    }

    /**
     * This method updates an instance of interaction into the database
     * @param interaction the interaction instance to be uploaded
     * @param timestamp the timestamp to change
     */
    public void updateInteraction(Interaction interaction, int timestamp){
        interaction.setTimestamp(timestamp);

        em.getTransaction().begin();
        em.merge(interaction);
        em.getTransaction().commit();
    }

    /**
     * This method retrieves the list of questions associated to a video from the databse
     * @param videoId the video id
     * @return the list of question related to the video id
     */
    public List<Question> findQuestionsByVideo(int videoId) {
        Video video = em.find(Video.class, videoId);
        return video.getQuestionList();
    }
    // TODO TO be removed

    /**
     * This method delete an interaction instance from the database
     * @param interactionId the id of the interaction to delete
     */
    public void deleteInteraction(int interactionId){
        Interaction interaction = em.find(Interaction.class, interactionId);
        Video video = interaction.getVideo();
        video.removeInteraction(interaction);

        em.getTransaction().begin();
        em.remove(interaction);
        em.getTransaction().commit();
    }

}
