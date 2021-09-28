package Widget;

import javax.swing.*;
import java.awt.*;

public class PhotoComponentUI {

    private PhotoComponent photoComponent;

    Frame frame;

    Photos photo = null;

    Canvas canvas;


    public PhotoComponentUI(PhotoComponent photoComponent){
        this.photoComponent = photoComponent;
        this.photo = new Photos(this.photoComponent.getPhoto());
        setCanvas();

        /*this.frame = new JPanel(){
            @Override
            public void paintComponents(Graphics g) {
                super.paintComponents(g);
                g.setColor(new Color(240, 195, 0));
                g.fillRect(frame.getX(), frame.getY(),frame.getWidth(), frame.getHeight());
            }
        };

        this.frame.setBackground(new Color(240, 195, 0));
        this.frame.setBorder(BorderFactory.createEmptyBorder(10,10,10,10));

        this.frame.add(new JLabel("Test"));

         */

        //Create a frame with no border -> default frame
        this.setFrame(new Frame(new Insets(0,0,0,0)));
    }


    /**
     * Set the frame
     * @param frame
     */
    public void setFrame(Frame frame) {
        this.frame = frame;
        this.frame.setPhoto(this.photo); //Change the bounds of the photos to fit in the frame
        this.canvas.updateBounds(this.photo.getBounds());

    }

    /**
     * Create a new canvas. Call before set frame !
     */
    private void setCanvas(){
        this.canvas = new Canvas(this.photo.getBounds());
    }



    public void paint(Graphics g){
        //Begin by drawing the frame in the background
        this.frame.paint(g);
        if(this.photoComponent.photoComponentModel.isFlipped()){
            this.canvas.paintComponent(g);
        } else {
            this.photo.paint(g);
        }

    }

    /**
     * Install the 2 listners
     */
    public void installUI(){
        this.photoComponent.addMouseListener(this.photoComponent.photoComponentModel.getMouseListenerUI());
        this.photoComponent.addMouseMotionListener(this.photoComponent.photoComponentModel.getMouseListenerUI());
        this.photoComponent.addKeyListener(this.photoComponent.photoComponentModel.getKeyListenerUI());
    }


}
