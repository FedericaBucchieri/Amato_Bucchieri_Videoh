package StudentHomePage;

import uk.co.caprica.vlcj.discovery.NativeDiscovery;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentHomePageUI {
    private StudentHomePage controller;
    private JPanel mainPanel;
    private VideoBox videoBox;
    private JPanel settingsPanel;
    private JPanel annotationsPanel;

    private JButton backButton;

    public StudentHomePageUI(StudentHomePage controller){
        this.controller = controller;

        setupMainPanel();
        setupVideoBox();
        setupSettingsPanel();
        setupAnnotationsPanel();
        setupBackButton();

    }

    private void setupBackButton() {
        Icon icon = new ImageIcon("src/main/images/back-2.png");
        backButton = new JButton(icon);
        settingsPanel.add(backButton);

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                videoBox.dismissVideo();
                controller.goToStudentInsertCode();
            }
        });
    }

    private void setupAnnotationsPanel() {
    }

    private void setupSettingsPanel() {
        settingsPanel = new JPanel();
        settingsPanel.setLayout(new BoxLayout(settingsPanel, BoxLayout.Y_AXIS));
        settingsPanel.add(Box.createVerticalGlue());

        settingsPanel.setBackground(Color.GRAY); //to remove
        mainPanel.add(settingsPanel, BorderLayout.EAST);

    }

    private void setupMainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.setBackground(Color.blue); //to remove
    }

    private void setupVideoBox() {
        new NativeDiscovery().discover();
        videoBox = new VideoBox(controller.getModel().getFile());
        mainPanel.add(videoBox.getUI().getMainPanel(), BorderLayout.CENTER);

    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

}
