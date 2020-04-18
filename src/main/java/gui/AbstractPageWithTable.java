package gui;

import model.Facility;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class AbstractPageWithTable extends Page {
    protected final static int SIZE_WIDTH = 500;
    protected final static int SIZE_HEIGHT = 500;
    protected final static int LOCATION_X = (screenSize.width - SIZE_WIDTH) / 2;
    protected final static int LOCATION_Y = (screenSize.height - SIZE_HEIGHT) / 2;
    protected final JButton editButton = new JButton("Edit");

    public AbstractPageWithTable(String name) {
        super(name);
        setBounds(LOCATION_X, LOCATION_Y, SIZE_WIDTH, SIZE_HEIGHT);
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
    }

    protected void updateTable(){
        throw new UnsupportedOperationException();
    }

    @Override
    public void setVisible(boolean b) {
        updateTable();
        super.setVisible(b);
    }
}
