package Widget;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

class CanvasController {

    private Canvas canvas;

    private Drawable currentDrawable = null;
    private boolean drawingModeOn = false;

    private Point mousePressedPosition = null;

    public CanvasController(Canvas canvas){

        this.canvas = canvas;
    }

    void scaleGraphics(double scaleX, double scaleY){

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
        System.out.println("Close drawable");
    }


    public void onMousePressed(MouseEvent e){
        this.registerMousePressedPosition(e);
    }


    public void onMouseDragged(MouseEvent e){
        //System.out.println("canvas mouse dragged");
        if(isDrawing()){
            if(this.currentDrawable instanceof Text){
                //System.out.println("Close text from drag");
                this.endDrawingMode();
                this.registerMousePressedPosition(e);
            }
        }
        if(! isDrawing()){
            this.openDrawingMode(new PenLine(this.mousePressedPosition));
        }
        this.drawPenLine(e);
    }

    public void onMouseReleased(MouseEvent e){
        if(isDrawing()){
            //System.out.println("close on mouse released");
            this.endDrawingMode();
            this.releaseMousePressedPosition();
        }
    }

    /**
     * Handle the text creating
     * @param e
     */
    public void onMouseClicked(MouseEvent e){
        if(! isDrawing()){
            this.registerMousePressedPosition(e);
            if(this.mousePressedPosition == null){
                //System.out.println("mouse pos null");
            }
            if(this.canvas.getBounds().contains(this.mousePressedPosition)){
                Text newText = new Text(this.mousePressedPosition, this.canvas.getBounds());
                this.openDrawingMode(newText);
                //System.out.println("New text");
            }

        } else {
            //End old text and create a new one
            //System.out.println("Close text from click");
            this.endDrawingMode();
            this.onMouseClicked(e);
        }
    }

    /**
     * Handle the writing of the text.
     * Entering enter key will close the text.
     * Entering backspace will delete the last charater typed
     * @param e
     */
    public void onKeyClicked(KeyEvent e){
        if(this.isDrawing()){
            if(this.currentDrawable instanceof Text && ! ((Text) this.currentDrawable).isClosed()){
                if((int)e.getKeyChar() == 10){ //KeyEvent.VK_ENTER
                    System.out.println("Press enter");
                    this.endDrawingMode();
                } else if((int)e.getKeyChar() == 8){ //KeyEvent.VK_BACK_SPACE
                    System.out.println("Press back space");
                    ((Text) this.currentDrawable).deleteLastChar();
                } else {
                    this.currentDrawable.addNewChar(e.getKeyChar());
                }
            }

        }
    }


}
