package ProfessorHomePage;

import entities.Interaction;
import entities.Question;
import entities.Video;
import services.InteractionService;
import services.QuestionService;
import services.VideoService;

import java.util.List;

public class StatisticsPaneModel {
    //The statisticsPane component
    StatisticsPane controller;
    //The video for which the statistics are shown in the StatisticsPane
    private Video video;

    /**
     * Creates the model of the StatisticsPane component
     * @param controller: the StatisticsPane component
     * @param videoId: the video id for which the statistics are to be shown.
     */
    public StatisticsPaneModel(StatisticsPane controller, int videoId){
        this.controller = controller;

        VideoService service = new VideoService();
        this.video = service.findVideoById(videoId);

    }

    /**
     * This method retrieves the complete list of interaction corresponding to video attribute of this class
     * @return the complete list of interaction of the video
     */
    public List<Interaction> getTotalInteractions(){
        InteractionService service = new InteractionService();
        return service.findInteractionsByVideo(video.getId());
    }

    /**
     * This method retrieves the list of only negative interaction of the video attribute of this class
     * @return the list of negative interaction of the video
     */
    public List<Interaction> getNegativeInteractions(){
        InteractionService service = new InteractionService();
        return service.findNegativeInteractions(video.getId());
    }

    /**
     * This method retrieves the list of only positive interaction of the video attribute of this class
     * @return the list of positive interaction of the video
     */
    public List<Interaction> getPositiveInteractions(){
        InteractionService service = new InteractionService();
        return service.findPositiveInteractions(video.getId());
    }

    /**
     * This method retrieve the list of questions related to the video attribute of this class
     * @return the complete list of questions of the video
     */
    public List<Question> getTotalQuestions(){
        QuestionService service = new QuestionService();
        return service.findQuestionsByVideo(video.getId());
    }

    public Video getVideo() {
        return video;
    }
}
