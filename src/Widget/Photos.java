package Widget;

import Tools.Utilities;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Photos {

    /**
     * The bounds of the photo, positionned in its parent. The place the photo has to display itself
     */
    private Rectangle displayBounds;

    /**
     * The bounds of the photo itself
     */
    private Rectangle bounds;

    private BufferedImage photo;

    private BufferedImage resizedPhoto;

    /**
     * Draw the photo at the designated point. Its width and height will be dependent on the photo
     * <br/>The photo will be placed at point (0,0) by default
     * @param photo
     */
    public Photos(BufferedImage photo){
        this.photo = photo;
        this.displayBounds = new Rectangle(new Point(0,0), new Dimension(photo.getWidth(), photo.getHeight()));
    }

    /**
     * Create a new bounds taking into account the positionOffset
     * @param positionOffset
     */
    public void setPosition(Point positionOffset){
        this.displayBounds = new Rectangle(positionOffset, new Dimension(photo.getWidth(), photo.getHeight()));
    }

    /**
     * Calculate the bounds of the photo and resize it depending on the given bounds
     * @param displayBounds the place the photo has to display itself
     */
    void setBounds(Rectangle displayBounds){
        this.displayBounds = displayBounds;
        this.resizedPhoto = Utilities.resizeBIWithProportion(this.photo, displayBounds.width, displayBounds.height);
        Dimension newDim = new Dimension(resizedPhoto.getWidth(), resizedPhoto.getHeight());
        Point newPos = new Point(displayBounds.x+((displayBounds.width- newDim.width)/2),displayBounds.y+((displayBounds.height- newDim.height)/2));
        this.bounds = new Rectangle(newPos, newDim);
    }

    /**
     * The bounds of the photo within its parent
     * @return
     */
    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Draw the photo within the bounds
     * @param g
     */
    public void paint(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(this.resizedPhoto, this.bounds.x, this.bounds.y, null);
    }
}
