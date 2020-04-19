package gui;

import dao.Service;
import gui.addEditPages.AddEditFacilityPage;
import model.Facility;
import model.Role;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class FacilityPage extends AbstractPageWithTable {
    private static FacilityPage instance;

    private Object[] columnsHeader = new String[]{"Name", "Address", "Kind"};
    private List<Facility> facilityList;

    public static FacilityPage getInstance(){
        if (instance == null){
            instance = new FacilityPage();
        }
        return instance;
    }

    private FacilityPage() {
        super("Facility");
        final JPanel buttonPanel = new JPanel();

        updateTable();
        final Container container = getContentPane();
        container.add(entityPane, BorderLayout.NORTH);

        if (PageManager.getRole().equals(Role.ADMIN)) {
            final JButton addButton = new JButton("Add");

            buttonPanel.add(addButton);
            buttonPanel.add(editButton);
            buttonPanel.add(removeButton);
            addButton.addActionListener(e -> new AddEditFacilityPage(null));
            editButton.addActionListener(e ->
                    new AddEditFacilityPage(facilityList.get(entityTable.getSelectedRow())));
            removeButton.addActionListener(e -> {
                if (!Service.deleteFacility(facilityList.get(entityTable.getSelectedRow()))){
                    Utils.createErrorDialog(this, "Can not delete facility", "Error");
                }
            });
            editButton.setEnabled(false);
            removeButton.setEnabled(false);
        }

        buttonPanel.add(backButton);
        container.add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void updateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnsHeader);
        facilityList = Service.getAllFacilities();
        facilityList.forEach(e -> model.addRow(new Object[]{e.getName(), e.getAddress(),
                e.getKind().getName()}));
        entityTable.setModel(model);
       super.updateTable();
    }
}
