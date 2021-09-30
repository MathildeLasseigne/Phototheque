package Widget;

import java.awt.*;

/**
 * Create a frame with insets intending to surround the photo given to the PhotoComponent
 */
public class Frame {

    private Insets insets;

    private Rectangle bounds;

    private Rectangle insideBounds;


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
     * The the bounds of the photo in which it is possible to be displayed
     * @param photo
     */
    protected void setPhotoBounds(Photos photo){
        photo.setBounds(this.insideBounds);
    }

    /**
     * Return the position the photo must be in to respect the insets and its bounds
     * @return
     */
    private Point positionPhoto(){
        return new Point(this.bounds.x+this.insets.left, this.bounds.y+this.insets.top);
    }


    /**
     * Create the inside and outside bounds depending on the photo component bounds
     * @param photoComponent
     */
    void updateBounds(PhotoComponent photoComponent){
        Dimension pcDim = photoComponent.getSize();
        this.bounds = new Rectangle(photoComponent.getLocation(), pcDim);
        this.insideBounds = new Rectangle(positionPhoto(), new Dimension(pcDim.width - this.insets.left - this.insets.right, pcDim.height - this.insets.top - this.insets.bottom));
    }

    /**
     * Create the inside and outside bounds depending on the photo component bounds
     * @param photoComponent
     */
    void setBounds(PhotoComponent photoComponent){
        this.bounds = new Rectangle(photoComponent.getLocation(), photoComponent.dimension);
        this.insideBounds = new Rectangle(positionPhoto(), new Dimension(photoComponent.dimension.width - this.insets.left - this.insets.right, photoComponent.dimension.height - this.insets.top - this.insets.bottom));
    }

    /**
     * Return the bounds if the frame was setted into the PhotoComponent
     * @return
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Return the bounds in which the photo must be displayed
     * @return
     */
    public Rectangle getInsideBounds() {
        return insideBounds;
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

        g2.setColor(new Color(223, 242, 255));
        g2.fill(this.insideBounds);
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
