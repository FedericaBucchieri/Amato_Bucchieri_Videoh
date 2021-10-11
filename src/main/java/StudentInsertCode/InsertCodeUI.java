package StudentInsertCode;

import sceneManager.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class InsertCodeUI {
    private InsertCode controller;
    private JPanel mainPanel;
    private JTextField codeField;
    private JButton sendButton;
    private JButton backButton;
    private JLabel errorLabel;
    private final static int CODE_LENGTH = 6;

    public InsertCodeUI(InsertCode controller) {
        this.controller = controller;
        setupMainPanel();
        setupTitleLabel();
        setupCodeLogo();
        setupInsertCodeLabel();
        setupCodeTextField();
        setupErrorLabel();
        setupButtons();
    }

    public void installUI(){
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.goBackToGeneralLogin();
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

    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }

    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(Color.decode("#42577F"));
    }

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

    private void setupInsertCodeLabel() {
        mainPanel.add(Box.createVerticalStrut(Utils.TITLE_MARGIN));
        JLabel insertCodeLabel = new JLabel("Insert a video Code here:");
        insertCodeLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, Utils.SUBTITLE_WIDTH));
        insertCodeLabel.setForeground(Color.white);
        insertCodeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(insertCodeLabel);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
    }

    private void setupCodeLogo() {
        JLabel codeLogo = new JLabel();
        codeLogo.setIcon(new ImageIcon(new ImageIcon("src/main/images/browser.png").getImage().getScaledInstance(Utils.BIG_LOGO_SIZE, Utils.BIG_LOGO_SIZE, Image.SCALE_SMOOTH)));
        codeLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(codeLogo);
    }

    private void setupCodeTextField(){
        codeField = new JTextField("insert the code");
        codeField.setAlignmentX(Component.CENTER_ALIGNMENT);
        codeField.setPreferredSize(new Dimension(Utils.STANDARD_SMALL_TEXT_FIELD_WIDTH, Utils.STANDARD_TEXT_FIELD_HEIGHT));
        codeField.setMaximumSize(codeField.getPreferredSize());
        mainPanel.add(codeField);
    }

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

    private void setupErrorLabel(){
        errorLabel = new JLabel();
        errorLabel.setForeground(Color.red);
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(errorLabel);
    }

    public void displayError(){
        errorLabel.setText(Utils.CODE_ERROR);
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }
}
