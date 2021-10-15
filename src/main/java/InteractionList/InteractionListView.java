package InteractionList;

import java.awt.*;


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
     * This method allows to paint the interactionDrawing list
     * @param g the Graphic instance to paint
     */
    public void paint(Graphics g){
        for(InteractionDrawing draw: controller.getModel().getInteractionDrawings()) {
            draw.paintComponent(g);
        }
    }


}
