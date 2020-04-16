package gui;

import dao.Service;
import gui.addEditPages.AddEditClubPage;
import gui.addEditPages.AddEditFacilityPage;
import model.Facility;
import model.Role;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;

public class FacilityPage extends Page {
    private JTable facilityTable = new JTable();
    private JButton addButton = new JButton("Add");
    private JButton editButton = new JButton("Edit");
    private JButton removeButton = new JButton("Remove");
    private JButton backButton = new JButton("Back");
    private Object[] columnsHeader = new String[]{"Name", "Address", "Kind"};
    private List<Facility> facilityList;

    public FacilityPage() {
        super("Facility");
        updateTable();
        Container container = getContentPane();
        container.add(facilityTable);
        editButton.setEnabled(false);
        if (Manager.getRole().equals(Role.ADMIN)) {
            container.add(addButton);
            container.add(editButton);
            container.add(removeButton);
        }
        container.add(backButton);

        addButton.addActionListener(e -> new AddEditFacilityPage(null));
        editButton.addActionListener(e -> new AddEditFacilityPage(facilityList.get(facilityTable.getSelectedRow())));
        backButton.addActionListener(e -> {
            Manager.hideFacilityPage();
            Manager.showMainPage();
        });
        pack();
    }

    private void updateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnsHeader);
        facilityList = Service.getAllFacilities();
        facilityList.forEach(e -> model.addRow(new Object[]{e.getName(), e.getAddress(),
                e.getKind().getName()}));
        facilityTable = new JTable(model);
        facilityTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = facilityTable.getSelectionModel();
        selectionModel.addListSelectionListener(e -> editButton.setEnabled(true));
    }
}
