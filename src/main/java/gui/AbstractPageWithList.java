package gui;

import dao.Service;
import model.Club;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class AbstractPageWithList extends Page {
    protected final static int SIZE_WIDTH = 600;
    protected final static int SIZE_HEIGHT = 300;
    protected final static int LOCATION_X = (screenSize.width - SIZE_WIDTH) / 2;
    protected final static int LOCATION_Y = (screenSize.height - SIZE_HEIGHT) / 2;
    protected final JButton editButton = new JButton("Edit");
    protected final JButton addButton = new JButton("Add");
    protected final JButton removeButton = new JButton("Remove");
    protected final JButton backButton = new JButton("Back");
    protected final JList<Model> entityList = new JList<>();

    public AbstractPageWithList(String name) {
        super(name);
        setBounds(LOCATION_X, LOCATION_Y, SIZE_WIDTH, SIZE_HEIGHT);
        Container container = getContentPane();
        container.setLayout(new BorderLayout());

        backButton.addActionListener(e -> {
            PageManager.hideUpperPage();
            new PageManager(MainPage.getInstance()).showPage();
        });
    }

    protected void updateList(){
        getContentPane().remove(entityList);
        entityList.addListSelectionListener(e -> {
            editButton.setEnabled(true);
            removeButton.setEnabled(true);
        });
        getContentPane().add(entityList, BorderLayout.CENTER);
    }

    @Override
    public void setVisible(boolean b) {
        editButton.setEnabled(false);
        removeButton.setEnabled(false);
        updateList();
        super.setVisible(b);
    }
}
