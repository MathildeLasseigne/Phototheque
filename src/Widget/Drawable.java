package Widget;

import java.awt.*;

public abstract class Drawable {

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
     * Add a position to the drawable. By default do nothing
     */
    public void addNewPosition(Point newPoint){}


    /**
     * Add a char written on the keyboard to the drawable. By default do nothing
     */
    public void addNewChar(char newChar){}
}
