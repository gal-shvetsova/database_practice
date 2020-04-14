package gui;

import dao.Service;
import model.Club;
import model.Role;

import javax.swing.*;
import java.awt.*;

public class ClubPage extends Page {
    private JList<Club> clubList  = new JList<>(Service.getAllClubs().toArray(new Club[0]));

    public ClubPage() {
        super("Club");

        JButton addButton = new JButton("Add");
        Container container = getContentPane();
        container.add(clubList);

        if (Manager.getRole().equals(Role.ADMIN)){
            container.add(addButton);
        }

        container.add(clubList);
    }

    @Override
    public void setVisible(boolean b) {
        clubList.setListData(Service.getAllClubs().toArray(new Club[0]));
        super.setVisible(b);
    }
}
