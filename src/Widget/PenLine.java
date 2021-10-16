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

    private Rectangle displayBounds = new Rectangle();

    public PenLine(Point originePoint){
        this.points.add(originePoint);
        //Initialize the shape to a a single point at the origin point
        super.setBoundingShape(new Area(new Ellipse2D.Double(originePoint.getX(), this.points.get(0).getY(),1,1)));
    }

    /**
     * Create a copy of the penLine
     * @param penline
     */
    private PenLine(PenLine penline){
        super.setBoundingShape(penline.getBoundingShape());
        this.points = (ArrayList<Point>) penline.points.clone();
        this.lines = (ArrayList<Line2D>) penline.lines.clone();
        update();
        closeDrawable();

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
            calculateDisplayBounds();
        }
    }

    /**
     * Add bounds2D of the shape to current boundingShape, with operation addition of Area.
     * @param addedShape
     * @see Area#add(Area)
     */
    private void updateShape(Shape addedShape){
        AffineTransform aff = new AffineTransform();
        aff.scale(2,2); //Scale up for more facility in selection
        Shape customScaledShape = Utilities.scale(addedShape.getBounds2D(), 5);
        super.getBoundingShape().add(new Area(customScaledShape));
    }

    /**
     * Return a clone of the shape of the PenLine
     * @return
     */
    public Area getShape() {
        return (Area) super.getBoundingShape().clone(); //To avoid modifing the shape from outside
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
     * Calculate the rectangle containing all the points
     */
    public void calculateDisplayBounds(){
        Point min = getMinCoord();
        Point max = getMaxCoord();
        int border = 4;
        Dimension rect = new Dimension(max.x - min.x+border, max.y- min.y+border);
        this.displayBounds = new Rectangle(new Point(min.x-(border/2), min.y-(border/2)), rect);
    }


    /**
     * Return the min x and y for all points.
     * Return the top left point of the rectangle containing the whole drawing
     * @return
     */
    private Point getMinCoord(){
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        for(Point p : points){
            if(p.x < minX){
                minX = p.x;
            }
            if(p.y < minY){
                minY = p.y;
            }
        }
        return new Point(minX, minY);
    }

    /**
     * Return the bottom right point of the rectangle containing the whole drawing
     * @return
     */
    private Point getMaxCoord(){
        int maxX = 0;
        int maxY = 0;
        for(Point p : points){
            if(p.x > maxX){
                maxX = p.x;
            }
            if(p.y > maxY){
                maxY = p.y;
            }
        }
        return new Point(maxX, maxY);
    }

    /**
     * Update the bounds of the drawable. To call once the drawable is closed
     */
    void update(){
        super.setBoundingShape(new Area()); //Reset the shape
        for(Line2D l : lines){
            updateShape(l);
        }
        calculateDisplayBounds();
    }

    public void draw(Graphics g){
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
        if(isSelected()){
            g2.draw(this.displayBounds);
        }
        if(points.size() <= 1){
            //g2.drawOval(points.get(0).x, points.get(0).y, 1,1 );
            g2.fill(super.getBoundingShape());
        } else {
            for(Line2D l : this.lines){
                g2.draw(l);
            }
            //g2.draw(getBoundingShape()); //Debug


            /*g2.setStroke(new BasicStroke(4)); //Do not work, bounds cant have rotation -> really ugly
            g2.fill(this.lineShape);*/
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
