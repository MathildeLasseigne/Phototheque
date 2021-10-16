package Tools;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Utilities {

    public static Point2D pointToPoint2D(Point p){
        return new Point2D.Double(p.getX(), p.getY());
    }

    public static Point point2DToPoint(Point2D p){
        return new Point((int) p.getX(), (int) p.getY());
    }

    /**
     * Return the bounds of the component relative to the screen
     * @param component
     * @return
     */
    public static Rectangle getBoundsOnScreen(JComponent component){
        Point locationOnScreen = component.getLocationOnScreen();
        return new Rectangle(locationOnScreen, component.getBounds().getSize());
    }


    /**
     * Create a BufferedImage from a path leading to the image
     * @param pathName the path
     * @return the BufferedImage found. Raise an exception if the image couldn't be found
     */
    public static BufferedImage getBIfromPath(String pathName) {
        BufferedImage bi = null;
        try{
            File c = new File(pathName);
            bi = ImageIO.read(c);
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("Image couldn't be read");
        } catch (Exception e){
            e.printStackTrace();
        }
        if(bi.getHeight()==-1 || bi.getWidth()==-1){
            throw new RuntimeException("Image couldn't be read");
        }

        return bi;
    }


    /**
     * Resize the bi while keeping the proportions. The scale will be made depending on the biggest side of the bi.
     * <br/> This method will fit the original image into the given rectangle (created from the height and width given)
     * @param bi - source image to scale
     * @param width - desired width
     * @param height - desired height
     * @return - the new resized image
     */
    public static BufferedImage resizeBIWithProportion(BufferedImage bi, int width, int height){
        //Calculate width & height for proportion
        int w = 0,h = 0; //The new sizes
        //Biggest side must fit into its new size. The other side is changed in proportion
        if(bi.getWidth() > bi.getHeight()){
            w = width;
            double scale = w/(double) bi.getWidth();//bi.getWidth() * scale = w;
            h = (int) Math.round(bi.getHeight() * scale);
            if(h>height){ //If it protrude out of the given bounds. Limit extension to height
                h = height;
                double scaleBis = h/(double) bi.getHeight();//bi.getHeight() * scale = h;
                w = (int) Math.round(bi.getWidth() * scaleBis);
            }
        } else {
            h = height;
            double scale = h/(double) bi.getHeight();//bi.getHeight() * scale = h;
            w = (int) Math.round(bi.getWidth() * scale);
            if(w>width){//If it protrude out of the given bounds. Limit extension to width
                w = width;
                double scaleBis = w/(double) bi.getWidth();//bi.getWidth() * scale = w;
                h = (int) Math.round(bi.getHeight() * scaleBis);
            }
        }

        //Do resizing
        BufferedImage resizedImg = new BufferedImage(w, h, BufferedImage.TRANSLUCENT);
        Graphics2D g2 = resizedImg.createGraphics();
        g2.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        g2.drawImage(bi, 0, 0, w, h, null);
        g2.dispose();
        return resizedImg;
    }

    public static Rectangle2D scale(Rectangle2D original, double scale){
        double newWidth = original.getWidth() * scale;
        double newHeight = original.getHeight() * scale;
        double newX = original.getCenterX() - (newWidth/2);
        double newY = original.getCenterY() - (newHeight/2);
        return new Rectangle2D.Double(newX, newY, newWidth, newHeight);
    }

}
