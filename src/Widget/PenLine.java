package Widget;

import Tools.Utilities;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class PenLine extends Drawable {

    private ArrayList<Point> points = new ArrayList<>();

    private ArrayList<Line2D> lines = new ArrayList<>();

    private boolean open = true;

    /**The whole line of the penLine*/
    private Area lineShape;

    public PenLine(Point originePoint){
        this.points.add(originePoint);
        //Initialize the shape to a a single point at the origin point
        this.lineShape  = new Area(new Ellipse2D.Double(originePoint.getX(), this.points.get(0).getY(),1,1));
    }

    /**
     * Create a copy of the penLine
     * @param penline
     */
    private PenLine(PenLine penline){
        this.lineShape = penline.getShape();
        //Since the draw method only draw the shape, no other information is needed
    }

    /**
     * Add a new to position to line to
     * @param newPoint
     */
    @Override
    public void addNewPosition(Point newPoint) {
        if(open){
            //Create a new line from the last point to the new one
            Line2D newLine = new Line2D.Double(Utilities.pointToPoint2D(this.points.get(this.points.size()-1)),Utilities.pointToPoint2D(newPoint));
            this.lines.add(newLine);
            //Add point to all the points
            points.add(newPoint);


            updateShape(newLine);
        }
    }

    /**
     * Add a line to current area
     * @param addedShape
     */
    private void updateShape(Shape addedShape){
        this.lineShape.add(new Area(addedShape));
    }

    /**
     * Return a clone of the shape of the PenLine
     * @return
     */
    public Area getShape() {
        return (Area) lineShape.clone(); //To avoid modifing the shape from outside
    }

    /**
     * Close the PenLine, make it not editable
     */
    @Override
    public void closeDrawable() {
        this.open = false;
    }

    /**
     * Check if the drawable is closed
     * @return
     */
    public boolean isClosed() {
        return ! open;
    }

    /**
     * Copy the penLine
     * @return
     */
    public PenLine copy(){
        return new PenLine(this);
    }

    /**
     * Transforme the shape of the PenLine. Only work if the PenLine is closed
     * <br/>If the penline is open, do nothing
     * @param affineTransform
     */
    public void transform(AffineTransform affineTransform){
        if(this.isClosed()){
            this.lineShape.transform(affineTransform);
        }
    }

    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        if(points.size() <= 1){
            //g2.drawOval(points.get(0).x, points.get(0).y, 1,1 );
            g2.fill(this.lineShape);
        } else {
            for(Line2D l : this.lines){
                g2.draw(l);
            }
            /*Point last = points.get(0);
            for(int i = 1; i< points.size(); i++){
                Point current = points.get(i);
                g2.drawLine(last.x,last.y, current.x, current.y);
                last = current;
            }

             */
        }


        //g2.draw(this.lineShape);

    }

}
