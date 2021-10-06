package StudentHomePage;

import EventManagement.*;
import entities.Video;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class VideoBox implements Listener {//controller

    private VideoBoxModel model;
    private VideoBoxUI UI;
    private List<Listener> listeners = new ArrayList<>();

    public VideoBox(VideoPlayerArea videoPlayerArea ,Video video, String username){
        this.listeners.add(videoPlayerArea);

        model = new VideoBoxModel(video, username);
        UI = new VideoBoxUI(this);
    }

    public JSlider getSlider(){
        return UI.getSlider();
    }

    public VideoBoxModel getModel() {
        return model;
    }

    public VideoBoxUI getUI() {
        return UI;
    }

    public int getVideoId(){
        return model.getVideo().getId();
    }

    public void dismissVideo() {
        getUI().dismissVideo();
    }


    @Override
    public void listen(Event event) {
        if (event.getClass().equals(FreezeEvent.class)){
            UI.freezeVideo();
        }
        else if (event.getClass().equals(NewQuestionEvent.class)){
            UI.restartVideoAfterFreeze();
            dispatchNewQuestionEvent((NewQuestionEvent) event);
        }
        else if (event.getClass().equals(RestartAfterFreezeEvent.class)){
            UI.restartVideoAfterFreeze();
        }
        else if (event.getClass().equals(UpdateQuestionEvent.class)){
            dispatchUpdateQuestionEvent((UpdateQuestionEvent) event);
        }
    }

    private void dispatchNewQuestionEvent(NewQuestionEvent event){
        for (Listener listener : listeners)
            listener.listen(event);
    }

    private void dispatchUpdateQuestionEvent(UpdateQuestionEvent event){
        for (Listener listener : listeners)
            listener.listen(event);
    }

}



/*
*
* {
//TODO: to remove. not at the moment, i still need it
*
    private static final int width = 600;

    private static final int height = 400;

    private final JFrame frame;

    private final JPanel videoSurface;

    private final BufferedImage image;

    private final DirectMediaPlayerComponent mediaPlayerComponent;

    public static void main(final String[] args) {
        new NativeDiscovery().discover();
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Tutorial_due(args);
            }
        });
    }

    public Tutorial_due(String[] args) {
        frame = new JFrame("Direct Media Player");
        frame.setBounds(100, 100, width, height);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        videoSurface = new VideoSurfacePanel();
        frame.setContentPane(videoSurface);
        image = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration()
                .createCompatibleImage(width, height);
        BufferFormatCallback bufferFormatCallback = new BufferFormatCallback() {
            @Override
            public BufferFormat getBufferFormat(int sourceWidth, int sourceHeight) {
                return new RV32BufferFormat(width, height);
            }
        };
        mediaPlayerComponent = new DirectMediaPlayerComponent(bufferFormatCallback) {
            @Override
            protected RenderCallback onGetRenderCallback() {
                return new TutorialRenderCallbackAdapter();
            }
        };
        frame.setVisible(true);
//        mediaPlayerComponent.getMediaPlayer().playMedia(args[0]);
        mediaPlayerComponent.getMediaPlayer().playMedia("/Users/andrewamato/Downloads/IMG_6363.MOV");
    }

    private class VideoSurfacePanel extends JPanel {

        private VideoSurfacePanel() {
            setBackground(Color.black);
            setOpaque(true);
            setPreferredSize(new Dimension(width, height));
            setMinimumSize(new Dimension(width, height));
            setMaximumSize(new Dimension(width, height));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
            g2.drawImage(image, null, 0, 0);
        }
    }

    private class TutorialRenderCallbackAdapter extends RenderCallbackAdapter {

        private TutorialRenderCallbackAdapter() {
            super(new int[width * height]);
        }

        @Override
        protected void onDisplay(DirectMediaPlayer mediaPlayer, int[] rgbBuffer) {
            // Simply copy buffer to the image and repaint
            image.setRGB(0, 0, width, height, rgbBuffer, 0, width);
            videoSurface.repaint();
        }
    }
}
* */