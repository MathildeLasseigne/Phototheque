import Controller.MainController;
import View.MainWindow;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {
        MainWindow frame = new MainWindow();
        MainController ctrl = new MainController(frame);
        frame.setVisible(true);

    }

}
