package gui;

import dao.Service;
import gui.addEditPages.AddEditFacilityKindPage;
import model.FacilityKind;
import model.Role;

import javax.swing.*;
import java.awt.*;

public class FacilityKindPage extends Page {
    JList<FacilityKind> facilityKindList = new JList<>(Service.getAllFacilityKinds().toArray(new FacilityKind[0]));
    public FacilityKindPage() {
        super("Facility kind");
        JButton addButton = new JButton("Add");
        JButton editButton = new JButton("Edit");
        JButton removeButton = new JButton("Remove");
        JButton backButton = new JButton("Back");

        facilityKindList.addListSelectionListener(e -> editButton.setEnabled(true));

        Container container = getContentPane();
        container.add(facilityKindList);
        editButton.setEnabled(false);
        if (Manager.getRole().equals(Role.ADMIN)) {
            container.add(addButton);
            container.add(editButton);
            container.add(removeButton);
        }
        container.add(backButton);

        addButton.addActionListener(e -> new AddEditFacilityKindPage(null));
        editButton.addActionListener(e -> new AddEditFacilityKindPage(facilityKindList.getSelectedValue()));

        backButton.addActionListener(e -> {
            Manager.hideFacilityKindPage();
            Manager.showMainPage();
        });
        pack();
    }
}
