package StudentInsertCode;

import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InsertCodeFormView {
    // the Component controller
    private InsertCodeForm controller;
    // the panel that container every element of the component view
    private JPanel mainPanel;
    // the text field for inserting the video code to search for
    private JTextField codeField;
    // the button to handle code submit
    private JButton sendButton;
    // the button to go back to the previous scene
    private JButton backButton;
    // a label to display login errors
    private JLabel errorLabel;
    // Fixed length of a Code label
    private final static int CODE_LENGTH = 6;

    /**
     * This constructor creates an instance of InsertCodeFormView, adding all the UI element required to the mainPanel
     * @param controller the InsertCodeForm instance that is the component controller
     */
    public InsertCodeFormView(InsertCodeForm controller) {
        this.controller = controller;
        setupMainPanel();
        setupTitleLabel();
        setupCodeLogo();
        setupInsertCodeLabel();
        setupCodeTextField();
        setupErrorLabel();
        setupButtons();
    }

    /**
     * This method installs the UI, setting up all the required actionListeners for each element of the UI
     */
    public void installUI(){
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.dispatchBackEvent();
            }
        });

        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = codeField.getText();
                if( code.trim().length() == 0 || code.length() < CODE_LENGTH || !isNumeric(code) )
                    displayError();
                else
                    controller.goToStudentHomePage(codeField.getText(), controller.getStudentUsername());
            }
        });

        codeField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String code = codeField.getText();
                    if( code.trim().length() == 0 || code.length() < CODE_LENGTH || !isNumeric(code) )
                        displayError();
                    else
                        controller.goToStudentHomePage(codeField.getText(), controller.getStudentUsername());
                }
            }
        });

        codeField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                codeField.setText("");
            }
        });
    }

    /**
     * This method checks if the passed string contains only integer characters, converting the string to a interger value
     * @param str the string to check
     * @return the integer value corresponding to the string
     */
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    /**
     * This method instantiate a new JPanel as mainPanel, populating it with a new panel for the formPanel one
     */
    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(Color.decode("#42577F"));
    }

    /**
     * This method creates a JLabel for the title of the scene
     */
    private void setupTitleLabel() {
        mainPanel.add(Box.createVerticalStrut(Utils.TITLE_MARGIN));
        JLabel titleLabel = new JLabel("Welcome");
        titleLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, Utils.TITLE_WIDTH));
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);

        JLabel nameLabel = new JLabel( controller.getStudentUsername());
        nameLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, Utils.TITLE_WIDTH));
        nameLabel.setForeground(Color.white);
        nameLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(nameLabel);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
    }

    /**
     * This method sets up the video code TextField Label
     */
    private void setupInsertCodeLabel() {
        mainPanel.add(Box.createVerticalStrut(Utils.TITLE_MARGIN));
        JLabel insertCodeLabel = new JLabel("Insert a video Code here:");
        insertCodeLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, Utils.SUBTITLE_WIDTH));
        insertCodeLabel.setForeground(Color.white);
        insertCodeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(insertCodeLabel);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
    }

    /**
     * This method sets up the loginButton
     */
    private void setupCodeLogo() {
        JLabel codeLogo = new JLabel();
        codeLogo.setIcon(new ImageIcon(new ImageIcon("src/main/images/browser.png").getImage().getScaledInstance(Utils.BIG_LOGO_SIZE, Utils.BIG_LOGO_SIZE, Image.SCALE_SMOOTH)));
        codeLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(codeLogo);
    }

    /**
     * This method sets up the video code textField required to search for a specific video
     */
    private void setupCodeTextField(){
        codeField = new JTextField("insert the code");
        codeField.setAlignmentX(Component.CENTER_ALIGNMENT);
        codeField.setPreferredSize(new Dimension(Utils.STANDARD_SMALL_TEXT_FIELD_WIDTH, Utils.STANDARD_TEXT_FIELD_HEIGHT));
        codeField.setMaximumSize(codeField.getPreferredSize());
        mainPanel.add(codeField);
    }

    /**
     * This method sets up the sendButton and the backButton adding them to the mainPanel
     */
    private void setupButtons(){
        Utils utils = new Utils();
        sendButton = new JButton("Go to Video");
        sendButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        sendButton = utils.styleButtonTwo(sendButton);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        mainPanel.add(sendButton);

        backButton = utils.setUPBackButton();
        mainPanel.add(Box.createVerticalGlue());
        mainPanel.add(backButton);
    }

    /**
     * This method sets up the Error label with an empty text
     */
    private void setupErrorLabel(){
        errorLabel = new JLabel();
        errorLabel.setForeground(Color.red);
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(errorLabel);
    }

    /**
     * This method updates the text of the errorLabel to display the student login error message
     */
    public void displayError(){
        errorLabel.setText(Utils.CODE_ERROR);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
