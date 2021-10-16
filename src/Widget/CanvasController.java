package Widget;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

class CanvasController {

    private Canvas canvas;

    private Drawable currentDrawable = null;
    private boolean drawingModeOn = false;

    private Point mousePressedPosition = null;

    /*---------Selection-----------*/

    /**
     * Decide if any selection is allowed
     */
    private boolean selectionAllowed = false;

    private Drawable selection = null;

    /**Flag if the current action is using an old drawing or an old one*/
    private boolean selectModeOn = false;

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
    }

    /**
     * Calculate the AffineTranslate to apply to the different points of the drawable,
     * depending on the registered mouse position
     * @param e
     */
    public void moveDrawable(MouseEvent e){
        //TODO
    }


    /**
     * Check if the model is currently drawing
     * @return
     */
    public boolean isDrawing(){
        return this.currentDrawable != null && this.drawingModeOn;
    }

    /**
     * Set the current drawable and add it to the canvas
     * @param drawable
     */
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
        if(selectionAllowed){
            this.selectModeOn = trySelect(e, false);
        }
    }


    public void onMouseDragged(MouseEvent e){
        //System.out.println("canvas mouse dragged");
        if(! this.selectModeOn){ //Normal drawing
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
        } else {
            if(selectionAllowed){
                //TODO
            }
        }
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
        if(! selectModeOn){
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

    }

    /**
     * Handle the writing of the text.
     * Entering enter key will close the text.
     * Entering backspace will delete the last character typed
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

    int test = 0;

    /**
     * Try to select a drawing at the position of the mouse.
     * If no drawing is at the position of the mouse, {@link CanvasController#endSelect()}
     * @param e the mouse event linked to the mouse
     * @param unselect decide whether to unselect a selected drawable at the position of the mouse.
     *                 If false, the mouse position being on a current selection will do nothing, else will unselect it.
     * @return true if a drawing was selected, else false
     * @see CanvasController#selectModeOn
     */
    private boolean trySelect(MouseEvent e, boolean unselect){
        if(! this.canvas.getBounds().contains(e.getPoint())){
            System.out.println("No selection outside screen");
        } else {
            test++;
            if(test == 3){
                //System.out.println("Test");
            }
            if(selectModeOn){ //Doesnt try the select again the same drawing
                Drawable drawableOnMouse = this.selection;
                //if(this.selection.contains(e.getLocationOnScreen())){
                if(this.selection.contains(e.getPoint())){
                    if(unselect){
                        return unselect(drawableOnMouse);
                    } else {
                        //TODO check multiple selection
                        return true;
                    }
                } else { //If the point is not on the selection, remove the point
                    endSelect();
                }
            }
            //Drawable drawableOnPoint = this.canvas.mapPointToDrawable(e.getLocationOnScreen());
            Drawable drawableOnPoint = this.canvas.mapPointToDrawable(e.getPoint());
            if(drawableOnPoint != null){
                this.selection = drawableOnPoint;
                this.selection.setSelected(true);
                return true;
            }
            //endSelect();
        }
        return false;

    }

    /**
     * Stop the select operation and close the drawable.
     * Remove any drawable from the selection.
     */
    private void endSelect(){
        if(this.selection == null){
            System.out.println("Cant end null selection");
            return;
        }
        this.selection.closeDrawable();
        this.selection.setSelected(false);
        this.selection = null;
    }

    /**
     * Unselect the given drawable. If it is the only selection, end the selectMode
     * @param drawable the drawable to unselect
     * @return remaining select : false if the last selected drawable was removed, else true
     */
    private boolean unselect(Drawable drawable){
        if(false){ //TODO more than 1 selection
            drawable.closeDrawable();
            drawable.setSelected(false);
            //TODO remove from selection
            return true;
        } else {
            endSelect();
            return false;
        }
    }

    /**
     * Allow or do not allow selection.
     * @param allow If false, close and clear all selection
     */
    public void setSelectionAllowed(boolean allow){
        this.selectionAllowed = allow;
        if(!allow){
            endSelect();
            this.selectModeOn = false;
        }
    }


}
