package View;

import Model.PhotoDataBase;
import Tools.Utilities;
import Widget.Frame;
import Widget.PhotoComponent;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Board extends JScrollPane {

    /**The JPanel with the content of the Board*/
    private JPanel contentBoard;

    private MainWindow window;

    private ArrayList<PhotoDataBase.PhotoData> photoList = new ArrayList<>();

    private PhotoDataBase.PhotoData selectedPhoto = null;

    /**The place where the pictures are displayed*/
    public Board(MainWindow window){
        super(new JPanel(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.window = window;
        this.setMinimumSize(new Dimension(500, 500));

        this.getViewport().setBackground(Color.LIGHT_GRAY);

        this.contentBoard = (JPanel) this.getViewport().getView();

        this.contentBoard.setMinimumSize(new Dimension(500, 500));
        Dimension boardPrefSize = new Dimension((int) (window.getPreferredSize().getWidth()/2), (int) (window.getPreferredSize().getHeight()/2));
        this.contentBoard.setPreferredSize(boardPrefSize);
        //this.contentBoard.setMaximumSize(boardPrefSize);
        this.contentBoard.setBackground(Color.LIGHT_GRAY);
        this.contentBoard.setFocusable(true);

        //setMouseListener();
        //setKeyListener();

        addPhotoComponent();
    }


    /**
     * Add the photoData photoComponent to the viewPort and create it if necessary
     * @param photoData the photo to add
     */
    public void addNewPhoto(PhotoDataBase.PhotoData photoData){
        PhotoComponent pc = photoData.getPhotoComponent();
        if(pc == null){
            pc = new PhotoComponent(photoData.getOriginalPhoto());
            pc.setFrame(new Frame(new Insets(20,20,20,20)));
            photoData.registerPhotoComponent(pc);
        }
        pc.redispatchEventsTo(contentBoard);
        this.photoList.add(photoData);
        //this.setViewportView(pc);
        this.contentBoard.add(pc);
    }

    public void addPhotoComponent(){
        BufferedImage img = Utilities.getBIfromPath("src/Sprites/Icone_d_image_color.png");

        PhotoComponent pc = new PhotoComponent(img);
        pc.setFrame(new Frame(new Insets(20,20,20,20)));

        //this.setViewportView(pc);
        PhotoDataBase.PhotoData pd = new PhotoDataBase.PhotoData("path", null);
        pd.registerPhotoComponent(pc);
        pc.redispatchEventsTo(contentBoard);
        this.photoList.add(pd);
        this.contentBoard.add(pc);
    }

    /**
     * Change the current view to the default one
     */
    public void removeAllPhotoComponent(){
        this.photoList.clear();
        this.contentBoard.removeAll();
    }

    /**
     * Change the current view to the default one
     */
    public void removePhotoComponent(PhotoDataBase.PhotoData photoData){
        //this.setViewportView(this.contentBoard);
        this.photoList.remove(photoData);
        this.contentBoard.remove(photoData.getPhotoComponent());
        if(selectedPhoto == photoData){
            selectedPhoto = null;
        }
    }


    /**
     * When mouse press on a photoComponent, select it. If not photoComponent, select = null.
     * <br/>Setup for key listener management
     */
    void setMouseListener(){
        this.contentBoard.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                boolean found = false;
                //System.out.println("Board pressed");
                for(PhotoDataBase.PhotoData photoData : photoList){
                    //Necessary because photoComponent parent is not the same as the listener
                    if(getBoundsOnScreen(photoData.getPhotoComponent()).contains(e.getLocationOnScreen())){
                        if(selectedPhoto != photoData){
                            selectedPhoto = photoData;
                        }
                        found = true;
                        break;
                    }
                }
                if(! found){
                    selectedPhoto = null;
                }

            }
        });
    }


    /**
     * Redirect key events to the selected photo.
     * <br/>A JFrame is necessary to catch the events
     * @param mainWindow the JFrame which catch the event
     */
    void setKeyListener(MainWindow mainWindow){
        mainWindow.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                //System.out.println("Board Key typed");

                if(selectedPhoto != null){
                    selectedPhoto.getPhotoComponent().getKeyListenerInUse().keyTyped(e);
                }


            }
        });
    }

    /**
     * Return the bounds of the component relative to the screen
     * @param component
     * @return
     */
    private Rectangle getBoundsOnScreen(JComponent component){
        Point locationOnScreen = component.getLocationOnScreen();
        return new Rectangle(locationOnScreen, component.getBounds().getSize());
    }

}
