package View;

import Tools.ResizingTracker;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class StatusBar extends JPanel {

    private JLabel imgInfo;

    private JLabel actionInfo;

    /**The space to display messages describing the status of the app*/
    public StatusBar(){
        this.setBorder(new EmptyBorder(0, 10, 0, 10));
        this.setName("Status Bar");
        FlowLayout fl = new FlowLayout();
        fl.setAlignment(FlowLayout.LEADING);
        fl.setHgap(50);
        this.setLayout(fl);

        this.setBackground( new Color(223, 242, 255));

        imgInfo = new JLabel("No image is currently being displayed");
        //imgInfo.setVerticalAlignment(JLabel.CENTER);
        this.add(imgInfo);

        actionInfo = new JLabel("This application is waiting to be used");
        this.add(actionInfo);

        new ResizingTracker(this, true, true);

    }

    /**
     * Display a message on the action information part of the status bar
     * @param message the string message. Display the default message if null
     */
    public void setActionMessage(String message){
        if(message != null){
            this.actionInfo.setText(message);
        } else {
            this.actionInfo.setText("This application is waiting to be used");
        }

    }

    /**
     * Set the status to display the name of the image supposedly being showed on the board
     * @param name if null, display the default message
     */
    public void setCurrentImgName(String name){
        if(name != null){
            this.imgInfo.setText("You selected the picture '" + name + "'. Further implementation coming soon");
        } else {
            this.imgInfo.setText("No image is currently being displayed");
        }
    }


}
