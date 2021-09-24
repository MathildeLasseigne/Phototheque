package Widget;

import Tools.ClickListener;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class PhotoComponentModel {

    PhotoComponent photoComponent;

    BufferedImage photo;

    /**
     * True means that the canvas is displayed, false that the photo is displayed
     */
    private boolean isFlipped = false;

    private CanvasController canvasController;

    private MouseAdapter mouseListenerUI;


    public PhotoComponentModel(PhotoComponent photoComponent, BufferedImage photo){
        this.photoComponent = photoComponent;
        this.photo = photo;
        this.canvasController = new CanvasController(this.photoComponent.photoComponentUI.canvas);

        createMouseAdapter();

    }

    /**
     * Flip the photo to the specified side
     */
    public void setFlipped(boolean flipped){
        isFlipped = flipped;
    }

    /**
     * Flip the photo to the other side
     */
    public void flip(){
        setFlipped(! isFlipped());
        this.photoComponent.repaint();
    }

    /**
     * Check if the back of the photo or the photo itself is displayed
     * @return True means that the canvas is displayed, false that the photo is displayed
     */
    public boolean isFlipped() {
        return isFlipped;
    }

    /**
     * Get the size of the photo
     * @return
     */
    public Dimension getPhotoSize() {
        return new Dimension(this.photo.getWidth(), this.photo.getHeight());
    }


    /**
     * Create a mouseAdapter that implement clickListener
     */
    private void createMouseAdapter(){

        this.mouseListenerUI = new ClickListener(){
            public void singleClick(MouseEvent e)
            {
                //System.out.println("single");
                if(isFlipped()){
                    canvasController.onMouseClicked(e);
                    photoComponent.repaint();
                }
            }

            public void doubleClick(MouseEvent e)
            {
                System.out.println("Flip");
                flip();
                photoComponent.repaint();

            }

            public void mousePressed(MouseEvent e)
            {
                //System.out.println("mouse pressed");
                if(isFlipped()){
                    canvasController.onMousePressed(e);
                }
            }

            public void mouseReleased(MouseEvent e)
            {
                //System.out.println("mouse released");
                if(isFlipped()){
                    canvasController.onMouseReleased(e);
                    photoComponent.repaint();
                }
            }

            public void mouseDragged(MouseEvent e) {
                //System.out.println("Mouse dragged");
                if(isFlipped()){
                    canvasController.onMouseDragged(e);
                    photoComponent.repaint();
                }
            }

        };


    }

    /**
     * Return the mouse listener attached to the UI
     * @return
     */
    public MouseAdapter getMouseListenerUI() {
        return mouseListenerUI;
    }

}
