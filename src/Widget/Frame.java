package Widget;

import java.awt.*;

/**
 * Create a frame with insets intending to surround the photo given to the PhotoComponent
 */
public class Frame {

    private Insets insets;

    private Rectangle bounds;


    public Frame(Insets insets){

        this.insets = insets;
    }

    /**
     * Create the bounds of the frame to be larger than the frame Insets and change the bounds of the photos to fit in the frame
     * @param photo
     */
    protected void setPhoto(Photos photo){
        photo.setPosition(positionPhoto());
        this.bounds = new Rectangle(new Point(photo.getBounds().x-this.insets.left, photo.getBounds().y-this.insets.top),
                new Dimension(photo.getBounds().width + this.insets.right + this.insets.left, photo.getBounds().height + this.insets.bottom + this.insets.top));
    }

    /**
     * Return the position the photo must be in to respect the insets
     * @return
     */
    private Point positionPhoto(){
        return new Point(this.insets.left, this.insets.top);
    }


    /**
     * Return the bounds if the frame was setted into the PhotoComponent
     * @return
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Get the insets of the frame
     * @return
     */
    public Insets getInsets() {
        return insets;
    }

    /**
     * Draw the following into the bounds.
     * <br/>By default, draw a beige rectangle filling the bounds
     * <br/>This methode can be overiden by a new one
     * <br/>The graphic component is clipped to the bounds of the frame
     * @param g2
     */
    public void paintComponent(Graphics2D g2){
        g2.setColor(new Color(200, 173, 127));
        g2.fill(this.bounds);
    }

    /**
     * Draw the following into the bounds.
     * <br/>By default, draw a beige rectangle filling the bounds
     * @param g
     */
    void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        Shape oldClip = g2.getClip();
        Color oldColor = g2.getColor();

        //g2.setClip(this.bounds); //setting clip will create bugs in JScroll bar
        paintComponent(g2);

        g2.setColor(oldColor);
        g2.setClip(oldClip);
    }


}
