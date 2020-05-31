package gui;

import dao.Service;
import gui.addEditPages.AddEditAttributeFacilityPage;
import model.AttributeFacility;
import model.Role;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class AttributeFacilityPage extends AbstractPageWithTable {
    private static AttributeFacilityPage instance;

    private final Object[] columnsHeader = new String[]{"Attribute", "Facility",
           "Value"};
    private List<AttributeFacility> attrList;

    public static AttributeFacilityPage getInstance() {
        if (instance == null) {
            instance = new AttributeFacilityPage();
        }
        return instance;
    }


    private AttributeFacilityPage() {
        super("Attribute facility");
        final JPanel buttonPanel = new JPanel();

        updateTable();
        final Container container = getContentPane();

        container.add(entityPane, BorderLayout.NORTH);
        if (PageManager.getRole().equals(Role.ADMIN)) {
            buttonPanel.add(addButton);
            buttonPanel.add(removeButton);
            addButton.addActionListener(e -> new AddEditAttributeFacilityPage(null));
            removeButton.setEnabled(false);
            removeButton.addActionListener(e -> {
                try {
                    Service.deleteAttributeForFacility(attrList.get(entityTable.getSelectedRow()));
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this,
                            "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
        buttonPanel.add(backButton);
        container.add(buttonPanel, BorderLayout.SOUTH);

    }

    @Override
    protected void updateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnsHeader);
        try {
            attrList = Service.getAllAttributes();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        attrList.forEach(e -> model.addRow(new Object[]{e.getAttributeFacilityKind(), e.getFacility(),
                e.getValue()}));
        entityTable.setModel(model);
        super.updateTable();
    }
}
