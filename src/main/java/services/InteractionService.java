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

    public Interaction findInteractionById(int interactionId) {
        Interaction interaction = em.find(Interaction.class, interactionId);
        return interaction;
    }


    public List<Interaction> findInteractionsByVideo(int videoId) {
        List<Interaction> interactions = em
                .createQuery("Select interaction from Interaction interaction where interaction.video.id = :videoId", Interaction.class)
                .setParameter("videoId", videoId).getResultList();
        return interactions;
    }

    public Interaction createInteraction(int type, int videoId, int timestamp){
        Video video = em.find(Video.class, videoId);
        Interaction interaction = new Interaction(type, video, timestamp);
        em.getTransaction().begin();
        em.persist(interaction);
        em.getTransaction().commit();

        return interaction;
    }

    public void updateInteraction(Interaction interaction, int timestamp){
        interaction.setTimestamp(timestamp);

        em.getTransaction().begin();
        em.merge(interaction);
        em.getTransaction().commit();
    }

    public List<Question> findQuestionByVideo(int videoId) {
        Video video = em.find(Video.class, videoId);
        return video.getQuestionList();
    }

    public void deleteInteraction(int interactionId){
        Interaction interaction = em.find(Interaction.class, interactionId);
        Video video = interaction.getVideo();
        video.removeInteraction(interaction);

        em.getTransaction().begin();
        em.remove(interaction);
        em.getTransaction().commit();

        System.out.println("deleted");
    }

}
