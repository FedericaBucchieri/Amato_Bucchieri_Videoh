package ProfessorHomePage;

import Utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AddVideoFormView {
    //The main panel that contains all the element of the view of this AddVideoForm scene
    private JPanel mainPanel;
    //The description to add to the new video
    private JTextArea descriptionField;
    //The title of the video to add
    private JTextField titleField;
    //The button open a file chooser to add a video
    private JButton uploadVideoButton;
    //The button open a file chooser to add a preview to the video
    private JButton uploadImageButton;
    //The label that shows the name of the selected video
    private JLabel uploadVideoName;
    //The label that shows the name of the selected preview image
    private JLabel uploadImageName;
    //The button that confirm the adding process
    private JButton addVideoButton;
    //The button to go back to the ProfessorHomePage
    private JButton backButton;
    //The label that shows error messages when these occur
    private JLabel errorLabel;
    //The file of the new Video
    private File newVideoFile;
    //The file of the new preview image
    private File newImageFile;
    //The AddVideoForm component
    private AddVideoForm controller;

    /**
     * Creates the view for the given AddVideoForm component
     * @param controller the AddVideoForm component
     */
    public AddVideoFormView(AddVideoForm controller) {
        this.controller = controller;

        setupUI();

        uploadVideoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                
                FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Video files", "mp4");
                fileChooser.setFileFilter(imageFilter);

                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(mainPanel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    newVideoFile = fileChooser.getSelectedFile();
                    uploadVideoName.setText("File Selected: " + newVideoFile.getName());
                }
            }
        });

        uploadImageButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();

                FileNameExtensionFilter imageFilter = new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
                fileChooser.setFileFilter(imageFilter);

                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(mainPanel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    newImageFile = fileChooser.getSelectedFile();
                    uploadImageName.setText("File Selected: " + newImageFile.getName());
                }
            }
        });

        addVideoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleNewVideoRequest(titleField.getText(), descriptionField.getText(), newImageFile, newVideoFile);
            }
        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleCancelRequest();
            }
        });
    }

    /**
     * Sets the layout of the main panel and creates this latter
     */
    private void setupUI(){
        this.mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        setupTitle();
        setupForm();
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        setupImageUpload();
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        setupVideoUpload();
        setupErrorLabel();
        setupButtons();

        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
    }

    /**
     * Creates the Title label on the top side of the main pane
     */
    private void setupTitle(){
        mainPanel.add(Box.createVerticalStrut(Utils.TITLE_MARGIN));
        JLabel title = new JLabel("Add new Video");
        title.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, Utils.TITLE_WIDTH));
        title.setForeground(Color.decode("#314668"));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
    }

    /**
     * Sets the aspect of the addVideoButton and the backButton. Both of them are then added to the main panel
     */
    private void setupButtons(){
        Utils utils = new Utils();

        addVideoButton = new JButton("Add Video");
        addVideoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addVideoButton = utils.styleButtonOne(addVideoButton);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        mainPanel.add(addVideoButton);

        backButton = utils.setUPBackButton();
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(backButton);

    }

    /**
     * Sets up the form where the professor type all the video parameters, that are the title and the description,
     */
    private void setupForm(){

        JLabel title = new JLabel("Title");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleField = new JTextField();
        titleField.setMaximumSize(new Dimension(Utils.STANDARD_SMALL_TEXT_FIELD_WIDTH, Utils.STANDARD_TEXT_FIELD_HEIGHT));
        titleField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);
        mainPanel.add(titleField);

        JLabel description = new JLabel("Description");
        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionField = new JTextArea();
        descriptionField.setWrapStyleWord(true);
        descriptionField.setLineWrap(true);
        descriptionField.setMaximumSize(new Dimension(Utils.STANDARD_SMALL_TEXT_FIELD_WIDTH, Utils.STANDARD_TEXTAREA_FIELD_HEIGHT));
        descriptionField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(description);
        mainPanel.add(descriptionField);

    }

    /**
     * Sets up the UploadImage button.
     */
    private void setupImageUpload(){
        Utils utils = new Utils();
        uploadImageButton = new JButton("Browse Preview Image");
        uploadImageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        uploadImageButton = utils.styleButtonTwo(uploadImageButton);
        mainPanel.add(uploadImageButton);

        uploadImageName = new JLabel();
        uploadImageName.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(uploadImageName);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
    }

    /**
     * Sets up the uploadVideo button.
     */
    private void setupVideoUpload(){
        Utils utils = new Utils();
        uploadVideoButton = new JButton("Browse Video");
        uploadVideoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        uploadVideoButton = utils.styleButtonTwo(uploadVideoButton);
        mainPanel.add(uploadVideoButton);

        uploadVideoName = new JLabel();
        uploadVideoName.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(uploadVideoName);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * This method updates the text of the errorLabel to display the student login error message
     */
    public void displayError(String error){
        errorLabel.setText(error);
    }

    /**
     * This method sets up the Error label with an empty text
     */
    private void setupErrorLabel(){
        errorLabel = new JLabel();
        errorLabel.setForeground(Color.decode("#DB2A58"));
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(errorLabel);
    }
}
