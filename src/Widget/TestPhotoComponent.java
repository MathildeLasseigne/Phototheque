package Widget;

import Controller.MainController;
import Tools.Utilities;
import View.MainWindow;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;

public class TestPhotoComponent {


    public static void main(String[] args)
    {
        JFrame frame = new JFrame( "PhotoComponent test" );
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        frame.setLayout(new BorderLayout());
        JPanel p1 = new JPanel();
        p1.setPreferredSize(new Dimension(Integer.MAX_VALUE, 100));
        p1.setBackground(Color.GREEN);
        frame.add(p1, BorderLayout.NORTH);

        JPanel p2 = new JPanel();
        p2.setPreferredSize(new Dimension(Integer.MAX_VALUE, 100));
        p2.setBackground(Color.GREEN);
        frame.add(p2, BorderLayout.SOUTH);

        JPanel p3 = new JPanel();
        p3.setPreferredSize(new Dimension(100, Integer.MAX_VALUE));
        p3.setBackground(Color.GREEN);
        frame.add(p3, BorderLayout.WEST);

        JPanel p4 = new JPanel();
        p4.setPreferredSize(new Dimension(100, Integer.MAX_VALUE));
        p4.setBackground(Color.GREEN);
        frame.add(p4, BorderLayout.EAST);

        BufferedImage img = Utilities.getBIfromPath("src/Sprites/Icone_d_image_color.png");

        PhotoComponent pc = new PhotoComponent(img);
        pc.setFrame(new Frame(new Insets(20,20,20,20)));

        JScrollPane sp = new JScrollPane(pc);
        sp.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        sp.getViewport().setBackground(new Color(200, 173, 127));

        frame.add(sp, BorderLayout.CENTER);

        //pc.repaint();


        //frame.setSize(400, 400);
        frame.setPreferredSize(new Dimension(500,500));
        frame.pack();
        frame.setVisible(true);
    }


}
