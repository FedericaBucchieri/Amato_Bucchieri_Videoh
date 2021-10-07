package services;

import entities.Interaction;
import entities.Professor;
import entities.Video;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import jakarta.persistence.PersistenceContext;
import sceneManager.Utils;

import java.util.Date;
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
        System.out.println("service: findInteractionsByVideo for video "+videoId);
        Video video = em.find(Video.class, videoId);
        return video.getInteractionList();
    }

    public List<Interaction> findPositiveInteractionsByVideo(int videoId) {
        List<Interaction> interactions = em
                .createQuery("Select interaction from Interaction interaction where interaction.video.id = :videoId AND interaction.type =" + Utils.POSITIVE_INTERACTION + "", Interaction.class)
                .setParameter("videoId", videoId).getResultList();
        return interactions;
    }

    public List<Interaction> findNegativeInteractionsByVideo(int videoId) {
        List<Interaction> interactions = em
                .createQuery("Select interaction from Interaction interaction where interaction.video.id = :videoId AND interaction.type =" + Utils.NEGATIVE_INTERACTION + "", Interaction.class)
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
}
