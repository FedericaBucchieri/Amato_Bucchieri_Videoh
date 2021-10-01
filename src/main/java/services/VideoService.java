package services;

import entities.Professor;
import entities.Video;
import exceptions.UpdateVideoException;
import exceptions.UserNotRegisteredException;
import jakarta.persistence.*;
import sceneManager.Utils;
import uk.co.caprica.vlcj.player.media.Media;

import java.io.File;
import java.util.List;
import java.util.Random;

public class VideoService {

    @PersistenceContext(unitName="default")
    private EntityManager em;
    private EntityManagerFactory entityManagerFactory;


    public VideoService() {
        this.entityManagerFactory = Persistence.createEntityManagerFactory("default");
        this.em = entityManagerFactory.createEntityManager();
    }

    public Video findVideoById(int videoId) {
        Video video = em.find(Video.class, videoId);
        return video;
    }

    public Video findVideoByCode(int videoCode) {
       Video video = em.createNamedQuery("Video.findVideoByCode", Video.class).setParameter("code", videoCode)
                .getSingleResult();
        return video;
    }

    public List<Video> findVideosByProfessor(int professorId){
        Professor professor = em.find(Professor.class, professorId);
        return professor.getVideoList();
    }

    public List<Video> findVideoListByProfessor(int professorId){
            List<Video> videoList = em.createNamedQuery("Video.findVideoListByProfessor", Video.class).setParameter("id", professorId)
                    .getResultList();
            return videoList;
    }

    public Video createVideo(String title, String description, String previewImage, File file, Professor professor){
        Random random = new Random();
        int videoCode = random.nextInt(Utils.VIDEO_CODE_BOUND);

        Video video = new Video(title, description, previewImage, videoCode, file, professor);

        List<Video> videoList = professor.getVideoList();
        videoList.add(video);
        professor.setVideoList(videoList);

        em.getTransaction().begin();
        em.persist(video);
        em.getTransaction().commit();

        return video;
    }

    public void deleteVideo(int videoId){
        Video video = em.find(Video.class, videoId);
        Professor professor = video.getProfessor();
        professor.removeVideo(video);
        em.getTransaction().begin();
        em.remove(video);
        em.getTransaction().commit();
    }

    public void updateVideo(Video video, String title, String description, String preview) throws UpdateVideoException {
        if(title != null)
            video.setTitle(title);

        if(description != null)
            video.setDescription(description);

        if(preview != null)
            video.setPreviewImage(preview);

        try {
            em.merge(video);
        } catch (PersistenceException e){
            throw new UpdateVideoException(Utils.ERROR_UPDATE_VIDEO);
        }
    }
}
