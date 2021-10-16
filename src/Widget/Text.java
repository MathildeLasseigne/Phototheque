package Widget;

import java.awt.*;
import java.awt.geom.Area;
import java.util.ArrayList;

public class Text extends Drawable{

    private Point originPoint;

    private String text = "";

    private boolean open = true;

    private Rectangle windowBounds;

    private ArrayList<String> savedLines;

    public Text(Point originPoint, Rectangle windowBounds){
        this.originPoint = originPoint;
        this.windowBounds = windowBounds;
    }


    public void addNewChar(char newChar){
        if(! isClosed()){
            this.text += newChar;
            savedLines = null; //Remove old saved lines to calculate new ones
        }
    }

    public void deleteLastChar(){
        if(! isClosed()){
            if(text.length() != 0){
                this.text = text.substring(0, text.length() - 1);
                savedLines = null; //Remove old saved lines to calculate new ones
            }
        }
    }

    @Override
    public void closeDrawable(){
        this.open = false;
    }

    /**
     * Open the text, make it editable again.
     * <br/> Use open and close for security in drawing
     */
    @Override
    public void openDrawable() {
        this.open = true;
    }

    /**
     * Check if the drawable is closed
     * @return
     */
    public boolean isClosed() {
        return ! open;
    }

    /**
     * Cut the word list into lines according to the String properties and the position in the bounds
     * @param words the list of words from the line
     * @param g the graphic to analyse the String font
     * @return
     */
    private ArrayList<String> parseLine(String[] words, Graphics g){
        Point currentPoint = this.originPoint;

        ArrayList<String> parsedLines = new ArrayList<>();
        String line = "";
        int workingWidth = (this.windowBounds.x + this.windowBounds.width) - currentPoint.x; //The maximal width of the string
        for(int i = 0; i< words.length; i++){
            String tmpLine = line + words[i];
            if(g.getFontMetrics().stringWidth(tmpLine)< workingWidth){
                line +=  words[i];
            } else {
                parsedLines.add(line); //Save the line
                line = words[i]; //Initialise the new line
            }
            if(i == words.length-1){ //If last word of the text, add to array
                parsedLines.add(line);
            }
        }
        return parsedLines;
    }

    @Override
    public void draw(Graphics g) {
        Point currentPoint = (Point) this.originPoint;
        Graphics2D g2 = (Graphics2D) g;
        Color oldColor = g2.getColor();
        g2.setColor(Color.BLACK);

        if(isSelected()){
            g2.draw(getBoundingShape());
        }

        //To show text position even if empty
        if(open && this.text.isEmpty()){
            g2.drawRect(currentPoint.x-1, currentPoint.y-g2.getFontMetrics().getAscent(), 1, g2.getFontMetrics().getHeight());
        }
        g2.setColor(oldColor);
        if(! this.text.isEmpty()){
            if(savedLines == null){ //There has been a change -> recalculate
                savedLines = this.parseLine(parseInputToWordsArray(text), g2);
                setBoundingShape(new Area(new Rectangle(currentPoint.x-1, currentPoint.y-g2.getFontMetrics().getAscent(),
                        g2.getFontMetrics().stringWidth(savedLines.get(0))+1, g2.getFontMetrics().getHeight()*savedLines.size())));
            }
            //ArrayList<String> lines = this.parseLine(parseInputToWordsArray(text), g);
            int newY = currentPoint.y;
            for(String line : savedLines){
                g.drawString(line, currentPoint.x, newY);

                newY += g2.getFontMetrics().getHeight();
            }
            if(open || isSelected()){
                /*g.drawRect(currentPoint.x-1, currentPoint.y-g.getFontMetrics().getAscent(),
                        g.getFontMetrics().stringWidth(savedLines.get(0))+1, g.getFontMetrics().getHeight()*savedLines.size());
                  */
                g2.draw(getBoundingShape());
            }
        }


    }


    /**
     * Parse the string (the line) into words. All spaces and dots are kept but the String is trimmed
     * <br/> The following strings
     * <ul>
     *     <li>"Hello, my name is Blanc! I'm a student. Can you help me? Thanks."</li>
     *     <li>"      Hello, my name is Blanc! I'm a student. Can you help me? Thanks.     "</li>
     * </ul>
     *  will be parsed the same way :
     * <br/> "|Hello, |my |name |is |Blanc!| |I'm |a |student.| |Can |you |help |me?| |Thanks.|"
     * @param input the string to parse
     * @return the words of the input as an array
     * @see String#split(String)
     * @see String#trim()
     */
    public static String[] parseInputToWordsArray(String input) {
        return input.trim().split("(?<= )|(?<=\\.)|(?<=\\?)|(?<=!)|(?<=;)");
    }
}
