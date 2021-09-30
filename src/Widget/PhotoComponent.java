package Widget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.image.BufferedImage;

public class PhotoComponent extends JComponent {



    PhotoComponentModel photoComponentModel;

    PhotoComponentUI photoComponentUI;

    Dimension dimension;

    private BufferedImage photo;

    /**
     * Create the photoComponent
     * <br/>Its prefered size will be dependent on the Frame size
     * @param photo the photo to render in the photo Component
     * @see Frame
     */
    public PhotoComponent(BufferedImage photo){
        this.setFocusable(true);

        this.setVisible(true);
        //this.setPreferredSize(this.photoComponentUI.frame.getBounds().getSize());
        this.setPreferredSize(new Dimension(300,300));
        this.dimension = this.getPreferredSize();

        this.photo = photo;
        this.photoComponentUI = new PhotoComponentUI(this);
        this.photoComponentModel = new PhotoComponentModel(this, photo); //Must be called after UI to have the canvas & photo ready


        this.photoComponentUI.installUI();
    }

    public PhotoComponent(BufferedImage photo, Dimension dimension){
        this.setFocusable(true);

        this.setVisible(true);
        this.setPreferredSize(dimension);
        this.dimension = dimension;

        this.photo = photo;
        this.photoComponentUI = new PhotoComponentUI(this);
        this.photoComponentModel = new PhotoComponentModel(this, photo); //Must be called after UI to have the canvas & photo ready


        this.photoComponentUI.installUI();
        this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                updateBounds();
            }
        });
    }

    public PhotoComponent(BufferedImage photo, Point position, Dimension dimension){
        this.setFocusable(true);

        this.setPreferredSize(dimension);
        this.dimension = dimension;
        this.setLocation(position);


        this.photo = photo;
        this.photoComponentUI = new PhotoComponentUI(this);
        this.photoComponentModel = new PhotoComponentModel(this, photo); //Must be called after UI to have the canvas & photo ready


        this.setVisible(true);

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
        //this.setPreferredSize(this.photoComponentUI.frame.getBounds().getSize());
    }

    /**
     * Update the bounds in case of a resize
     */
    public void updateBounds(){
        this.photoComponentUI.updateBounds();
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
