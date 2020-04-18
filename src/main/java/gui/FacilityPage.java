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

    private JTable facilityTable = new JTable();
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
        final JButton backButton = new JButton("Back");
        final JPanel buttonPanel = new JPanel();

        updateTable();
        Container container = getContentPane();
        container.add(facilityTable, BorderLayout.NORTH);

        if (PageManager.getRole().equals(Role.ADMIN)) {
            final JButton addButton = new JButton("Add");
            final JButton removeButton = new JButton("Remove");
            buttonPanel.add(addButton);
            buttonPanel.add(editButton);
            buttonPanel.add(removeButton);
            addButton.addActionListener(e -> new AddEditFacilityPage(null));
            editButton.addActionListener(e ->
                    new AddEditFacilityPage(facilityList.get(facilityTable.getSelectedRow())));
            editButton.setEnabled(false);
        }

        buttonPanel.add(backButton);
        container.add(buttonPanel, BorderLayout.SOUTH);
        backButton.addActionListener(e -> {
            PageManager.hideUpperPage();
            new PageManager(MainPage.getInstance()).showPage();
        });
    }

    @Override
    protected void updateTable() {
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
