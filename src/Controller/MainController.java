package Controller;

import View.MainWindow;

public class MainController {

    MainWindow mainWindow;

    TopMenuController topMenuController;
    ToolBarController toolBarController;

    /**
     * The main Controller. Create all the others and permit access to other class
     */
    public MainController(MainWindow mainWindow){
        this.mainWindow = mainWindow;

        topMenuController = new TopMenuController(this);
        toolBarController = new ToolBarController(this);
    }

}
