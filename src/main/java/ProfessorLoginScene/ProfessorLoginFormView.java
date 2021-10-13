package ProfessorLoginScene;

import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ProfessorLoginFormView {
    // the Component controller
    private ProfessorLoginForm controller;
    // the panel that container every element of the component view
    private JPanel mainPanel;
    // the text field for inserting the professor username
    private JTextField usernameField;
    // A label that contains the main logo of the scene
    private JLabel profLogo;
    // the button to login
    private JButton loginButton;
    // the password field for inserting the professor password
    private JPasswordField passwordField;
    // a label to display login errors
    private JLabel errorLabel;
    // the button to go back to the previous scene
    private JButton backButton;

    /**
     * This constructor creates an instance of ProfessorLoginFormUI, adding all the UI element required to the mainPanel
     */
    public ProfessorLoginFormView(){
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(Color.decode("#42577F"));

        setupTitleLabel();
        setupProfLogo();
        setupForm();
        setupBackButton();
    }

    /**
     * This method setups the professor login Form
     */
    private void setupForm(){
        setupNameTextField();
        setupPassTextField();
        setupErrorLabel();
        setupLoginButt();
    }

    /**
     * This method creates a JLabel for the title of the scene
     */
    private void setupTitleLabel() {
        mainPanel.add(Box.createVerticalStrut(Utils.TITLE_MARGIN));
        JLabel titleLabel = new JLabel("Professor Login");

        titleLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, Utils.TITLE_WIDTH));
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(Utils.TITLE_MARGIN));
    }

    /**
     * This method creates a JLabel for the logo of the scene
     */
    private void setupProfLogo() {
        profLogo = new JLabel();
        profLogo.setIcon(new ImageIcon(new ImageIcon("src/main/images/professor.png").getImage().getScaledInstance(Utils.BIG_LOGO_SIZE, Utils.BIG_LOGO_SIZE, Image.SCALE_SMOOTH)));
        profLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        mainPanel.add(profLogo);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
    }

    /**
     * This method sets up the Username Text Field required to log in as a professor
     */
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

    /**
     * This method sets up the Password Text Field required to log in as a professor
     */
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

    /**
     * This method sets up the loginButton
     */
    private void setupLoginButt() {
        loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        Utils utils = new Utils();
        loginButton = utils.styleButtonTwo(loginButton);

        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        mainPanel.add(loginButton);
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

    /**
     * This method sets up the BackButton
     */
    private void setupBackButton(){
        Utils utils = new Utils();
        backButton = utils.setUPBackButton();

        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(backButton);
    }

    /**
     * This method installs the UI, setting up all the required actionListeners for each element of the UI
     */
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

    /**
     * This method formats correctly the password to be passed as a String
     * @param password The charset of characters that compose the password
     * @return the password as a String
     */
    private String passwordToString(char[] password){
        String newPass = "";
        for (char c: password) {
            newPass += c;
        }
        return newPass;
    }

    /**
     * This method updates the text of the errorLabel to display the student login error message
     */
    public void displayError(String error){
        errorLabel.setText(error);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
