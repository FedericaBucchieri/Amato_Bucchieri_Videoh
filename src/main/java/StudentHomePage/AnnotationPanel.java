package StudentHomePage;

import EventManagement.Listener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class AnnotationPanel {
    private AnnotationPanelModel model;
    private AnnotationPanelUI ui;
    private List<Listener> listeners = new ArrayList<>();

    public AnnotationPanel(VideoBox videoBox) {
        this.listeners.add(videoBox);

        this.model = new AnnotationPanelModel(videoBox.getSlider());
        this.ui = new AnnotationPanelUI();
        this.ui.install(this);
    }

    public JPanel getMainPanel(){
        return ui.getMainPanel();
    }

    public AnnotationPanelModel getModel() {
        return model;
    }
}
