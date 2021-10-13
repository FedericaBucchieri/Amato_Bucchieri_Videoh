package ProfessorHomePage;

import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UpdateProfileUI {
    private UpdateProfile controller;
    private JPanel mainPanel;
    private JButton updateButton;
    private JButton backButton;
    private JTextField usernameField;
    private JTextField passwordField;
    private JLabel errorLabel;
    private JLabel logo;

    public UpdateProfileUI(UpdateProfile controller) {
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

    private void setupTitle(){
        mainPanel.add(Box.createVerticalStrut(Utils.TITLE_MARGIN));
        JLabel title = new JLabel("Update your profile");
        title.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, Utils.TITLE_WIDTH));
        title.setForeground(Color.decode("#314668"));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(title);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
    }

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

    private void setupSettingsLogo() {
        logo = new JLabel();
        logo.setIcon(new ImageIcon(new ImageIcon("src/main/images/settings.png").getImage().getScaledInstance(Utils.BIG_LOGO_SIZE, Utils.BIG_LOGO_SIZE, Image.SCALE_SMOOTH)));
        logo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        mainPanel.add(logo);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));

    }

}
