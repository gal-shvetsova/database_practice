package gui;

import dao.Service;
import model.Club;
import model.Role;

import javax.swing.*;
import java.awt.*;

public class ClubPage extends Page {
    private JList<Club> clubList = new JList<>(Service.getAllClubs().toArray(new Club[0]));

    public ClubPage() {
        super("Club");

        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton removeButton = new JButton("Remove");
        JButton backButton = new JButton("Back");

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
        editButton.addActionListener(e -> new AddEditClubPage(clubList.getSelectedValue().getName()));

        backButton.addActionListener(e -> {
            Manager.hideClubPage();
            Manager.showMainPage();
        });
        pack();
    }

    @Override
    public void setVisible(boolean b) {
        clubList.setListData(Service.getAllClubs().toArray(new Club[0]));
        pack();
        super.setVisible(b);
    }
}
