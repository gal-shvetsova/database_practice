package gui.addEditPages;

import gui.Page;

import javax.swing.*;
import java.awt.*;

public abstract class AddEditPage<T> extends Page {
    protected final static int SIZE_WIDTH = 300;
    protected final static int SIZE_HEIGHT = 400;
    protected final static int LOCATION_X = (screenSize.width - SIZE_WIDTH) / 2;
    protected final static int LOCATION_Y = (screenSize.height - SIZE_HEIGHT) / 2;
    protected JButton okButton = new JButton("Ok");
    protected JButton cancelButton = new JButton("Cancel");
    protected T entity;
    protected T oldEntity;
    protected boolean isUpdate;

    public AddEditPage(String name, T entity) {
        super(name);
        final Container container = getContentPane();
        final JPanel panel = new JPanel();

        setBounds(LOCATION_X, LOCATION_Y, SIZE_WIDTH, SIZE_HEIGHT);
        container.setLayout(new BorderLayout());
        panel.add(okButton);
        panel.add(cancelButton);

        cancelButton.addActionListener(e -> {
            this.setVisible(false);
            this.dispose();
        });

        oldEntity = entity;
        this.entity = entity;
        container.add(panel, BorderLayout.SOUTH);
        if (entity != null) {
            isUpdate = true;
        }
    }

}
