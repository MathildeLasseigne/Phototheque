package Widget;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;

public abstract class Drawable {


    private boolean selected = false;

    protected Area boundingShape = new Area();

    /**
     * Draw the drawable component
     * @param g the graphical context where to paint
     */
    public abstract void draw(Graphics g);

    /**
     * Close the drawable. By default do nothing
     */
    public void closeDrawable(){}

    /**
     * Open the drawable. By default do nothing
     */
    public void openDrawable(){}

    /**
     * Add a position to the drawable. By default do nothing
     */
    public void addNewPosition(Point newPoint){}


    /**
     * Add a char written on the keyboard to the drawable. By default do nothing
     */
    public void addNewChar(char newChar){}


    /**Check if the point is contained in the drawable*/
    public boolean contains(Point p){
        return this.boundingShape.contains(p);
    };

    /**
     * Inform the drawable if it is selected. Implementation is done in Drawable class
     * @param selected
     */
    public void setSelected(boolean selected){
        this.selected = selected;
    }

    /**Check if the drawable is selected*/
    protected boolean isSelected() {
        return selected;
    }

    protected Area getBoundingShape(){
        return this.boundingShape;
    }

    protected void setBoundingShape(Area shape){
        this.boundingShape = shape;
    }
}
