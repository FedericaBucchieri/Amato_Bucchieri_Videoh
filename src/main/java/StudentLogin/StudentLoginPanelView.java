package StudentLogin;

import sceneManager.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

public class StudentLoginPanelView {
    private StudentLoginPanel controller;

    private JPanel formPanel;
    private JPanel mainPanel;
    private JLabel studLogo;
    private JTextField nameTextField;
    private JButton studLoginButt;
    private JButton backButton;
    private JLabel errorLabel;

    public StudentLoginPanelView(StudentLoginPanel controller){
        this.controller = controller;

        setupMainPanel();
        setupTitleLabel();
        setupStudLogo();
        setupNameTextField();
        setupLoginButt();
        setupErrorLabel();
        setupBackButton();

    }

    public void installUI(){
        studLoginButt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = nameTextField.getText();
                if(username.equals("") || username.trim().length() == 0 )
                    displayError();
                else
                    controller.sendUsername(nameTextField.getText());
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
                    String username = nameTextField.getText();
                    if(username.equals("") || username.trim().length() == 0 )
                        displayError();
                    else
                        controller.sendUsername(nameTextField.getText());
                }
            }
        });
    }

    private void setupMainPanel() {
        mainPanel = new JPanel(new BorderLayout());

        formPanel = new JPanel();
        formPanel.setLayout(new BoxLayout(formPanel, BoxLayout.Y_AXIS));
        formPanel.setBackground(Color.decode("#42577F"));
        formPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        mainPanel.add(formPanel, BorderLayout.CENTER);

    }

    private void setupTitleLabel() {
        formPanel.add(Box.createVerticalStrut(Utils.TITLE_MARGIN));
        JLabel titleLabel = new JLabel("Student Login");

        titleLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, Utils.TITLE_WIDTH));
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        formPanel.add(titleLabel);
        formPanel.add(Box.createVerticalStrut(Utils.TITLE_MARGIN));
    }

    private void setupStudLogo() {
        studLogo = new JLabel();
        studLogo.setIcon(new ImageIcon(new ImageIcon("src/main/images/student.png").getImage().getScaledInstance(Utils.BIG_LOGO_SIZE, Utils.BIG_LOGO_SIZE, Image.SCALE_SMOOTH)));
        studLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        formPanel.add(studLogo);
        formPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));

    }

    private void setupNameTextField(){
        JLabel label = new JLabel("Username");
        label.setForeground(Color.white);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(label);

        nameTextField = new JTextField("insert an username");
        nameTextField.setAlignmentX(Component.CENTER_ALIGNMENT);
        nameTextField.setPreferredSize(new Dimension(Utils.STANDARD_SMALL_TEXT_FIELD_WIDTH, Utils.STANDARD_TEXT_FIELD_HEIGHT));
        nameTextField.setMaximumSize(nameTextField.getPreferredSize());
        nameTextField.setHorizontalAlignment(SwingConstants.CENTER);
        formPanel.add(nameTextField);
    }

    private void setupLoginButt() {
        studLoginButt = new JButton("Login");
        studLoginButt.setAlignmentX(Component.CENTER_ALIGNMENT);
        Utils utils = new Utils();
        studLoginButt = utils.styleButtonTwo(studLoginButt);

        formPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        formPanel.add(studLoginButt);
    }

    private void setupBackButton(){
        Icon icon = new ImageIcon("src/main/images/back-2.png");
        backButton = new JButton(icon);
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton.setBorderPainted(false);

        formPanel.add(Box.createVerticalGlue());
        formPanel.add(backButton);
    }

    public JPanel getMainPanel() {return mainPanel;}

    private void setupErrorLabel(){
        errorLabel = new JLabel();
        errorLabel.setForeground(Color.red);
        errorLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        formPanel.add(errorLabel);
    }

    private void displayError(){
        errorLabel.setText(Utils.USERNAME_ERROR);
    }


}
