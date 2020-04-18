package gui;

import dao.Service;
import gui.addEditPages.AddEditSportPage;
import model.Role;
import model.Sport;

import javax.swing.*;
import java.awt.*;

public class SportPage extends Page {
    private static SportPage instance;

    protected final static int SIZE_WIDTH = 300;
    protected final static int SIZE_HEIGHT = 400;
    protected final static int LOCATION_X = (screenSize.width - SIZE_WIDTH) / 2;
    protected final static int LOCATION_Y = (screenSize.height - SIZE_HEIGHT) / 2;
    private JList<Sport> sportList;
    private JButton editButton = new JButton("Edit");

    public static SportPage getInstance(){
        if (instance == null){
            instance = new SportPage();
        }
        return instance;
    }

    private SportPage() {
        super("Sport");
        uploadList();
        setBounds(LOCATION_X, LOCATION_Y, SIZE_WIDTH, SIZE_HEIGHT);
        Container container = getContentPane();
        container.add(sportList);

        if (PageManager.getRole().equals(Role.ADMIN)) {
            final JButton addButton = new JButton("Add");
            final JButton removeButton = new JButton("Remove");
            container.add(addButton);
            container.add(editButton);
            container.add(removeButton);
            editButton.setEnabled(false);
            addButton.addActionListener(e -> new AddEditSportPage(null));
            editButton.addActionListener(e -> new AddEditSportPage(sportList.getSelectedValue()));
        }

        final JButton backButton = new JButton("Back");
        container.add(backButton);

        backButton.addActionListener(e -> {
            PageManager.hideUpperPage();
            new PageManager(MainPage.getInstance()).showPage();
        });
    }

    @Override
    public void setVisible(boolean b) {
        uploadList();
        this.repaint();
        super.setVisible(b);
    }

    public void uploadList() {
        DefaultListModel<Sport> model = new DefaultListModel<>();
        Service.getAllSports().forEach(model::addElement);
        sportList = new JList<>(model);
        sportList.addListSelectionListener(e -> editButton.setEnabled(true));
    }
}
