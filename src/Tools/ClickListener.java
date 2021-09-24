package Tools;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * This class allow the monitoring of double clicks.
 * </br> It is to be used to prevent a double click to fire both single click and double click action.
 * </br> <a href="https://stackoverflow.com/questions/4577424/distinguish-between-a-single-click-and-a-double-click-in-java">Find the source code here</a>
 */
public class ClickListener extends MouseAdapter implements ActionListener
{
    private final static int clickInterval = (Integer)Toolkit.getDefaultToolkit().
            getDesktopProperty("awt.multiClickInterval");

    private MouseEvent lastEvent;
    private Timer timer;

    /**
     * This class allow the monitoring of double clicks.
     * </br> It is to be used to prevent a double click to fire both single click and double click action.
     */
    public ClickListener()
    {
        this(clickInterval);
    }

    /**
     * This class allow the monitoring of double clicks.
     * </br> It is to be used to prevent a double click to fire both single click and double click action.
     * @param delay The delay in miliseconds to add to the default one to wait for the second click
     */
    public ClickListener(int delay)
    {
        timer = new Timer( delay, this);
    }

    public void mouseClicked (MouseEvent e)
    {
        if (e.getClickCount() > 2) return;

        lastEvent = e;

        if (timer.isRunning())
        {
            timer.stop();
            doubleClick( lastEvent );
        }
        else
        {
            timer.restart();
        }
    }

    public void actionPerformed(ActionEvent e)
    {
        timer.stop();
        singleClick( lastEvent );
    }

    /**
     * Action fired when single click happen
     * @param e
     */
    public void singleClick(MouseEvent e) {}

    /**
     * Action fired when double click happen
     * @param e
     */
    public void doubleClick(MouseEvent e) {}

}

