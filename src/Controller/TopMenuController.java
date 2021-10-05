package Controller;

import Model.PhotoDataBase;
import View.TopMenu;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TopMenuController {

    MainController ctrl;
    TopMenu topMenu;

    String fileChooserResult;

    public TopMenuController(MainController ctrl){
        this.topMenu = ctrl.mainWindow.topMenu;
        this.ctrl = ctrl;

        setAction();

    }

    /**
     * Set all the actions of the menu items of the top menu
     */
    private void setAction(){

        topMenu.quit.addActionListener( new AbstractAction() {
            public void actionPerformed(ActionEvent a) {
                ctrl.mainWindow.dispose();
                System.exit(0);
            }
        });

        topMenu.importFile.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent a) {
                newPhoto();
            }
        });

        topMenu.delete.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent a) {
                ctrl.mainWindow.statusBar.setActionMessage("You asked to delete an image");
                ctrl.mainWindow.statusBar.setCurrentImgName(null);
                ctrl.mainWindow.board.removeAllPhotoComponent();
            }
        });

        topMenu.photoViewer.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent a) {
                ctrl.mainWindow.statusBar.setActionMessage("You choose to view one picture at a time");
            }
        });

        topMenu.browser.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent a) {
                ctrl.mainWindow.statusBar.setActionMessage("You choose to browse the pictures");
            }
        });
    }

    private void newPhoto(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select a new picture");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        fileChooser.setMultiSelectionEnabled(false);
        fileChooser.setFileFilter(new FileNameExtensionFilter("Images files","png", "jpg", "jpeg"));
        if(fileChooser.showOpenDialog(topMenu.importFile)==JFileChooser.APPROVE_OPTION){
            fileChooserResult = fileChooser.getSelectedFile().getPath();
            ctrl.mainWindow.statusBar.setCurrentImgName(fileChooser.getSelectedFile().getName());

            BufferedImage origImage = null;
            try {
                origImage = ImageIO.read(fileChooser.getSelectedFile());
                if(origImage == null){
                    JOptionPane.showInputDialog("New image didnt load correctly !!");
                    System.out.println("new image null in file chooser !!");
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            PhotoDataBase.PhotoData newPhotoData = new PhotoDataBase.PhotoData(fileChooser.getSelectedFile().getPath(), origImage);
            newPhotoData = PhotoDataBase.registerNewPhotoData(newPhotoData);
            this.ctrl.mainWindow.board.addNewPhoto(newPhotoData);
        }
    }
}
