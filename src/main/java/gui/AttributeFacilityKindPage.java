package gui;

import dao.Service;
import gui.addEditPages.AddEditAttributeFacilityKind;
import gui.addEditPages.AddEditCompetitionPage;
import model.AttributeFacilityKind;
import model.Role;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AttributeFacilityKindPage extends Page {
    private Object[] columnHeader = new String[]{"Name", "Facility kind"};
    private JButton editButton = new JButton("Edit");
    private JTable attrTable;
    private List<AttributeFacilityKind> attributeFacilityKindList;

    public AttributeFacilityKindPage() {
        super("Attribute facility kind");
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        JButton backButton = new JButton("Back");
        editButton.setEnabled(false);
        Container container = getContentPane();

        if (Manager.getRole().equals(Role.ADMIN)) {
            container.add(addButton);
            container.add(editButton);
            container.add(removeButton);
        }
        container.add(backButton);

        addButton.addActionListener(e -> new AddEditCompetitionPage(null));
        editButton.addActionListener(e ->
                new AddEditAttributeFacilityKind(attributeFacilityKindList.get(attrTable.getSelectedRow())));

        backButton.addActionListener(e -> {
            Manager.hideAttributeFacilityKindPage();
            Manager.showMainPage();
        });
        pack();

    }

    public void updateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnHeader);
        attributeFacilityKindList = Service.getAllAttributeFacilityKinds();
        attributeFacilityKindList.forEach(e -> model.addRow(new Object[]{e.getName(), e.getFacilityKind()}));
        attrTable = new JTable(model);
        attrTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = attrTable.getSelectionModel();
        selectionModel.addListSelectionListener(e -> editButton.setEnabled(true));
    }
}
