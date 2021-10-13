package InteractionList;

import java.awt.*;


public class InteractionListUI {
    private InteractionList controller;

    public InteractionListUI(InteractionList controller) {
        this.controller = controller;
    }

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

    public void paint(Graphics g){
        for(InteractionDrawing draw: controller.getModel().getInteractionDrawings()) {
            draw.paintComponent(g);
        }
    }


}
