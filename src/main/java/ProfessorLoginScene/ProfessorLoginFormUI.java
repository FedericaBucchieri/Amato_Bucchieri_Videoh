package ProfessorLoginScene;

import sceneManager.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProfessorLoginFormUI {
    private JPanel mainPanel;
    private JTextField usernameField;
    private JLabel profLogo;
    private JButton loginButton;
    private JPasswordField passwordField;
    private JLabel errorLabel;
    private ProfessorLoginForm controller;
    private JButton backButton;

    public ProfessorLoginFormUI(){
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(Color.decode("#42577F"));

        setupTitleLabel();
        setupProfLogo();
        setupForm();
        setupBackButton();
    }

    private void setupForm(){
        setupNameTextField();
        setupPassTextField();
        setupErrorLabel();
        setupLoginButt();
    }

    private void setupTitleLabel() {
        mainPanel.add(Box.createVerticalStrut(Utils.TITLE_MARGIN));
        JLabel titleLabel = new JLabel("Professor Login");

        titleLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, Utils.TITLE_WIDTH));
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(Utils.TITLE_MARGIN));
    }

    private void setupProfLogo() {
        profLogo = new JLabel();
        profLogo.setIcon(new ImageIcon(new ImageIcon("src/main/images/professor.png").getImage().getScaledInstance(Utils.BIG_LOGO_SIZE, Utils.BIG_LOGO_SIZE, Image.SCALE_SMOOTH)));
        profLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        mainPanel.add(profLogo);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
    }

    private void setupNameTextField(){
        JLabel label = new JLabel("Username");
        label.setForeground(Color.white);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(label);

        usernameField = new JTextField("insert your username");
        usernameField.setAlignmentX(Component.CENTER_ALIGNMENT);
        usernameField.setPreferredSize(new Dimension(Utils.STANDARD_SMALL_TEXT_FIELD_WIDTH, Utils.STANDARD_TEXT_FIELD_HEIGHT));
        usernameField.setMaximumSize(usernameField.getPreferredSize());
        usernameField.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(usernameField);

    }

    private void setupPassTextField(){
        JLabel label = new JLabel("Password");
        label.setForeground(Color.white);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(label);

        passwordField = new JPasswordField("Password");
        passwordField.setAlignmentX(Component.CENTER_ALIGNMENT);
        passwordField.setPreferredSize(new Dimension(Utils.STANDARD_SMALL_TEXT_FIELD_WIDTH, Utils.STANDARD_TEXT_FIELD_HEIGHT));
        passwordField.setMaximumSize(passwordField.getPreferredSize());
        passwordField.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(passwordField);
    }

    private void setupLoginButt() {
        loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        Utils utils = new Utils();
        loginButton = utils.styleButtonTwo(loginButton);

        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        mainPanel.add(loginButton);
    }

    private void setupErrorLabel(){
        errorLabel = new JLabel();
        errorLabel.setForeground(Color.decode("#DB2A58"));
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(errorLabel);
    }

    private void setupBackButton(){
        Utils utils = new Utils();
        backButton = utils.setUPBackButton();

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(backButton);
    }

    public void installUI(ProfessorLoginForm controller){
        this.controller = controller;

        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(usernameField.getText() != null && passwordField.getPassword() != null)
                    controller.checkCredential(usernameField.getText(), passwordToString(passwordField.getPassword()));
            }
        });


        usernameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if(usernameField.getText() != null && passwordField.getPassword() != null)
                        controller.checkCredential(usernameField.getText(), passwordToString(passwordField.getPassword()));
                }
            }
        });

        passwordField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    if (usernameField.getText() != null && passwordField.getPassword() != null)
                        controller.checkCredential(usernameField.getText(), passwordToString(passwordField.getPassword()));
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.goBackToGeneralLogin();
            }
        });

        usernameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                usernameField.setText("");
            }
        });

        passwordField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                passwordField.setText("");
            }
        });

    }

    private String passwordToString(char[] password){
        String newPass = "";
        for (char c: password) {
            newPass += c;
        }
        return newPass;
    }

    public void displayError(String error){
        errorLabel.setText(error);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
