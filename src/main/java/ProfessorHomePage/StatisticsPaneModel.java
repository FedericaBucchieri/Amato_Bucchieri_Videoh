package ProfessorHomePage;

import entities.Video;

public class StatisticsPaneModel {
    StatisticsPane controller;
    private Video video;

    public StatisticsPaneModel(StatisticsPane controller, Video video){
        this.controller = controller;
        this.video = video;

    }

    public Video getVideo() {
        return video;
    }
}
