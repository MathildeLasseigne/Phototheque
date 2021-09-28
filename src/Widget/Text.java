package Widget;

import java.awt.*;
import java.util.ArrayList;

public class Text extends Drawable{

    private Point originPoint;

    private String text = "";

    private boolean open = true;

    private Rectangle windowBounds;

    public Text(Point originPoint, Rectangle windowBounds){
        this.originPoint = originPoint;
        this.windowBounds = windowBounds;
    }


    public void addNewChar(char newChar){
        if(! isClosed()){
            this.text += newChar;
        }
    }

    public void deleteLastChar(){
        if(! isClosed()){
            if(text.length() != 0){
                this.text = text.substring(0, text.length() - 1);
            }
        }
    }

    @Override
    public void closeDrawable(){
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
     * Cut the word list into lines according to the String properties and the position in the bounds
     * @param words the list of words from the line
     * @param g the graphic to analyse the String font
     * @return
     */
    private ArrayList<String> parseLine(String[] words, Graphics g){
        ArrayList<String> parsedLines = new ArrayList<>();
        String line = "";
        int workingWidth = (this.windowBounds.x + this.windowBounds.width) - this.originPoint.x; //The maximal width of the string
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

        if(! this.text.isEmpty()){
            ArrayList<String> lines = this.parseLine(parseInputToWordsArray(text), g);
            int newY = this.originPoint.y;
            for(String line : lines){
                g.drawString(line, this.originPoint.x, newY);

                newY += g.getFontMetrics().getHeight();
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
