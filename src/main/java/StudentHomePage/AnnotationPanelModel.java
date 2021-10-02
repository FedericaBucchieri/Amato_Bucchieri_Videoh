package StudentHomePage;

import javax.swing.*;

public class AnnotationPanelModel {
    private JSlider videoSlider;

    public AnnotationPanelModel(JSlider videoSlider) {
        this.videoSlider = videoSlider;
    }

    public int getSliderWidth(){
        return videoSlider.getWidth();
    }
}
