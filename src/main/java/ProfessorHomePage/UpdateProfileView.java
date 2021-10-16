package ProfessorHomePage;

import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateProfileView {
    //The UpdateProfile component
    private UpdateProfile controller;
    //The panel that contains all the element
    private JPanel mainPanel;
    //The button to confirm the update
    private JButton updateButton;
    //The button to go back to the previous scene
    private JButton backButton;
    //The textfield to set the new username
    private JTextField usernameField;
    //The textfield to set the new password
    private JTextField passwordField;
    private JLabel errorLabel;//TODO da eliminare?
    //The fancy logo of the UpdateProfile scene
    private JLabel logo;

    /**
     * Creates a view for the UpdateProfile component
     * @param controller the UpdateProfile component
     */
    public UpdateProfileView(UpdateProfile controller) {
        this.controller = controller;

        this.mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        setupTitle();
        setupSettingsLogo();
        setupForm();
        setupButtons();
    }

    public void installUI(){
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.goBackToVideoList();
            }
        });

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleUpdateProfileRequest(usernameField.getText(), passwordField.getText());
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    /**
     * Setup the title of the page and position iit on top of the main panel
     */
    private void setupTitle(){
        mainPanel.add(Box.createVerticalStrut(Utils.TITLE_MARGIN));
        JLabel title = new JLabel("Update your profile");
        title.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, Utils.TITLE_WIDTH));
        title.setForeground(Color.decode("#314668"));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
    }


    /**
     * Set the text field of the new username and the new password and adds them to the main panel.
     */
    private void setupForm(){

        JLabel username = new JLabel("Username");
        username.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameField = new JTextField(controller.getUsername());
        usernameField.setMaximumSize(new Dimension(Utils.STANDARD_SMALL_TEXT_FIELD_WIDTH, Utils.STANDARD_TEXT_FIELD_HEIGHT));
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(username);
        mainPanel.add(usernameField);

        JLabel pass = new JLabel("Password");
        pass.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField = new JTextField(controller.getPassword());
        passwordField.setMaximumSize(new Dimension(Utils.STANDARD_SMALL_TEXT_FIELD_WIDTH, Utils.STANDARD_TEXT_FIELD_HEIGHT));
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(pass);
        mainPanel.add(passwordField);

    }

    /**
     * Sets the updateButton and the back button
     */
    private void setupButtons(){
        Utils utils = new Utils();

        updateButton = new JButton("Update profile");
        updateButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateButton = utils.styleButtonOne(updateButton);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        mainPanel.add(updateButton);

        backButton = utils.setUPBackButton();
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(backButton);
    }

    /**
     * Sets the fancy logo of the UpdateProfile scene
     */
    private void setupSettingsLogo() {
        logo = new JLabel();
        logo.setIcon(new ImageIcon(new ImageIcon("src/main/images/settings.png").getImage().getScaledInstance(Utils.BIG_LOGO_SIZE, Utils.BIG_LOGO_SIZE, Image.SCALE_SMOOTH)));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        mainPanel.add(logo);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));

    }

}
