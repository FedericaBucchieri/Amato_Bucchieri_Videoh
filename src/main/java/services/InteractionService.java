package services;

import entities.Interaction;
import entities.Video;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import java.util.List;


public class InteractionService {
    private final static int TYPE_UNCLEAR = 0;
    private final static int TYPE_CLEAR = 1;

    @PersistenceContext(unitName="default")
    private EntityManager em;

    public InteractionService(EntityManager em) {
        this.em = em;
    }

    public Interaction findInteractionById(int interactionId) {
        Interaction interaction = em.find(Interaction.class, interactionId);
        return interaction;
    }

    public List<Interaction> findInteractionsByVideo(int videoId) {
        Video video = em.find(Video.class, videoId);
        return video.getInteractionList();
    }

    public List<Interaction> findClearInteractionsByVideo(int videoId) {
        List<Interaction> interactions = em
                .createQuery("Select interaction from Interaction interaction where interaction.video.id = :videoId AND interaction.type =" + TYPE_CLEAR + "", Interaction.class)
                .setParameter("videoId", videoId).getResultList();
        return interactions;
    }

    public List<Interaction> findNotClearInteractionsByVideo(int videoId) {
        List<Interaction> interactions = em
                .createQuery("Select interaction from Interaction interaction where interaction.video.id = :videoId AND interaction.type =" + TYPE_UNCLEAR + "", Interaction.class)
                .setParameter("videoId", videoId).getResultList();
        return interactions;
    }

    public void createInteraction(int type, int videoId){
        Video video = em.find(Video.class, videoId);
        Interaction interaction = new Interaction(type, video);
        em.getTransaction().begin();
        em.persist(interaction);
        em.getTransaction().commit();
    }
}
