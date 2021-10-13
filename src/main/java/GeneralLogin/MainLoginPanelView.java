package GeneralLogin;

import Dialogues.HelpDialogue;
import Utils.Utils;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainLoginPanelView {
    // the Component controller
    private MainLoginPanel controller;
    // the panel that container every element of the component view
    private JPanel mainPanel;
    // the panel that contains the login options
    private JPanel studProfPanel;
    // a button to handle help requests
    private JButton helpButton;
    // a button to login as a student
    private JButton studLoginButton;
    // a button to login as a professor
    private JButton profLoginButton;


    /**
     * This constructor creates an instance of MainLoginPanelView, adding a MainLoginPanel as controller and setting up the main Jpanel of the component view
     * @param controller the MainLoginPanel instance that is the component controller
     */
    public MainLoginPanelView(MainLoginPanel controller){
        this.controller = controller;
        setupMainPanel();
    }

    /**
     * This method instantiate a new JPanel as mainPanel, populating it with all the required UI elements
     */
    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        setupWelcomeLabel();
        setupLoginLabel();
        setupStudProfPanel();
        setupProblemButton();//any problem
    }

    /**
     * This method sets up the helpButton aspect and actions, adding it to the mainPanel
     */
    private void setupProblemButton() {
        JPanel problemPanel = new JPanel();
        problemPanel.setLayout(new BoxLayout(problemPanel, BoxLayout.Y_AXIS));
        helpButton = new JButton("Need an help?");
        helpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        Utils utils = new Utils();
        helpButton = utils.styleButtonTwo(helpButton);

        helpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    HelpDialogue dialog = new HelpDialogue();
                    dialog.setLocation(300,100);
                    dialog.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
                    dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
                    dialog.setVisible(true);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        //setupHelpButton
        problemPanel.add(helpButton);
        problemPanel.add(Box.createRigidArea(new Dimension(0, 80)));

        mainPanel.add(Box.createRigidArea(new Dimension(0, 100)));
        mainPanel.add(problemPanel);

        //add(problemPanel, BorderLayout.SOUTH);

    }

    /**
     * This method sets up a Welcome label for the scene, adding it to the mainPanel
     */
    private void setupWelcomeLabel() {
        mainPanel.add(Box.createVerticalStrut(Utils.TITLE_MARGIN));
        JLabel welcomeLabel = new JLabel("Welcome");
        welcomeLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, Utils.TITLE_WIDTH));
        welcomeLabel.setForeground(Color.decode("#314668"));
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(welcomeLabel);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));

    }

    /**
     * This method sets up the general login label to guide the user
     */
    private void setupLoginLabel(){
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        JLabel loginLabel = new JLabel("Do you want to login as a Student or a Professor?");
        loginLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, Utils.SUBTITLE_WIDTH));
        loginLabel.setForeground(Color.decode("#314668"));
        loginLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(loginLabel);
        mainPanel.add(Box.createVerticalStrut(Utils.TITLE_MARGIN));
    }


    /**
     * This method sets up the StudProfPanel creating a new JPanel and populating it with all the relevant UI components
     */
    private void setupStudProfPanel() {//questo contiene sia i loghi che i due bottoni
        studProfPanel = new JPanel();
        studProfPanel.setLayout(new BoxLayout(studProfPanel, BoxLayout.Y_AXIS));
        studProfPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        studProfPanel.add(setupLogos());//setupLogos contiene solo contiene solo le due immagini
        studProfPanel.add(setupLoginButtons());
        mainPanel.add(studProfPanel);

    }


    /**
     * This method sets up the LoginButtons for login as a student or as a professor
     * @return A JPanel containing the two login buttons
     */
    private JPanel setupLoginButtons() {
        JPanel loginButtonsPanel = new JPanel();
        loginButtonsPanel.setLayout(new BoxLayout(loginButtonsPanel, BoxLayout.X_AXIS));
        studLoginButton = new JButton("student");
        Utils utils = new Utils();
        studLoginButton = utils.styleButtonOne(studLoginButton);

        studLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.dispatchStudentLoginPressed();
            }
        });

        loginButtonsPanel.add(studLoginButton);
        loginButtonsPanel.add(Box.createHorizontalStrut(Utils.STANDARD_BORDER));

        profLoginButton = new JButton("professor");
        profLoginButton = utils.styleButtonOne(profLoginButton);

        profLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.dispatchProfLoginPressed();

            }
        });

        loginButtonsPanel.add(profLoginButton);

        return loginButtonsPanel;

    }

    /**
     * This method creates the two logos to be displayed in the UI
     * @return a JPanel with the logos attached
     */
    private JPanel setupLogos() {
        JPanel logos = new JPanel();
        logos.setLayout(new BoxLayout(logos, BoxLayout.X_AXIS));

        JLabel studLabel = new JLabel();
        studLabel.setIcon(controller.getModel().getStudIcon());
        studLabel.setBorder(new EmptyBorder(new Insets(Utils.STANDARD_BORDER, Utils.STANDARD_BORDER, Utils.STANDARD_BORDER, Utils.STANDARD_BORDER)));
        logos.add(studLabel);

        JLabel profLabel = new JLabel();
        profLabel.setIcon(controller.getModel().getProfIcon());
        profLabel.setBorder(new EmptyBorder(new Insets(Utils.STANDARD_BORDER, Utils.STANDARD_BORDER, Utils.STANDARD_BORDER, Utils.STANDARD_BORDER)));
        logos.add(profLabel);
        return  logos;
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }


}
