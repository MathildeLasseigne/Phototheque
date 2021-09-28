package Widget;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

public class PhotoComponent extends JComponent {



    PhotoComponentModel photoComponentModel;

    PhotoComponentUI photoComponentUI;

    private BufferedImage photo;

    /**
     * Create the photoComponent
     * <br/>Its prefered size will be dependent on the Frame size
     * @param photo the photo to render in the photo Component
     * @see Frame
     */
    public PhotoComponent(BufferedImage photo){
        this.setFocusable(true);
        this.photo = photo;
        this.photoComponentUI = new PhotoComponentUI(this);
        this.photoComponentModel = new PhotoComponentModel(this, photo); //Must be called after UI to have the canvas & photo ready

        this.add(new JLabel("Test2"));

        this.setVisible(true);
        this.setPreferredSize(this.photoComponentUI.frame.getBounds().getSize());

        this.photoComponentUI.installUI();
    }


    /**
     * Flip the photo to see its other side
     */
    public void flip(){
        this.photoComponentModel.setFlipped(! this.photoComponentModel.isFlipped());
    }


    /**
     * Set the frame of the photo
     */
    public void setFrame(Frame frame){
        this.photoComponentUI.setFrame(frame);
        this.setPreferredSize(this.photoComponentUI.frame.getBounds().getSize());
    }

    /**
     * Return the frame of the photos.
     * <br/>Default frame is beige and has a border of 10 on all sides
     * @return
     */
    public Frame getFrame() {
        return this.photoComponentUI.frame;
    }

    /**
     * Return the the photo displayed by the photo component
     * @return
     */
    public BufferedImage getPhoto(){
        return this.photo;
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        this.photoComponentUI.paint(g);
        //this.paintChildren(g);
        //Graphics2D g2d = (Graphics2D) g.create();
        //g2d.setColor(Color.red);
        //this.photoComponentUI.frame.repaint();
        //g2d.drawString("Hello", 50, 50);
        //g2d.dispose();
    }
}
