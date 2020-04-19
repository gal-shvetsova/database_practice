package gui;

import javax.swing.*;
import java.awt.*;

public abstract class Page extends JFrame {
    protected final static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

    protected Page(String name) {
        super(name);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Container container = getContentPane();
        container.setLayout(new GridLayout(0,1));
    }

}
