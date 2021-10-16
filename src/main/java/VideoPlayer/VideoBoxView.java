package VideoPlayer;

import Dialogues.InfoDialog;
import EventManagement.InteractionPanelCreatedEvent;
import EventManagement.Listener;
import InteractionList.InteractionPanel;
import Utils.Utils;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class VideoBoxView {
    //The VideoBox element
    private VideoBox controller;
    //The panel that contains the video surface and the southPanel
    private JPanel mainPanel;
    //The video surface is the actual panel in which the video is played.
    private JPanel videoSurface;
    //The panel with the controll buttons of the video: play/pause, inforButton, the slider for the timeline.
    private JPanel controllButtonsPanel;
    //The button to play and pause the video
    private JButton pausePlayButton;
    //The button to show info related to the video
    private JButton infoButton;
    //The slider to interact with the video timeline.
    private JSlider slider;
    //The panel that collects all the interactions and questions that the student can post.
    private InteractionPanel interactionPanel;
    //The video status: playing or not playing
    private boolean isPlaying = false;
    //The video timeline status: setted or not setted
    private boolean isTimeSetted = false;
    //The southPanel that contains the interactions and the controlls
    private JPanel southPanel;
    //The BufferedImage that will host the frame of the video to be displayed and update as the video is played.
    private BufferedImage image;
    //The actual VLCj media player component that expose into Swing the VLCj API to controll and create the video element.
    private DirectMediaPlayerComponent mediaPlayerComponent;

    private ArrayList<Listener> listeners = new ArrayList<Listener>();


    /**
     * This will build the VideoBoxView. It will contains the actual VideoPanel, the controlls button, the info button, the slider and the interaction panel. This constructor also add
     * controller as a listener. The string parameter is actually unused: its purpose is just to change the signature of the constructor in order to differentiate this one (that add the
     * controller as a listener) and the other one.
     * @param controller a VideoBox. It'll be added to list of listeners
     * @param unused: string of whatever velue not actually used. It's added just to change the signature of the Constructor.
     */
    public VideoBoxView(VideoBox controller, String unused) {
        this.controller = controller;
        this.mainPanel = new JPanel();
        this.listeners.add(controller); //this is the difference with the other constructor

        setupMainPanel();
        setupImage();
        setupVideoTitle();
        setupVideoSurface();

        mediaPlayerComponent.getMediaPlayer().playMedia(this.controller.getModel().getVideo().getFile().getPath());
        isPlaying = true;

        setupSouthPanel();



    }

    /**
     * This creates the view of the videoBox. It will contains the actual VideoPanel, the controlls button, the info button, the slider and the interaction panel.
     * @param controller
     */
    public VideoBoxView(VideoBox controller) {
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

    /**
     * This method sets up the south panel, that is the panel with all the controls of the video, the slider, the info button and the interactions panel.
     *
     */
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

    /**
     * Sets the playButton aspect, adding the actionListener to is, adding it to controllButtonsPanel.
     */
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
                togglePausePlayButton();
            }
        });
    }

    /**
     * Takes the mediaPlayer of the videoBox in order to obtain the video duration, and the width of the videoSurface. This will be necessary to set the slider to the proper dimension and to link it to the actual timeline of the video.
     */
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
                    setupInteractionPanel();
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

    /**
     * This method sets up the infoButton. The infoButton will create a new DialogBox shown to the user as a JDialog in order
     * to display some info related to the video itself.
     */
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

    /**
     * This method creates the panel with all the interaction. After the creation, it'll dispatch an InteractionPanelCreatedEvent to the above listeners.
     */
    private void setupInteractionPanel(){
        interactionPanel = new InteractionPanel(controller);
        southPanel.add(interactionPanel.getMainPanel());
        dispatchInteractionPanelCreated();
    }

    /**
     * Dispatch to the above listener an InteractionPanelCreatedEvent in order to notify them about the copmletion of the creation of the interaction panel.
     */
    private void dispatchInteractionPanelCreated() {
        for (Listener listener: listeners){
            listener.listen(new InteractionPanelCreatedEvent());
        }
    }

    /**
     * This method takes the VideoTitle from the database and display it on top of the videoSurface.
     */
    private void setupVideoTitle(){
        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(Color.white);
        JLabel title = new JLabel(controller.getModel().getVideo().getTitle());
        title.setFont(new Font(Font.SANS_SERIF, Font.BOLD, Utils.SUBTITLE_WIDTH));
        titlePanel.add(title);
        mainPanel.add(titlePanel, BorderLayout.NORTH);
    }

    /**
     * Creates the videoSurfacePanel that will show the video.
     * It will also add some videoListeners to the video.
     */
    private void setupVideoSurface() {
        videoSurface = new VideoSurfacePanel();
        setupVideoListeners();
        mainPanel.add(videoSurface, BorderLayout.CENTER);
    }

    /**
     * This method set the single click on the videoSurface as a Mouse listener that will toggle the playPauseButton.
     */
    private void setupVideoListeners() {
        videoSurface.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                togglePausePlayButton();
                super.mouseClicked(e);
            }
        });
    }

    /**
     * This method ask the mediaPLayerComponent to pause or play the video depending on its current status. The current
     * status is updated accordingly.
     * It also re-arrange the view in order to be consistent with the status of the video: the pause button is
     * turned into a play button and viceversa.
     */
    private void togglePausePlayButton() {
        if (isPlaying){
            mediaPlayerComponent.getMediaPlayer().pause();
            isPlaying = false;
            pausePlayButton.setIcon(new ImageIcon(new ImageIcon("src/main/images/play.png").getImage().getScaledInstance(Utils.PLAY_PAUSE_SIZE, Utils.PLAY_PAUSE_SIZE, Image.SCALE_SMOOTH)));

        }
        else{
            mediaPlayerComponent.getMediaPlayer().play();
            isPlaying = true;
            pausePlayButton.setIcon(new ImageIcon(new ImageIcon("src/main/images/pause.png").getImage().getScaledInstance(Utils.PLAY_PAUSE_SIZE, Utils.PLAY_PAUSE_SIZE, Image.SCALE_SMOOTH)));
        }

    }

    /**
     * This method setup the mainPanel of the videoBox, that is the panel that contains the actual videoPanel TODO forse no, vedi bene
     */
    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.black);
        mainPanel.setPreferredSize(new Dimension(controller.getModel().getWidth(), controller.getModel().getHeight()));
//        setupImage();
    }

    /**
     * This method will pause the video.
     */
    public void freezeVideo(){
        togglePausePlayButton();
        pausePlayButton.setEnabled(false);
    }

    /**
     * This method will play the video after it's been frozen.
     */
    public void restartVideoAfterFreeze(){
        togglePausePlayButton();
        pausePlayButton.setEnabled(true);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * The main function of the video box. This will build the videoPanel by rendering the video file
     * and extracting from it the array of frames the video is made of. This is not the usual way to embed a videoPlayer
     * in a JAVA application: usually an EmbeddedMediaPlayer from the vlcj library has to be implemented to play video. Since we used
     * a JAVA version later than 1.6 on MacOS there is no heavyweight AWT toolkit that can manage the EmbeddedMediaPlayer. That means that
     * via the vlcj this method will create a video buffer (the bufferedImage file) and then it'll render it on screen.
     * The code this func is taken and inspired from the official tutorial of the caprica vlcj website
     * (https://capricasoftware.co.uk/projects/vlcj-3/tutorials/direct-rendering)
     *
     */
    private void setupImage() {
        image = GraphicsEnvironment
                .getLocalGraphicsEnvironment()
                .getDefaultScreenDevice()
                .getDefaultConfiguration()
                .createCompatibleImage(controller.getModel().getWidth() , controller.getModel().getHeight());
        BufferFormatCallback bufferFormatCallback = new BufferFormatCallback() {
            //this will set the format for the native DirectMediaPlayerComponent. the RV32 format has been selected, with a fixed sized extracted from the width and height saved in the model.
            @Override
            public BufferFormat getBufferFormat(int sourceWidth, int sourceHeight) {
                return new RV32BufferFormat(controller.getModel().getWidth(), controller.getModel().getHeight());
            }
        };
        //The DirectMediaPlayerComponent will be created with the format selected above.
        mediaPlayerComponent = new DirectMediaPlayerComponent(bufferFormatCallback) {
            @Override
            protected RenderCallback onGetRenderCallback() {
                return new VideoRenderCallbackAdapter();
            }
        };


    }


    /**
     * This method will release the video detaching it from the mediaPLayerComponent.
     */
    public void dismissVideo(){
        this.mediaPlayerComponent.release();
    }

    public JSlider getSlider() {
        return slider;
    }


    private class VideoRenderCallbackAdapter extends RenderCallbackAdapter {

        /**
         * This private class will create a RenderCallbackAdapter adapter that will preallocate in its constructor a buffer that is large enough to hold a single frame of videos.

         */
        private VideoRenderCallbackAdapter() {
            super(new int[controller.getModel().getWidth() * controller.getModel().getHeight()]);
        }

        /**
         * The onDisplay is called after all frame is added to the bufferedImage and will repaint the video surface.
         * It simply copy the buffer of the RenderCallBack to the bufferedImage and repaint. The paint component of the videoSurface is instead overwritten in order to just draw the bufferedImage
         * @param mediaPlayer
         * @param rgbBuffer
         */
        @Override
        protected void onDisplay(DirectMediaPlayer mediaPlayer, int[] rgbBuffer) {

            image.setRGB(0, 0, controller.getModel().getWidth(), controller.getModel().getHeight(), rgbBuffer, 0, controller.getModel().getWidth());
            videoSurface.repaint();
        }
    }

    /**
     * Implementation of a JPanel with specific caracteristics taken from the videoBoxModel.
     */
    private class VideoSurfacePanel extends JPanel {

        private VideoSurfacePanel() {
//            setBackground(Color.GREEN);
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

    public JPanel getSouthPanel() {
        return southPanel;
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



}
