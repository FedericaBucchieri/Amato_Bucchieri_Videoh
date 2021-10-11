package StudentHomePage;

import Dialogues.InfoDialog;
import sceneManager.Utils;
import uk.co.caprica.vlcj.component.DirectMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerEventAdapter;
import uk.co.caprica.vlcj.player.direct.*;
import uk.co.caprica.vlcj.player.direct.format.RV32BufferFormat;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
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
    private JButton infoButton;
    private JSlider slider;
    private InteractionPanel interactionPanel;
    private boolean isPlaying = false;
    private boolean isTimeSetted = false;

    public JPanel getSouthPanel() {
        return southPanel;
    }
    public JPanel getVideoSurface() {
        return videoSurface;
    }


    private JPanel southPanel;
    private BufferedImage image;
    private DirectMediaPlayerComponent mediaPlayerComponent;

    public VideoBoxUI(VideoBox controller, String unused) {//this constructor is used when the videobox is loaded by statistics pane
        this.controller = controller;
        this.mainPanel = new JPanel();

//        interactionPanel = new InteractionPanel(controller);
        setupMainPanel();
        setupImage();
        setupVideoSurface();

        mediaPlayerComponent.getMediaPlayer().playMedia(this.controller.getModel().getVideo().getFile().getPath());
        isPlaying = true;

        setupSouthPanel();



    }

    public VideoBoxUI(VideoBox controller) {
        this.controller = controller;
        this.mainPanel = new JPanel();

        setupMainPanel();
        setupImage();
        setupVideoTitle();
        setupVideoSurface();

        mediaPlayerComponent.getMediaPlayer().playMedia(this.controller.getModel().getVideo().getFile().getPath());
        isPlaying = true;

        setupSouthPanel();

    }

    private void setupSouthPanel(){
        southPanel = new JPanel();
        southPanel.setLayout(new BoxLayout(southPanel, BoxLayout.PAGE_AXIS));
        mainPanel.add(southPanel, BorderLayout.SOUTH);

        setupControllButtonsPanel();
    }

    private void setupControllButtonsPanel() {
        controllButtonsPanel = new JPanel();
        controllButtonsPanel.setLayout(new BoxLayout(controllButtonsPanel, BoxLayout.X_AXIS));
        southPanel.add(controllButtonsPanel);

        setupPlayButton();
        setupTimeline();
        setupInfoButton();

    }

    private void setupPlayButton() {

        pausePlayButton = new JButton();
        pausePlayButton.setIcon(new ImageIcon(new ImageIcon("src/main/images/pause.png").getImage().getScaledInstance(Utils.PLAY_PAUSE_SIZE, Utils.PLAY_PAUSE_SIZE, Image.SCALE_SMOOTH)));
        pausePlayButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        pausePlayButton.setOpaque(true);
        pausePlayButton.setBorderPainted(false);
        controllButtonsPanel.add(pausePlayButton);

        pausePlayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(isPlaying){
                    mediaPlayerComponent.getMediaPlayer().pause();
                    isPlaying = false;
                    pausePlayButton.setIcon(new ImageIcon(new ImageIcon("src/main/images/play.png").getImage().getScaledInstance(Utils.PLAY_PAUSE_SIZE, Utils.PLAY_PAUSE_SIZE, Image.SCALE_SMOOTH)));
                }else{
                    pausePlayButton.setIcon(new ImageIcon(new ImageIcon("src/main/images/pause.png").getImage().getScaledInstance(Utils.PLAY_PAUSE_SIZE, Utils.PLAY_PAUSE_SIZE, Image.SCALE_SMOOTH)));
                    mediaPlayerComponent.getMediaPlayer().play();
                    isPlaying = true;
                }

            }
        });
    }

    private void setupTimeline(){
        MediaPlayer embededMediaPlayer = mediaPlayerComponent.getMediaPlayer();
        slider = new JSlider(0, (int)embededMediaPlayer.getLength(), 0);
        slider.setPreferredSize(new Dimension(controller.getModel().getWidth(), slider.getHeight()));
        JLabel currentTimeLabel = new JLabel();
        JLabel finalTimeLabel = new JLabel();

        embededMediaPlayer.addMediaPlayerEventListener(new MediaPlayerEventAdapter(){
            @Override
            public void timeChanged(MediaPlayer mediaPlayer, long newTime) {
                super.timeChanged(mediaPlayer, newTime);
                int time = (int)mediaPlayerComponent.getMediaPlayer().getTime();

                if(!isTimeSetted) {
                    slider.setMaximum((int) mediaPlayerComponent.getMediaPlayer().getLength());
                    finalTimeLabel.setText(Utils.formatTime(mediaPlayerComponent.getMediaPlayer().getLength()));
                    setupAnnotationPanel();
                    isTimeSetted = true;
                }

                if (!slider.getValueIsAdjusting()) {
                    slider.setValue(time);
                    currentTimeLabel.setText(Utils.formatTime(mediaPlayerComponent.getMediaPlayer().getTime()));
                }
            }
        });

        slider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                if (slider.getValueIsAdjusting()) {
                    long currentTime = mediaPlayerComponent.getMediaPlayer().getTime();
                    currentTimeLabel.setText(Utils.formatTime(mediaPlayerComponent.getMediaPlayer().getTime()));
                    mediaPlayerComponent.getMediaPlayer().skip(slider.getValue() - currentTime);
                }
            }
        });

        controllButtonsPanel.add(currentTimeLabel);
        controllButtonsPanel.add(slider);
        controllButtonsPanel.add(finalTimeLabel);
    }

    private void setupInfoButton(){
        infoButton = new JButton();
        infoButton.setIcon(new ImageIcon(new ImageIcon("src/main/images/information.png").getImage().getScaledInstance(Utils.PLAY_PAUSE_SIZE, Utils.PLAY_PAUSE_SIZE, Image.SCALE_SMOOTH)));
        infoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        infoButton.setOpaque(true);
        infoButton.setBorderPainted(false);
        controllButtonsPanel.add(infoButton);

        infoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    InfoDialog dialog = new InfoDialog(controller.getModel().getVideo());
                    dialog.setLocation(300,100);
                    dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private void setupAnnotationPanel(){
        interactionPanel = new InteractionPanel(controller);
        southPanel.add(interactionPanel.getMainPanel());
    }

    private void setupVideoTitle(){
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.white);
        JLabel title = new JLabel(controller.getModel().getVideo().getTitle());
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, Utils.SUBTITLE_WIDTH));
        titlePanel.add(title);
        mainPanel.add(titlePanel, BorderLayout.NORTH);
    }

    private void setupVideoSurface() {
        videoSurface = new VideoSurfacePanel();
        mainPanel.add(videoSurface, BorderLayout.CENTER);
    }

    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.black);
        mainPanel.setPreferredSize(new Dimension(controller.getModel().getWidth(), controller.getModel().getHeight()));
        setupImage();
    }

    public void freezeVideo(){
        if(isPlaying)
            mediaPlayerComponent.getMediaPlayer().pause();
        isPlaying = false;
        pausePlayButton.setIcon(new ImageIcon(new ImageIcon("src/main/images/play.png").getImage().getScaledInstance(Utils.PLAY_PAUSE_SIZE, Utils.PLAY_PAUSE_SIZE, Image.SCALE_SMOOTH)));
        pausePlayButton.setEnabled(false);
    }

    public void restartVideoAfterFreeze(){
        mediaPlayerComponent.getMediaPlayer().play();
        isPlaying = true;
        pausePlayButton.setIcon(new ImageIcon(new ImageIcon("src/main/images/pause.png").getImage().getScaledInstance(Utils.PLAY_PAUSE_SIZE, Utils.PLAY_PAUSE_SIZE, Image.SCALE_SMOOTH)));
        pausePlayButton.setEnabled(true);
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
                return new VideoRenderCallbackAdapter();
            }
        };


    }


    public void dismissVideo(){
        this.mediaPlayerComponent.release();
    }

    public JSlider getSlider() {
        return slider;
    }

    private class VideoRenderCallbackAdapter extends RenderCallbackAdapter {

        private VideoRenderCallbackAdapter() {
            super(new int[controller.getModel().getWidth() * controller.getModel().getHeight()]);
        }

        @Override
        protected void onDisplay(DirectMediaPlayer mediaPlayer, int[] rgbBuffer) {
            // Simply copy buffer to the image and repaint
            image.setRGB(0, 0, controller.getModel().getWidth(), controller.getModel().getHeight(), rgbBuffer, 0, controller.getModel().getWidth());
            videoSurface.repaint();
        }
    }

    private class VideoSurfacePanel extends JPanel {

        private VideoSurfacePanel() {
            setBackground(Color.GREEN);
            setOpaque(false);
            setPreferredSize(new Dimension(controller.getModel().getWidth(), controller.getModel().getHeight()));
            setMinimumSize(new Dimension(controller.getModel().getWidth(), controller.getModel().getHeight()));
            setMaximumSize(new Dimension(controller.getModel().getWidth(), controller.getModel().getHeight()));
        }

        @Override
        protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
            g2.drawImage(image, null, 0, 0);
        }
    }

    public JPanel getVideoSurface() {
        return videoSurface;
    }

    public JPanel getControllButtonsPanel() {
        return controllButtonsPanel;
    }
    public InteractionPanel getInteractionPanel() {
        return interactionPanel;
    }


    public JPanel getInteractionPanel() {
        return interactionPanel.getUi().getGeneralInteractionsPanel(controller.getVideoId());
    }

    public JPanel getSouthPanel() {
        return southPanel;
    }

}