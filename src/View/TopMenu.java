package View;

import Tools.ResizingTracker;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

public class TopMenu extends JMenuBar {

    /**One of the 2 mains menus*/
    private JMenu file, view;

    /**The menus items of the file menu*/
    public JMenuItem importFile, delete, quit;
    /**The menus items of the view menu*/
    public JRadioButtonMenuItem photoViewer, browser;

    private ImageIcon singleImage, gallery;

    /**
     * The main menu managing thins like files and display of the pictures
     */
    public TopMenu(){
        super();
        this.setName("Top Menu");
        loadImages();
        setMenus();
        //super.setMargin(new Insets(100,100, 100,100));
        super.setBorder(new EmptyBorder(3, 10, 1, 10));
        super.setMinimumSize(new Dimension(400,21));

        new ResizingTracker(this, true, true);

    }


    /**
     * Set the 2 menus of the TopMenu
     */
    private void setMenus(){
        EmptyBorder menuBorder = new EmptyBorder(0, 5, 0, 5);
        file = new JMenu("<html><p style='text-align:center;width:50px;'>File</p></html>");
        file.setBorder(menuBorder);
        super.add(file);
        importFile = new JMenuItem("Import");
        delete = new JMenuItem("Delete");
        quit = new JMenuItem("Quit");
        file.add(importFile);
        file.add(delete);
        file.addSeparator();
        file.add(quit);

        file.setMinimumSize(new Dimension(50,30));

        addSpace(100);

        view = new JMenu("<html><p style='text-align:center;width:50px;'>View</p></html>");
        view.setBorder(menuBorder);
        super.add(view);
        photoViewer = new JRadioButtonMenuItem("Photo Viewer", singleImage);
        photoViewer.setHorizontalTextPosition(JMenuItem.RIGHT);
        photoViewer.setSelected(true);
        browser = new JRadioButtonMenuItem("Browser", gallery);
        browser.setHorizontalTextPosition(JMenuItem.RIGHT);
        ButtonGroup group = new ButtonGroup();
        group.add(photoViewer);
        group.add(browser);
        view.add(photoViewer);
        view.add(browser);

        view.setMinimumSize(new Dimension(50,30));

        view.setDelay(3000);
    }

    /**
     * Load & resize the icons and put them in the <i>gallery</i> and <i>singleImage</i> variables
     */
    private void loadImages(){
        String path = "/Sprites/";
        int width = 30;
        int height = 30;
        try{
            ImageIcon galleryTemp = new ImageIcon(getClass().getResource(path+"medium-icons.png"));
            ImageIcon singleImageTemp = new ImageIcon(getClass().getResource(path+"image-gallery.png"));
            gallery = new ImageIcon(galleryTemp.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
            singleImage = new ImageIcon(singleImageTemp.getImage().getScaledInstance(width, height, Image.SCALE_DEFAULT));
        } catch(Exception e){e.printStackTrace();}
    }

    /**
     * Add space on the buttonBar.
     * <br/>This method add an empty JMenu not able to be selected to the JMenuBar
     * @param width the width of the space
     */
    private void addSpace(int width){
        JMenu space = new JMenu();
        space.setEnabled(false);
        space.setPreferredSize(new Dimension(width,1));
        super.add(space);
    }

}
