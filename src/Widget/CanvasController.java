package Widget;

import java.awt.*;
import java.awt.event.MouseEvent;

class CanvasController {

    private Canvas canvas;

    private Drawable currentDrawable = null;
    private boolean drawingModeOn = false;

    private Point mousePressedPosition = null;

    public CanvasController(Canvas canvas){

        this.canvas = canvas;
    }



    public void registerMousePressedPosition(MouseEvent e){
        this.mousePressedPosition = e.getPoint();
    }

    public void releaseMousePressedPosition(){
        this.mousePressedPosition = null;
    }


    public void drawPenLine(MouseEvent e){
        Point p = e.getPoint();
        this.currentDrawable.addNewPosition(e.getPoint());
        //TODO finish
    }


    /**
     * Check if the model is currently drawing
     * @return
     */
    public boolean isDrawing(){
        return this.currentDrawable != null && this.drawingModeOn;
    }

    public void openDrawingMode(Drawable drawable){
        this.currentDrawable = drawable;
        this.canvas.addNewDrawable(drawable);
        this.drawingModeOn = true;
    }

    /**
     * Close the current drawing before setting it to null
     */
    public void endDrawingMode(){
        this.currentDrawable.closeDrawable();
        this.currentDrawable = null;
        this.drawingModeOn = false;
    }


    public void onMousePressed(MouseEvent e){
        this.registerMousePressedPosition(e);
    }

    int newDrawing = 0;

    public void onMouseDragged(MouseEvent e){
        //System.out.println("canvas mouse dragged");
        if(! isDrawing()){
            this.openDrawingMode(new PenLine(this.mousePressedPosition));
            //System.out.println("new drawing :" + ++newDrawing);
            /*if(newDrawing == 5){
                System.out.println("test");
            }

             */
        }
        this.drawPenLine(e);
    }

    public void onMouseReleased(MouseEvent e){
        if(isDrawing()){
            this.endDrawingMode();
            this.releaseMousePressedPosition();
        }
        //TODO text part
    }

    public void onMouseClicked(MouseEvent e){
        //TODO text part
    }

}
