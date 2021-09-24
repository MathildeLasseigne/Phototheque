package Widget;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

class Canvas {

    private Rectangle bounds;

    /**
     * The bounds that limit & position the canvas
     * @param bounds the bounds of the photo
     */
    public Canvas(Rectangle bounds){

        this.bounds = bounds;
    }

    private ArrayList<Drawable> drawableComponents = new ArrayList<>();


    /**
     * Ajoute le drawAble a la liste des components
     * @param newDrawable
     */
    public void addNewDrawable(Drawable newDrawable){
        this.drawableComponents.add(newDrawable);
    }


    /**
     * Update the new boundaries of the canvas
     * @param newBounds
     */
    public void updateBounds(Rectangle newBounds){
        this.bounds = newBounds;
    }


    public void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        Color oldColor = g2.getColor();
        g2.setColor(Color.WHITE);
        g2.fill(this.bounds);

        g2.setColor(oldColor);
        g2.setClip(this.bounds);
        for(Drawable d : this.drawableComponents){
            d.draw(g);
        }

        /*g.setColor(Color.YELLOW);
        g.fillOval(10, 10, 200, 200);
        // draw Eyes
        g.setColor(Color.BLACK);
        g.fillOval(55, 65, 30, 30);
        g.fillOval(135, 65, 30, 30);
        // draw Mouth
        g.fillOval(50, 110, 120, 60);
        // adding smile
        g.setColor(Color.YELLOW);
        g.fillRect(50, 110, 120, 30);
        g.fillOval(50, 120, 120, 40);
        g2.setColor(oldColor);

         */
    }

    /**
     * Return the bounds of the photo
     * @return
     */
    public Rectangle getBounds() {
        return bounds;
    }
}
