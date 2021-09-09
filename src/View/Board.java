package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class Board extends JScrollPane {

    /**The JPanel with the content of the Board*/
    private JPanel contentBoard;

    private MainWindow window;

    /**The place where the pictures are displayed*/
    public Board(MainWindow window){
        super(new JPanel(), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.window = window;
        this.setMinimumSize(new Dimension(500, 500));

        this.contentBoard = (JPanel) this.getViewport().getView();

        this.contentBoard.setMinimumSize(new Dimension(500, 500));
        Dimension boardPrefSize = new Dimension((int) (window.getPreferredSize().getWidth()/2), (int) (window.getPreferredSize().getHeight()/2));
        this.contentBoard.setPreferredSize(boardPrefSize);
        this.contentBoard.setMaximumSize(boardPrefSize);
        System.out.println("Board pref size : "+new Dimension((int) (window.getPreferredSize().getWidth()/2), (int) (window.getPreferredSize().getHeight()/2)));
        this.contentBoard.setBackground(Color.LIGHT_GRAY);
    }
}
