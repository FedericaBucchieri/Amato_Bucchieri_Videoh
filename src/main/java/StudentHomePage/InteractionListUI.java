package StudentHomePage;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InteractionListUI {
    private InteractionList controller;

    public InteractionListUI(InteractionList controller) {
        this.controller = controller;
    }

    public void installUI(){
        InteractionListModel model = controller.getModel();

        controller.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectInteractionDrawing(e.getPoint());
                model.setMousePressed(true);
                System.out.println("clicked");
            }
        });

        controller.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if(model.isMousePressed()){
                    model.getSelectedInteractionDrawing().setX(e.getX());
                    controller.repaint();
                }
            }
        });


        controller.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                model.setMousePressed(false);
                model.getSelectedInteractionDrawing().setSelected(false);
                controller.updateInteraction(model.getSelectedInteractionDrawing().getInteraction(), e.getX());
                controller.repaint();
            }
        });
    }

    private void selectInteractionDrawing(Point point){
        InteractionListModel model = controller.getModel();

        for(InteractionDrawing drawing : model.getInteractionDrawings()){
            if(drawing.contain(point)) {
                drawing.setSelected(true);
                System.out.println("Selected");
                model.setSelectedInteractionDrawing(drawing);
                return;
            }
        }
    }

    public void paint(Graphics g){
        for(InteractionDrawing draw: controller.getModel().getInteractionDrawings()) {
            draw.paintComponent(g);
        }
    }
}
