package view;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {


    private JPanel mainPanel;
    private JPanel studProfPanel;
    private JButton helpButton;
    private JButton studLoginButton;
    private JButton profLoginButton;

    public LoginView(){
        setupUI();
        setupMainPanel();

    }

    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        setupWelcomeLabel();
        setupStudProfPanel();
        setupProblemButton();//any problem
        //add(mainPanel, BorderLayout.CENTER);
        //pack();
    }

    private void setupProblemButton() {
        JPanel problemPanel = new JPanel();
        problemPanel.setLayout(new BoxLayout(problemPanel, BoxLayout.Y_AXIS));
        helpButton = new JButton("Need an help?");
        helpButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        //setupHelpButton
        problemPanel.add(helpButton);
        problemPanel.add(Box.createRigidArea(new Dimension(0, 80)));

        mainPanel.add(Box.createRigidArea(new Dimension(0, 100)));
        //mainPanel.add(problemPanel);

        //add(problemPanel, BorderLayout.SOUTH);

    }

    private void setupWelcomeLabel() {
        mainPanel.add(Box.createRigidArea(new Dimension(0, 80)));
        JLabel welcomeLabel = new JLabel("Welcome");
        welcomeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        //to add welcome icon
        mainPanel.add(welcomeLabel);
        mainPanel.add(Box.createRigidArea(new Dimension(0, 80)));

    }

    private void setupStudProfPanel() {//questo contiene sia i loghi che i due bottoni
        studProfPanel = new JPanel();
        studProfPanel.setLayout(new BoxLayout(studProfPanel, BoxLayout.Y_AXIS));
        studProfPanel.setAlignmentX(Component.CENTER_ALIGNMENT);

        studProfPanel.add(setupLogos());//setupLogos contiene solo contiene solo le due immagini
        studProfPanel.add(setupLoginButtons());
        mainPanel.add(studProfPanel);

    }

    private JPanel setupLoginButtons() {
        JPanel loginButtonsPanel = new JPanel();
        loginButtonsPanel.setLayout(new BoxLayout(loginButtonsPanel, BoxLayout.X_AXIS));
        studLoginButton = new JButton("student");
        loginButtonsPanel.add(studLoginButton);

        profLoginButton = new JButton("professor");
        loginButtonsPanel.add(profLoginButton);

        return loginButtonsPanel;

    }

    private JPanel setupLogos() {
        JPanel logos = new JPanel();
        logos.setLayout(new BoxLayout(logos, BoxLayout.X_AXIS));
        JLabel studLabel = new JLabel();
        studLabel.setIcon(new ImageIcon(new ImageIcon("images/stud.png").getImage().getScaledInstance(90, 120, Image.SCALE_SMOOTH)));
        logos.add(studLabel);

        JLabel profLabel = new JLabel();
        profLabel.setIcon(new ImageIcon(new ImageIcon("images/prof.png").getImage().getScaledInstance(90, 120, Image.SCALE_SMOOTH)));
        logos.add(profLabel);
        return  logos;




    }

    private void setupUI() {
        setPreferredSize(new Dimension(600, 800));
        setMinimumSize(getPreferredSize());
        setLayout(new BorderLayout());
        setVisible(true);
    }
}
