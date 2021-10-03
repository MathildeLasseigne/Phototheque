package Tools;

import java.awt.*;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;

/**A testing tool to monitor a component resizing */
public class ResizingTracker {

    /**Activate or disactivate the Resizing tracker*/
    private static boolean activate = false;

    private Dimension oldDimension = new Dimension();

    /**
     * Add a componentListener to the component & print the result into the terminal
     * @param c the component to monitor
     * @param checkMinSize is the minimal size of the component checked ?
     * @param checkMaxSize is the maximal size of the component checked ?
     * @see ResizingTracker#ResizingTracker(Component)
     */
    public ResizingTracker(Component c, boolean checkMinSize, boolean checkMaxSize){
        if(activate){
            c.addComponentListener(new ComponentListener() {
                @Override
                public void componentResized(ComponentEvent e) {
                    Dimension newDim = c.getSize();
                    if(! newDim.equals(oldDimension)){
                        if(checkMinSize & inf(newDim, c.getMinimumSize())){
                            System.out.println(c.getName() + " is smaller than its minimal size : new size : "+ newDim+", min size : "+c.getMinimumSize());
                        } else if(checkMaxSize & sup(newDim, c.getMaximumSize())){
                            System.out.println(c.getName() + " is bigger than its maximal size : new size : "+ newDim+", max size : "+c.getMaximumSize());
                        } else {
                            System.out.println(c.getName() + " new size : "+ newDim);
                        }
                    }
                }

                @Override
                public void componentMoved(ComponentEvent e) {

                }

                @Override
                public void componentShown(ComponentEvent e) {

                }

                @Override
                public void componentHidden(ComponentEvent e) {

                }
            });
        }

    }

    /**
     * Add a componentListener to the component & print the result into the terminal
     * @param c the component to monitor
     * @see ResizingTracker#ResizingTracker(Component, boolean, boolean)
     */
    public ResizingTracker(Component c){
        this(c, false, false);
    }


    /**
     * Check if d1 < d2
     * @param d1
     * @param d2
     * @return d1 < d2
     */
    public static boolean inf(Dimension d1, Dimension d2){
        return d1.getHeight()<d2.getHeight() | d1.getWidth()< d2.getWidth();
    }

    /**
     * Check if d1 > d2
     * @param d1
     * @param d2
     * @return d1 > d2
     */
    public static boolean sup(Dimension d1, Dimension d2){
        return d1.getHeight()>d2.getHeight() | d1.getWidth()> d2.getWidth();
    }

    /**
     * Activate or desactivate the resizing tracker.
     * <br/>Desactivatig the tracker prevent the component listener from being added
     * @param activateTracker set to false to desactivate and true to activate
     */
    public static void activate(boolean activateTracker){
        activate = activateTracker;
    }
}
