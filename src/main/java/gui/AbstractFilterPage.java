package gui;

import javafx.application.Application;
import javafx.stage.Stage;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractFilterPage extends AbstractPageWithTable {
    protected final static int SIZE_WIDTH = 500;
    protected final static int SIZE_HEIGHT = 1000;
    protected final static int LOCATION_X = (screenSize.width - SIZE_WIDTH) / 2;
    protected final static int LOCATION_Y = (screenSize.height - SIZE_HEIGHT) / 2;
    protected final JButton okButton = new JButton("ok");

    protected AbstractFilterPage(String name) {
        super(name);
        setBounds(LOCATION_X, LOCATION_Y, SIZE_WIDTH, SIZE_HEIGHT);
        Container container = getContentPane();
        container.setLayout(new BoxLayout(container, BoxLayout.Y_AXIS));
    }

    protected Integer parseInt(String string){
        if (string.isEmpty()){
            return null;
        }

        return Integer.parseInt(string);
    }


}
