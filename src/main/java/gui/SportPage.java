package gui;

import dao.Service;
import gui.addEditPages.AddEditSportPage;
import model.Role;
import model.Sport;

import javax.swing.*;
import java.awt.*;

public class SportPage extends Page{
    private JList<Sport> sportList;
    private JButton addButton = new JButton("Add");
    private JButton editButton = new JButton("Edit");
    private JButton removeButton = new JButton("Remove");
    private JButton backButton = new JButton("Back");

    public SportPage() {
        super("Sport");
        uploadList();

        Container container = getContentPane();
        container.add(sportList);
        editButton.setEnabled(false);

        if (Manager.getRole().equals(Role.ADMIN)) {
            container.add(addButton);
            container.add(editButton);
            container.add(removeButton);
        }
        container.add(backButton);

        addButton.addActionListener(e -> new AddEditSportPage(null));
        editButton.addActionListener(e -> new AddEditSportPage(sportList.getSelectedValue()));

        backButton.addActionListener(e -> {
            Manager.hideSportPage();
            Manager.showMainPage();
        });
        pack();
    }

    @Override
    public void setVisible(boolean b) {
        uploadList();
        this.repaint();
        pack();
        super.setVisible(b);
    }

    public void uploadList(){
        DefaultListModel<Sport> model = new DefaultListModel<>();
        Service.getAllSports().forEach(model::addElement);
        sportList = new JList<>(model);
        sportList.addListSelectionListener(e -> editButton.setEnabled(true));
    }
}
