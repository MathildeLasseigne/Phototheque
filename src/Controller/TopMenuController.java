package Controller;

import View.TopMenu;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;

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
                JFileChooser fileChooser = new JFileChooser();
                //fileChooser.setCurrentDirectory(new File("C:\\Users\\hamee\\Downloads"));
                fileChooser.setDialogTitle("Select a new picture");
                fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
                fileChooser.setFileFilter(new FileNameExtensionFilter("Images files","png", "jpg"));
                if(fileChooser.showOpenDialog(topMenu.importFile)==JFileChooser.APPROVE_OPTION){
                    fileChooserResult = fileChooser.getSelectedFile().getPath();
                    ctrl.mainWindow.statusBar.setCurrentImgName(fileChooser.getSelectedFile().getName());
                }
            }
        });

        topMenu.delete.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent a) {
                ctrl.mainWindow.statusBar.setActionMessage("You asked to delete an image");
                ctrl.mainWindow.statusBar.setCurrentImgName(null);
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
}
