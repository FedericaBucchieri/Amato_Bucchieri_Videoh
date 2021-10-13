package StudentEndVisionScene;

import EventManagement.BackEvent;
import EventManagement.Listener;
import sceneManager.Scene;
import sceneManager.SceneManager;
import Utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class StudentEndVisionScene implements Scene {
    private JPanel mainPanel;
    private JButton backButton;
    private List<Listener> listeners = new ArrayList<>();

    public StudentEndVisionScene(SceneManager sceneManager) {
        this.listeners.add(sceneManager);

        setupMainPanel();
        setupTitleLabel();
        setupTrophyLogo();
        setupBackButton();
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    private void setupMainPanel(){
        mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));
        mainPanel.setBackground(Color.decode("#42577F"));
    }

    private void setupTitleLabel() {
        mainPanel.add(Box.createVerticalStrut(Utils.TITLE_MARGIN));
        JLabel titleLabel = new JLabel("Well done!");
        titleLabel.setFont(new Font(Font.SANS_SERIF,  Font.BOLD, Utils.TITLE_WIDTH));
        titleLabel.setForeground(Color.white);
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(titleLabel);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
    }

    private void setupTrophyLogo() {
        JLabel trophyLogo = new JLabel();
        trophyLogo.setIcon(new ImageIcon(new ImageIcon("src/main/images/trophy.png").getImage().getScaledInstance(Utils.BIG_LOGO_SIZE, Utils.BIG_LOGO_SIZE, Image.SCALE_SMOOTH)));
        trophyLogo.setAlignmentX(Component.CENTER_ALIGNMENT);
        mainPanel.add(trophyLogo);
    }

    private void setupBackButton(){
        Utils utils = new Utils();
        backButton = new JButton("Back To Login");
        backButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        backButton = utils.styleButtonTwo(backButton);
        mainPanel.add(Box.createVerticalStrut(Utils.STANDARD_BORDER));
        mainPanel.add(backButton);
    }

    public void installUI(){
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Listener listener : listeners)
                    listener.listen(new BackEvent());
            }
        });
    }

}
