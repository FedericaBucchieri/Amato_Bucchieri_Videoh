package ProfessorHomePage;

import entities.Professor;
import entities.Video;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class VideoList extends JComponent  {
    //The model of a VideoList element
    private VideoListModel model;
    //The view of a VideoList element
    private VideoListView view;
    //The component that controlls the VideoList
    private ProfessorHomePageScene parentComponent;

    /**
     * Creates a new VideoList for the givenProfessor: it containts all the video that this professor have uploaded.
     * @param professor: the logged in professor
     * @param professorHomePageScene: the component that controls the VideoList
     */
    public VideoList(Professor professor, ProfessorHomePageScene professorHomePageScene) {
        this.parentComponent = professorHomePageScene;
        this.model = new VideoListModel(professor);
        this.view = new VideoListView();
        this.view.installUI(this);

        this.setLayout(new BorderLayout());
    }

    public ProfessorHomePageScene getParentComponent() {
        return parentComponent;
    }

    public JPanel getMainPanel(){
        return view.getMainPanel();
    }

    public List<Video> getVideoList(){
        return model.getVideoList();
    }

    /**
     * Remove the given video from the videoList
     * @param video the video to be removed
     */
    public void removeVideo(Video video){
        model.removeVideo(video);
        this.view.paint();
    }


    @Override
    public void paintComponent(Graphics pen) {
        this.view.paint();
    }


    public Dimension getMinimumSize() { return getPreferredSize(); }
    public Dimension getPreferredSize() {
        return new Dimension(Utils.VIDEOLIST_WIDTH, Utils.VIDEOLIST_HEIGHT);
    }
    public Dimension getMaximumSize() { return getPreferredSize(); }
}
