package Widget;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.image.BufferedImage;

public class PhotoComponent extends JComponent {



    PhotoComponentModel photoComponentModel;

    PhotoComponentUI photoComponentUI;

    Dimension dimension;

    private BufferedImage photo;

    private Dimension oldSize;

    /**The component events must be redispatched to*/
    JComponent listener = null;

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
        this.oldSize = this.dimension;

        this.photo = photo;
        this.photoComponentUI = new PhotoComponentUI(this);
        this.photoComponentModel = new PhotoComponentModel(this, photo); //Must be called after UI to have the canvas & photo ready


        this.photoComponentUI.installUI();
    }

    /**
     * Create the photoComponent
     * <br/>Its prefered size will be dependent on the Frame size
     * @param photo the photo to render in the photo Component
     * @param dimension
     * @see Frame
     */
    public PhotoComponent(BufferedImage photo, Dimension dimension){
        this.setFocusable(true);

        this.setVisible(true);
        this.setPreferredSize(dimension);
        this.dimension = dimension;
        this.oldSize = this.dimension;

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

    /**
     *  Create the photoComponent
     * <br/>Its prefered size will be dependent on the Frame size
     * @param photo the photo to render in the photo Component
     * @param position
     * @param dimension
     * @see Frame
     */
    public PhotoComponent(BufferedImage photo, Point position, Dimension dimension){
        this.setFocusable(true);

        this.setPreferredSize(dimension);
        this.dimension = dimension;
        this.oldSize = this.dimension;
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
     * Update the bounds in case of a resize or movement
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

    /**
     * Return the key adapter used by the photoComponent.
     * <br/>Useful because JComponants are not focusable and as such dont receive KeyEvents
     * @return
     */
    public KeyAdapter getKeyListenerInUse(){
        return photoComponentModel.getKeyListenerUI();
    }

    /**
     * Register a listener to which all listeners in the PhotoComponent will redispatch their event to.
     * <br/> All mouseEvent, mouseMotionEvent and mouseKeyEvent are consumed by photoComponent,
     * registering a listener will prevent events from being lost
     * @param listener The component which will receive the events. Must have a listener.
     */
    public void redispatchEventsTo(JComponent listener){
        this.listener = listener;
    }

    /**
     * Allow or not the selection of annotations
     * @param allow
     */
    public void allowSelection(boolean allow){
        this.photoComponentModel.getCanvasController().setSelectionAllowed(allow);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        this.photoComponentUI.paint(g);
    }
}
