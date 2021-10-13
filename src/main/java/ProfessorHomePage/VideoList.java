package ProfessorHomePage;

import entities.Professor;
import entities.Video;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VideoList extends JComponent  {
    private VideoListModel model;
    private VideoListUI ui;
    private ProfessorHomePage parentComponent;

    public VideoList(Professor professor, ProfessorHomePage professorHomePage) {
        this.parentComponent = professorHomePage;
        this.model = new VideoListModel(professor);
        this.ui = new VideoListUI();
        this.ui.installUI(this);

        this.setLayout(new BorderLayout());
    }

    public ProfessorHomePage getParentComponent() {
        return parentComponent;
    }

    public JPanel getMainPanel(){
        return ui.getMainPanel();
    }

    public List<Video> getVideoList(){
        return model.getVideoList();
    }

    public void removeVideo(Video video){
        model.removeVideo(video);
        this.ui.paint();
    }


    @Override
    public void paintComponent(Graphics pen) {
        this.ui.paint();
    }


    public Dimension getMinimumSize() { return getPreferredSize(); }
    public Dimension getPreferredSize() {
        return new Dimension(Utils.VIDEOLIST_WIDTH, Utils.VIDEOLIST_HEIGHT);
    }
    public Dimension getMaximumSize() { return getPreferredSize(); }
}
