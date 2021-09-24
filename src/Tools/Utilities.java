package Tools;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.Point2D;
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

}
