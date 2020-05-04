package gui;

import dao.Service;
import gui.addEditPages.AddEditAttributeFacilityKindPage;
import model.AttributeFacilityKind;
import model.Role;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AttributeFacilityKindPage extends AbstractPageWithTable {
    private static AttributeFacilityKindPage instance;

    private final Object[] columnHeader = new String[]{"Name", "Facility kind"};
    private List<AttributeFacilityKind> attributeFacilityKindList;

    public static AttributeFacilityKindPage getInstance() {
        if (instance == null) {
            instance = new AttributeFacilityKindPage();
        }
        return instance;
    }

    private AttributeFacilityKindPage() {
        super("Attribute facility kind");

        final Container container = getContentPane();
        final JPanel buttonPanel = new JPanel();


        if (PageManager.getRole().equals(Role.ADMIN)) {
            final JButton addButton = new JButton("Add");

            removeButton.setEnabled(false);
            editButton.setEnabled(false);
            buttonPanel.add(addButton);
            buttonPanel.add(editButton);
            buttonPanel.add(removeButton);

            addButton.addActionListener(e -> new AddEditAttributeFacilityKindPage(null));
            editButton.addActionListener(e ->
                    new AddEditAttributeFacilityKindPage(attributeFacilityKindList.get(entityTable.getSelectedRow())));

            removeButton.addActionListener(e -> {
                if (!Service.deleteAttributeFacilityKind(attributeFacilityKindList.get(entityTable.getSelectedRow()))) {
                    Utils.createErrorDialog(this,
                            "Can not delete this attribute facility kind",
                            "Error");
                }
            });
        }

        buttonPanel.add(backButton);
        container.add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            PageManager.hideUpperPage();
            (new PageManager(MainPage.getInstance())).showPage();
        });
    }

    public void updateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnHeader);
        attributeFacilityKindList = Service.getAllAttributeFacilityKinds();
        attributeFacilityKindList.forEach(e -> model.addRow(new Object[]{e.getName(), e.getFacilityKind()}));
        entityTable.setModel(model);
        super.updateTable();
    }
}
