package ProfessorHomePage;

import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DetailUI {
    private JPanel mainPanel;
    private JLabel profName;
    private JButton addNewVideoButton;
    private JButton updateProfileButton;
    private JLabel profLogo;
    private DetailPanel controller;
    private JButton logoutButton;

    public DetailUI() {
        this.mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setSize(getPreferredSize());
        mainPanel.setPreferredSize(getPreferredSize());
        mainPanel.setBackground(Color.decode("#42577F"));

        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        setupProfLogo();

        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        setupWelcome();

        setupButtons();

    }

    private void setupWelcome(){
        JLabel welcome = new JLabel("Welcome");
        welcome.setAlignmentX(Component.CENTER_ALIGNMENT);
        welcome.setForeground(Color.white);
        welcome.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, Utils.SUBTITLE_WIDTH));
        mainPanel.add(welcome);

        profName = new JLabel();
        profName.setAlignmentX(Component.CENTER_ALIGNMENT);
        profName.setForeground(Color.white);
        profName.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, Utils.SUBTITLE_WIDTH));
        mainPanel.add(profName);
    }

    private void setupButtons(){
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));

        Utils utils = new Utils();
        addNewVideoButton = new JButton("Add New Video");
        addNewVideoButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        addNewVideoButton = utils.styleButtonTwo(addNewVideoButton);
        mainPanel.add(addNewVideoButton);

        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));

        updateProfileButton = new JButton("Update profile");
        updateProfileButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        updateProfileButton = utils.styleButtonTwo(updateProfileButton);
        mainPanel.add(updateProfileButton);

        mainPanel.add(Box.createVerticalStrut(Utils.DIVIDER));

        logoutButton = new JButton("Logout");
        logoutButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        logoutButton = utils.styleButtonThree(logoutButton);
        mainPanel.add(logoutButton);
    }

    private void setupProfLogo() {
        profLogo = new JLabel();
        profLogo.setIcon(new ImageIcon(new ImageIcon("src/main/images/professor.png").getImage().getScaledInstance(Utils.BIG_LOGO_SIZE, Utils.BIG_LOGO_SIZE, Image.SCALE_SMOOTH)));
        profLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        mainPanel.add(profLogo);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
    }

    public void installUI(DetailPanel controller){
        this.controller = controller;
        profName.setText(controller.getProfessorUsername());

        addNewVideoButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleNewVideoRequest();
            }
        });

        updateProfileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleUpdateProfileRequest();
            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.handleLogout();
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public Dimension getPreferredSize() {
        return new Dimension(Utils.DETAILPANEL_WIDTH, Utils.DETAILPANEL_HEIGHT);
    }
}
