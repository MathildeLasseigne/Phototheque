package Controller;

import View.ToolsBar;

import javax.swing.*;
import java.awt.event.ActionEvent;

public class ToolBarController {

    private MainController ctrl;
    private ToolsBar toolsBar;

    public ToolBarController(MainController ctrl){
        this.ctrl = ctrl;
        this.toolsBar = ctrl.mainWindow.toolsBar;
        setActions();
    }

    /**
     * Set all the actions of the toolbar
     */
    private void setActions(){
        toolsBar.school.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent a) {
                ctrl.mainWindow.statusBar.setActionMessage("You currently have these options selectionned : "+toolsBar.getSelectedOption());
            }
        });

        toolsBar.places.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent a) {
                ctrl.mainWindow.statusBar.setActionMessage("You currently have these options selectionned : "+toolsBar.getSelectedOption());
            }
        });

        toolsBar.people.addActionListener(new AbstractAction() {
            public void actionPerformed(ActionEvent a) {
                ctrl.mainWindow.statusBar.setActionMessage("You currently have these options selectionned : "+toolsBar.getSelectedOption());
            }
        });
    }
}
