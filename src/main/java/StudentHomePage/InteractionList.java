package StudentHomePage;

import EventManagement.Listener;
import EventManagement.UpdateQuestionEvent;
import entities.GenericInteraction;
import entities.Question;
import sceneManager.Utils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

public class InteractionList extends JComponent {
    private InteractionListModel model;
    private InteractionListUI ui;
    private List<Listener> listeners = new ArrayList<>();

    public InteractionList(int generalLength, InteractionPanel interactionPanel) {
        this.listeners.add(interactionPanel);

        this.model = new InteractionListModel(generalLength, this);
        this.ui = new InteractionListUI(this);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if(!model.isShiftPressed()) {
                    ui.selectInteractionDrawing(e.getPoint());
                    model.setMousePressed(true);
                }
                else { // Shift pressed: Delete mode
                    ui.selectInteractionDrawing(e.getPoint());
                    model.deleteSelectedInteraction();
                    repaint();
                }
            }
        });

        addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(model.isMousePressed() && model.getSelectedInteractionDrawing() != null){
                    model.getSelectedInteractionDrawing().setX(e.getX());
                    repaint();
                }
            }
        });


        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                model.setMousePressed(false);
                if(model.getSelectedInteractionDrawing() != null) {
                    model.getSelectedInteractionDrawing().setSelected(false);
                    updateInteraction(model.getSelectedInteractionDrawing().getInteraction(), e.getX());
                    repaint();
                }
            }
        });


        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                System.out.println("working");

                if(e.getKeyCode() == KeyEvent.VK_SHIFT)
                    System.out.println("shift pressed");
                model.setShiftPressed(true);
            }
        });

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_SHIFT)
                    model.setShiftPressed(false);
            }
        });

        //TODO FOCUS NOT WORKING
    }


    public InteractionListModel getModel() {
        return model;
    }

    public void addInteractionToList(GenericInteraction interaction){
        model.addInteractionToList(interaction);
    }

    @Override
    protected void paintComponent(Graphics g) {
        ui.paint(g);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(getWidth(), Utils.ANNOTATION_PANEL_HEIGHT);
    }

    @Override
    public Dimension getSize(Dimension rv) {
        return getPreferredSize();
    }

    public void updateInteraction(GenericInteraction interaction, int newPosition){
        model.updateInteractionTimestamp(interaction, newPosition);

        if(interaction instanceof Question) {
            //dispatch UpdateQuestionEvent
            for (Listener listener : listeners)
                listener.listen(new UpdateQuestionEvent());
        }
    }
}
