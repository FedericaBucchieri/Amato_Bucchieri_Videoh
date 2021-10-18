package StudentLogin;

import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class StudentLoginPanelView {
    // the Component controller
    private StudentLoginPanel controller;
    // the panel that container every element of the component view
    private JPanel mainPanel;
    // the panel that container the login form
    private JPanel formPanel;
    // A label that contains the main logo of the scene
    private JLabel studLogo;
    // the text field for inserting the student username
    private JTextField nameTextField;
    // the button to login
    private JButton studLoginButt;
    // the button to go back to the previous scene
    private JButton backButton;
    // a label to display login errors
    private JLabel errorLabel;

    /**
     * This constructor creates an instance of StudentLoginPanelView, adding all the UI element required to the mainPanel
     * @param controller the StudentLoginPanel instance that is the component controller
     */
    public StudentLoginPanelView(StudentLoginPanel controller){
        this.controller = controller;

        setupMainPanel();
        setupTitleLabel();
        setupStudLogo();
        setupNameTextField();
        setupErrorLabel();
        setupLoginButt();
        setupBackButton();

    }

    /**
     * This method installs the UI, setting up all the required actionListeners for each element of the UI
     */
    public void installUI(){
        studLoginButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.checkUsername(nameTextField.getText());
            }
        });


        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.goBackToGeneralLogin();
            }
        });

        nameTextField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                nameTextField.setText("");
            }
        });

        nameTextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER){
                    controller.checkUsername(nameTextField.getText());
                }
            }
        });
    }

    /**
     * This method instantiate a new JPanel as mainPanel, populating it with a new panel for the formPanel one
     */
    private void setupMainPanel() {
        mainPanel = new JPanel(new BorderLayout());

        formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.decode("#42577F"));
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(formPanel, BorderLayout.CENTER);

    }

    /**
     * This method creates a JLabel for the title of the scene
     */
    private void setupTitleLabel() {
        formPanel.add(Box.createVerticalStrut(Utils.TITLE_MARGIN));
        JLabel titleLabel = new JLabel("Student Login");

        titleLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, Utils.TITLE_WIDTH));
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        formPanel.add(titleLabel);
        formPanel.add(Box.createVerticalStrut(Utils.TITLE_MARGIN));
    }

    /**
     * This method creates a JLabel for the logo of the scene
     */
    private void setupStudLogo() {
        studLogo = new JLabel();
        studLogo.setIcon(new ImageIcon(new ImageIcon("src/main/images/student.png").getImage().getScaledInstance(Utils.BIG_LOGO_SIZE, Utils.BIG_LOGO_SIZE, Image.SCALE_SMOOTH)));
        studLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        formPanel.add(studLogo);
        formPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));

    }

    /**
     * This method sets up the Username Text Field required to log in as a student
     */
    private void setupNameTextField(){
        JLabel label = new JLabel("Username");
        label.setForeground(Color.white);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(label);

        nameTextField = new JTextField(Utils.INSERT_USERNAME);
        nameTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameTextField.setPreferredSize(new Dimension(Utils.STANDARD_SMALL_TEXT_FIELD_WIDTH, Utils.STANDARD_TEXT_FIELD_HEIGHT));
        nameTextField.setMaximumSize(nameTextField.getPreferredSize());
        nameTextField.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(nameTextField);
    }

    /**
     * This method sets up the loginButton
     */
    private void setupLoginButt() {
        studLoginButt = new JButton("Login");
        studLoginButt.setAlignmentX(Component.CENTER_ALIGNMENT);
        Utils utils = new Utils();
        studLoginButt = utils.styleButtonTwo(studLoginButt);

        formPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        formPanel.add(studLoginButt);
    }

    /**
     * This method sets up the BackButton
     */
    private void setupBackButton(){
        backButton = Utils.setUPBackButton();

        formPanel.add(Box.createVerticalGlue());
        formPanel.add(backButton);
    }

    public JPanel getMainPanel() {return mainPanel;}

    /**
     * This method sets up the Error label with an empty text
     */
    private void setupErrorLabel(){
        errorLabel = new JLabel();
        errorLabel.setForeground(Color.red);
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(errorLabel);
    }

    /**
     * This method updates the text of the errorLabel to display the student login error message
     */
    public void displayError(){
        errorLabel.setText(Utils.USERNAME_ERROR);
    }


}
