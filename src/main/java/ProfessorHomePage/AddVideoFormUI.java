package ProfessorHomePage;

import sceneManager.Utils;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class AddVideoFormUI {
    private JPanel mainPanel;
    private JTextField previewImageField;
    private JTextField descriptionField;
    private JTextField titleField;
    private JButton uploadVideoButton;
    private JLabel uploadVideoName;
    private JButton addVideoButton;
    private JButton backButton;
    private JLabel errorLabel;
    private File newVideoFile;
    private AddVideoForm controller;

    public AddVideoFormUI(AddVideoForm controller) {
        this.controller = controller;

        setupUI();

        uploadVideoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
                int result = fileChooser.showOpenDialog(mainPanel);
                if (result == JFileChooser.APPROVE_OPTION) {
                    newVideoFile = fileChooser.getSelectedFile();
                    uploadVideoName.setText("File Selected: " + newVideoFile.getName());
                }
            }
        });

        addVideoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleNewVideoRequest(titleField.getText(), descriptionField.getText(), previewImageField.getText(), newVideoFile);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleCancelRequest();
            }
        });
    }

    private void setupUI(){
        this.mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        setupTitle();
        setupForm();
        setupVideoUpload();
        setupButtons();

        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));

    }

    private void setupTitle(){
        mainPanel.add(Box.createVerticalStrut(Utils.TITLE_MARGIN));
        JLabel title = new JLabel("Add new Video");
        title.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, Utils.TITLE_WIDTH));
        title.setForeground(Color.decode("#314668"));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));

    }

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
        descriptionField = new JTextField();
        descriptionField.setMaximumSize(new Dimension(Utils.STANDARD_SMALL_TEXT_FIELD_WIDTH, Utils.STANDARD_TEXT_FIELD_HEIGHT));
        descriptionField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(description);
        mainPanel.add(descriptionField);

        JLabel preview = new JLabel("Preview Image");
        preview.setAlignmentX(Component.CENTER_ALIGNMENT);
        previewImageField = new JTextField();
        previewImageField.setMaximumSize(new Dimension(Utils.STANDARD_SMALL_TEXT_FIELD_WIDTH, Utils.STANDARD_TEXT_FIELD_HEIGHT));
        previewImageField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(preview);
        mainPanel.add(previewImageField);
    }

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
}
