package services;

import entities.Professor;
import entities.Video;
import exceptions.UpdateVideoException;
import jakarta.persistence.*;
import Utils.Utils;

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
        try {
            Video video = em.createNamedQuery("Video.findVideoByCode", Video.class).setParameter("code", videoCode)
                    .getSingleResult();
            return video;
        }catch (NoResultException e){
            return null;
        }
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

    public Video createVideo(String title, String description, File previewImage, File file, Professor professor){
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

    /**
     * Delete the video with the given id from the database
     * @param videoId the ID of the video to eliminate from the DB
     */
    public void deleteVideo(int videoId){
        Video video = em.find(Video.class, videoId);
        Professor professor = video.getProfessor();
        professor.removeVideo(video);
        System.out.println("delete video inside database");

        em.getTransaction().begin();
        em.remove(video);
        em.getTransaction().commit();
    }

    public void updateVideo(Video video, String title, String description, File previewImage, File videoFile) throws UpdateVideoException {
        if(title != null)
            video.setTitle(title);

        if(description != null)
            video.setDescription(description);

        if(previewImage != null)
            video.setPreviewImage(previewImage);

        if(videoFile != null)
            video.setFile(videoFile);

        try {
            em.getTransaction().begin();
            em.merge(video);
            em.getTransaction().commit();
        } catch (PersistenceException e){
            throw new UpdateVideoException(Utils.ERROR_UPDATE_VIDEO);
        }
    }
}
