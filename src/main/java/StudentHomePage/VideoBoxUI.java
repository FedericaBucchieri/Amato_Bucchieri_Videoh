package StudentHomePage;

import uk.co.caprica.vlcj.component.DirectMediaPlayerComponent;
import uk.co.caprica.vlcj.player.direct.*;
import uk.co.caprica.vlcj.player.direct.format.RV32BufferFormat;

import javax.print.attribute.standard.Media;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

public class VideoBoxUI {
    private VideoBox controller;



    private JPanel mainPanel;
    private JPanel videoSurface;
    private JPanel controllButtonsPanel;
    private JButton pausePlayButton;
    private boolean isPlaying = false;

    private BufferedImage image;
    private DirectMediaPlayerComponent mediaPlayerComponent;


    public VideoBoxUI(VideoBox controller) {
        this.controller = controller;
        this.mainPanel = new JPanel();

        setupMainPanel();
        setupImage();
        setupVideoSurface();
        setupControllButtonsPanel();

        //TODO: raggruppa tutto in una funzione.
       mediaPlayerComponent.getMediaPlayer().playMedia(this.controller.getModel().getFile().getPath());
       isPlaying = true;


    }

    private void setupControllButtonsPanel() {
        controllButtonsPanel = new JPanel();
        controllButtonsPanel.setLayout(new BoxLayout(controllButtonsPanel, BoxLayout.Y_AXIS));
        mainPanel.add(controllButtonsPanel, BorderLayout.SOUTH);


        setupPlayButton();


    }

    private void setupPlayButton() {
        pausePlayButton = new JButton("PAUSE / PLAY");
        pausePlayButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        controllButtonsPanel.add(pausePlayButton);

        pausePlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isPlaying){
                    mediaPlayerComponent.getMediaPlayer().pause();
                    isPlaying = false;
                    System.out.println("PAUSED AT: ");
                    System.out.println(mediaPlayerComponent.getMediaPlayer().getTime());
                }else{
                    mediaPlayerComponent.getMediaPlayer().play();
                    isPlaying = true;
                }

            }
        });
    }

    private void setupVideoSurface() {
        videoSurface = new VideoSurfacePanel();
        mainPanel.add(videoSurface, BorderLayout.CENTER);
    }

    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        setupImage();
        //mediaPlayerComponent.getMediaPlayer().playMedia(this.controller.getModel().getFile().getPath());
        //isPlaying = true;

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void setupImage() {
        image = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration()
                .createCompatibleImage(controller.getModel().getWidth() , controller.getModel().getHeight());
        BufferFormatCallback bufferFormatCallback = new BufferFormatCallback() {
            @Override
            public BufferFormat getBufferFormat(int sourceWidth, int sourceHeight) {
                return new RV32BufferFormat(controller.getModel().getWidth(), controller.getModel().getHeight());
            }
        };
        mediaPlayerComponent = new DirectMediaPlayerComponent(bufferFormatCallback) {
            @Override
            protected RenderCallback onGetRenderCallback() {
                return new TutorialRenderCallbackAdapter();
            }
        };


    }

    public void dismissVideo(){
        this.mediaPlayerComponent.release();
    }


    private class TutorialRenderCallbackAdapter extends RenderCallbackAdapter {

        private TutorialRenderCallbackAdapter() {
            //TO-DO: remove magic number
            super(new int[1200 * 800]);
        }

        @Override
        protected void onDisplay(DirectMediaPlayer mediaPlayer, int[] rgbBuffer) {
            // Simply copy buffer to the image and repaint
            //TO-DO: remove magic number
            image.setRGB(0, 0, 1200, 800, rgbBuffer, 0, 1200);
            videoSurface.repaint();
        }
    }

    private class VideoSurfacePanel extends JPanel {

        private VideoSurfacePanel() {
            setBackground(Color.black);
            setOpaque(true);
            setPreferredSize(new Dimension(1200, 800));
            setMinimumSize(new Dimension(1200, 800));
            setMaximumSize(new Dimension(1200, 800));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
            g2.drawImage(image, null, 0, 0);
        }
    }

}
