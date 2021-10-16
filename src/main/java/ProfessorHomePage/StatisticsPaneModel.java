package ProfessorHomePage;

import entities.Video;

public class StatisticsPaneModel {
    //The statisticsPane component
    StatisticsPane controller;
    //The video for which the statistics are shown in the StatisticsPane
    private Video video;

    /**
     * Creates the model of the StatisticsPane component
     * @param controller: the StatisticsPane component
     * @param video: the video for which the statistics are to be shown.
     */
    public StatisticsPaneModel(StatisticsPane controller, Video video){
        this.controller = controller;
        this.video = video;

    }

    public Video getVideo() {
        return video;
    }
}
