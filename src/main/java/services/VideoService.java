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

    /**
     * This method retrieves a video instance from the database knowing its id
     * @param videoId the id of the video to be found
     * @return the video instance if present, null otherwise
     */
    public Video findVideoById(int videoId){
        Video video = em.find(Video.class, videoId);
        return video;
    }

    /**
     * This method retrieves a video instance from the database knowing its videoCode
     * @param videoCode the code correspondent to the video to retrieve
     * @return the instance of video to be found, null otherwise
     */
    public Video findVideoByCode(int videoCode) {
        try {
            Video video = em.createNamedQuery("Video.findVideoByCode", Video.class).setParameter("code", videoCode)
                    .getSingleResult();
            return video;
        }catch (NoResultException e){
            return null;
        }
    }

    /**
     * This method retrieves the video list associated to a professor from the database
     * @param professorId the professor id
     * @return the list of Video of a professor
     */
    public List<Video> findVideoListByProfessor(int professorId){
            List<Video> videoList = em.createNamedQuery("Video.findVideoListByProfessor", Video.class).setParameter("id", professorId)
                    .getResultList();
            return videoList;
    }

    /**
     * This method creates a new video instance and stores it in the database
     * @param title The title of the new video
     * @param description the description of the new video
     * @param previewImage the preview image of the new video
     * @param file the video file
     * @param professor the professor instance that is going to create the video
     * @return the new video created
     */
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
     * This method deletes a video instance from the database
     * @param videoId the id of the video to be deleted
     */
    public void deleteVideo(int videoId){
        Video video = em.find(Video.class, videoId);
        Professor professor = video.getProfessor();
        professor.removeVideo(video);

        em.getTransaction().begin();
        em.remove(video);
        em.getTransaction().commit();
    }

    /**
     * This method updates an instance of a video, updating its information
     * @param video the video to be updated
     * @param title the new title of the video
     * @param description the new description of the video
     * @param previewImage the new previewImage of the video
     * @param videoFile the new file of the video
     * @throws UpdateVideoException when an error occurs updating the video
     */
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
