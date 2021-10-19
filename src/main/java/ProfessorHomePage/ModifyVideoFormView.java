package ProfessorHomePage;

import Utils.Utils;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class ModifyVideoFormView {
    //The ModifyVideoForm component
    private ModifyVideoForm controller;
    //The main panel that will contain all the elements of this scene
    private JPanel mainPanel;
    //The text field to set the new title
    private JTextField titleField;
    //The text field to set the new description
    private JTextArea descriptionField;
    //The button to open a file chooser to choose a new video
    private JButton uploadVideoButton;
    //The button to open a file chooser to choose a new previewimage
    private JButton uploadImageButton;
    //The label that shows the name of the new videofile  uploaded
    private JLabel uploadVideoName;
    //The label that shows the name of the new previewImageFile  uploaded
    private JLabel uploadImageName;
    //The button to confirm the changes
    private JButton modifyButton;
    //The button to go back cancelling the changes
    private JButton backButton;
    //The label that shows error messages when these occur
    private JLabel errorLabel;
    //The  file containing the new video
    private File newVideoFile;
    //The  file containing the new previewImage
    private File newImageFile;

    /**
     * Creates a new view for the ModifyVideoForm component
     * @param controller the ModifyVideoForm component
     */
    public ModifyVideoFormView(ModifyVideoForm controller) {
        this.controller = controller;
        setupUI();
    }

    /**
     * Sets the action listener of the uploadVideoButton, uploadImageButton, modifyButton and backButton.
     */
    public void installUI(){
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

        modifyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleModifyRequest(titleField.getText(), descriptionField.getText(), newImageFile, newVideoFile);
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
     * Creates a new mainPanel with all the element to show in screen
     */
    private void setupUI(){
        mainPanel = new JPanel();
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
     * Sets the title of the video in its label and add it to the main panel
     */
    private void setupTitle(){
        mainPanel.add(Box.createVerticalStrut(Utils.TITLE_MARGIN));
        JLabel title = new JLabel("Modify video '" + controller.getModel().getVideo().getTitle() + "'");
        title.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, Utils.TITLE_WIDTH));
        title.setForeground(Utils.CUSTOM_BLUE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
    }

    /**
     * Sets the text field to modify the description and the title of the video and add them to the main panel. Sets the
     * Title label and the Description label
     */
    private void setupForm(){

        JLabel title = new JLabel("Title");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        titleField = new JTextField(controller.getModel().getVideo().getTitle());
        titleField.setMaximumSize(new Dimension(Utils.STANDARD_SMALL_TEXT_FIELD_WIDTH, Utils.STANDARD_TEXT_FIELD_HEIGHT));
        titleField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);
        mainPanel.add(titleField);

        JLabel description = new JLabel("Description");
        description.setAlignmentX(Component.CENTER_ALIGNMENT);
        descriptionField = new JTextArea(controller.getModel().getVideo().getDescription());
        descriptionField.setWrapStyleWord(true);
        descriptionField.setLineWrap(true);
        descriptionField.setMaximumSize(new Dimension(Utils.STANDARD_SMALL_TEXT_FIELD_WIDTH, Utils.STANDARD_TEXTAREA_FIELD_HEIGHT));
        descriptionField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(description);
        mainPanel.add(descriptionField);

    }

    /**
     * Sets the aspect of the uploadImageButton and the uploadImageName label and add them to the main panel
     */
    private void setupImageUpload(){
        Utils utils = new Utils();
        uploadImageButton = new JButton("Browse Preview Image");
        uploadImageButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        uploadImageButton = utils.styleButtonTwo(uploadImageButton);
        mainPanel.add(uploadImageButton);

        uploadImageName = new JLabel(controller.getModel().getVideo().getPreviewImage().getName());
        uploadImageName.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(uploadImageName);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
    }

    /**
     * Sets the aspect of the uploadVideoButton and the uploadVideoName label and add them to the main panel
     */
    private void setupVideoUpload(){
        Utils utils = new Utils();
        uploadVideoButton = new JButton("Browse Video");
        uploadVideoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        uploadVideoButton = utils.styleButtonTwo(uploadVideoButton);
        mainPanel.add(uploadVideoButton);

        uploadVideoName = new JLabel(controller.getModel().getVideo().getFile().getName());
        uploadVideoName.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(uploadVideoName);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Sets the aspect of the modifyButton and backButton and add them to the main panel
     */
    private void setupButtons(){
        Utils utils = new Utils();

        modifyButton = new JButton("Modify Video");
        modifyButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        modifyButton = utils.styleButtonOne(modifyButton);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        mainPanel.add(modifyButton);

        backButton = utils.setUPBackButton();
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(backButton);

    }

    /**
     * This method sets up the Error label with an empty text
     */
    private void setupErrorLabel(){
        errorLabel = new JLabel();
        errorLabel.setForeground(Utils.CUSTOM_VIOLET2);
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(errorLabel);
    }

    /**
     * This method updates the text of the errorLabel to display the student login error message
     */
    public void displayError(String error){
        errorLabel.setText(error);
    }

}
