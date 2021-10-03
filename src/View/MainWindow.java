package View;

import Tools.ResizingTracker;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainWindow extends JFrame {

    /**The size of the screen used by the computer running the app*/
    private static Rectangle dimScreen = GraphicsEnvironment.getLocalGraphicsEnvironment().getMaximumWindowBounds();

    //The components of the main window
    public TopMenu topMenu;
    public Board board;
    public StatusBar statusBar;
    public ToolsBar toolsBar;

    /**
     * The main window of the application
     */
    public MainWindow(){
        super("Phototheque");
        super.setMinimumSize(new Dimension(620, 225));
        super.setPreferredSize(dimScreen.getSize());
        super.setMaximumSize(dimScreen.getSize());
        super.setFocusable(true);

        super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ResizingTracker c;
        new ResizingTracker(this, true, true);

        //The main layout
        BorderLayout layout = new BorderLayout();
        super.setLayout(layout);

        try{
            super.setIconImage(ImageIO.read(getClass().getResource("/Sprites/phototheque.png")));//Use an image for the windows
        }catch(IOException e){e.printStackTrace();}

        setComponents();
        super.pack();
    }

    /**
     * Set the differents components used in the main window, such as the menus
     */
    private void setComponents(){
        topMenu = new TopMenu();
        super.add(topMenu, BorderLayout.NORTH);

        statusBar = new StatusBar();
        super.add(statusBar, BorderLayout.SOUTH);

        toolsBar = new ToolsBar();
        super.add(toolsBar, BorderLayout.WEST);

        board = new Board(this);
        super.add(board, BorderLayout.CENTER);
        board.setMouseListener();
        board.setKeyListener(this);
    }

}
