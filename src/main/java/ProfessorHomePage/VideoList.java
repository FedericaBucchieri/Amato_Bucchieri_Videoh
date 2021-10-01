package ProfessorHomePage;

import entities.Professor;
import entities.Video;
import sceneManager.Utils;

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


    @Override
    public void paintComponent(Graphics pen) {
        this.ui.paint();
        add(this.ui.getMainPanel(), BorderLayout.CENTER);
    }


    public Dimension getMinimumSize() { return getPreferredSize(); }
    public Dimension getPreferredSize() {
        return new Dimension(Utils.VIDEOLIST_WIDTH, Utils.VIDEOLIST_HEIGHT);
    }
    public Dimension getMaximumSize() { return getPreferredSize(); }
}
