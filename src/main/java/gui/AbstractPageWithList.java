package gui;

import javax.swing.*;
import java.awt.*;

public class AbstractPageWithList extends Page {
    protected final static int SIZE_WIDTH = 600;
    protected final static int SIZE_HEIGHT = 300;
    protected final static int LOCATION_X = (screenSize.width - SIZE_WIDTH) / 2;
    protected final static int LOCATION_Y = (screenSize.height - SIZE_HEIGHT) / 2;
    protected final JButton editButton = new JButton("Edit");

    public AbstractPageWithList(String name) {
        super(name);
        setBounds(LOCATION_X, LOCATION_Y, SIZE_WIDTH, SIZE_HEIGHT);
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
    }

    protected void updateList(){
        throw new UnsupportedOperationException();
    }

    @Override
    public void setVisible(boolean b) {
        updateList();
        super.setVisible(b);
    }
}
