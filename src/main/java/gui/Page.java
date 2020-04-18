package gui;

import javax.swing.*;
import java.awt.*;

public abstract class Page extends JFrame {
    protected final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    protected final static int SIZE_WIDTH = screenSize.width / 2;
    protected final static int SIZE_HEIGHT = screenSize.height /2;
    protected final static int LOCATION_X = (screenSize.width - SIZE_WIDTH) / 2;
    protected final static int LOCATION_Y = (screenSize.height - SIZE_HEIGHT) / 2;

    protected Page(String name) {
        super(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = getContentPane();
        container.setLayout(new GridLayout(0,1));
    }

}
