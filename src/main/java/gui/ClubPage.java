package gui;

import dao.Service;
import gui.addEditPages.AddEditClubPage;
import model.Club;
import model.Role;
import model.Sport;

import javax.swing.*;
import java.awt.*;

public class ClubPage extends Page {
    private JList<Club> clubList = new JList<>(Service.getAllClubs().toArray(new Club[0]));
    private JButton addButton = new JButton("Add");
    private JButton editButton = new JButton("Edit");
    private JButton removeButton = new JButton("Remove");
    private JButton backButton = new JButton("Back");
    public ClubPage() {
        super("Club");
        updateList();
        clubList.addListSelectionListener(e -> editButton.setEnabled(true));

        Container container = getContentPane();
        container.add(clubList);
        editButton.setEnabled(false);
        if (Manager.getRole().equals(Role.ADMIN)) {
            container.add(addButton);
            container.add(editButton);
            container.add(removeButton);
        }
        container.add(backButton);

        addButton.addActionListener(e -> new AddEditClubPage(null));
        editButton.addActionListener(e -> new AddEditClubPage(clubList.getSelectedValue()));

        backButton.addActionListener(e -> {
            Manager.hideClubPage();
            Manager.showMainPage();
        });
        pack();
    }

    public void updateList(){
        DefaultListModel<Club> model = new DefaultListModel<>();
        Service.getAllClubs().forEach(model::addElement);
        clubList = new JList<>(model);
        clubList.addListSelectionListener(e -> editButton.setEnabled(true));
    }

    @Override
    public void setVisible(boolean b) {
        clubList.setListData(Service.getAllClubs().toArray(new Club[0]));
        pack();
        super.setVisible(b);
    }
}
