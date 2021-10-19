package InteractionList;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class InteractionListView {
    // the component Controller
    private InteractionList controller;

    public InteractionListView(InteractionList controller) {
        this.controller = controller;
    }

    /**
     * This method select an interaction drawing from the printed drawings accordingly to a given point
     * @param point the point to be checked
     */
    public void selectInteractionDrawing(Point point){
        InteractionListModel model = controller.getModel();

        for(InteractionDrawing drawing : model.getInteractionDrawings()){
            if(drawing.contain(point)) {
                drawing.setSelected(true);
                model.setSelectedInteractionDrawing(drawing);
                return;
            }
        }
    }

    /**
     * this method install all the eventListeners required by the UI to make the component work
     * Allowing drag and drop of interaction drawing in the timeline
     */
    public void installUI(){
        InteractionListModel model = controller.getModel();

        controller.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (model.isListenersActive()){
                    if(!model.isDeleteMode()) {
                        selectInteractionDrawing(e.getPoint());
                        model.setMousePressed(true);
                    }
                    else { // Shift pressed: Delete mode
                        selectInteractionDrawing(e.getPoint());
                        model.deleteSelectedInteraction();
                        controller.repaint();
                    }
                }

            }
        });

        controller.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                if (model.isListenersActive()){
                    if(model.isMousePressed() && model.getSelectedInteractionDrawing() != null){
                        model.getSelectedInteractionDrawing().setX(e.getX());
                        controller.repaint();
                    }
                }

            }
        });


        controller.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (model.isListenersActive()){
                    model.setMousePressed(false);
                    if(model.getSelectedInteractionDrawing() != null) {
                        model.getSelectedInteractionDrawing().setSelected(false);
                        controller.updateInteraction(model.getSelectedInteractionDrawing().getInteraction(), e.getX());
                        controller.repaint();
                    }
                }

            }
        });
    }

    /**
     * This method allows to paint the interactionDrawing list
     * @param g the Graphic instance to paint
     */
    public void paint(Graphics g){
        for(InteractionDrawing draw: controller.getModel().getInteractionDrawings()) {
            draw.paintComponent(g);
        }
    }


}
