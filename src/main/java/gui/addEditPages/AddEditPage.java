package gui.addEditPages;

import dao.Service;
import gui.Page;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public abstract class AddEditPage<T> extends Page {

    protected JButton okButton = new JButton("Ok");
    protected JButton cancelButton = new JButton("Cancel");
    protected T entity;
    protected T oldEntity;
    protected boolean isUpdate;

    public AddEditPage(String name, T entity) {
        super(name);
        Container container = getContentPane();
        JPanel panel = new JPanel();
        panel.add(okButton);
        panel.add(cancelButton);
        cancelButton.addActionListener(e -> {
            this.setVisible(false);
            this.dispose();
        });
        oldEntity = entity;
        this.entity = entity;
        container.add(panel, BorderLayout.SOUTH);
        if (entity != null){
            isUpdate = true;
        }
    }

}
