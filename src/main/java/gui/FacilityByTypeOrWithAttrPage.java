package gui;

import dao.Service;
import model.AttributeFacilityKind;
import model.Facility;
import model.FacilityKind;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacilityByTypeOrWithAttrPage extends AbstractFilterPage {
    private static FacilityByTypeOrWithAttrPage instance;

    private final Object[] columnHeader = new String[]{"Name", "Address", "Kind"};
    private final List<Facility> facilityList = new ArrayList<>();
    private final JComboBox<AttributeFacilityKind> attributeFacilityKindComboBox = new JComboBox<>();
    private final JComboBox<FacilityKind> facilityKindComboBox = new JComboBox<>();
    private final JTextField attrValueTextFromField = new JTextField();
    private final JTextField attrValueTextToField = new JTextField();


    public static FacilityByTypeOrWithAttrPage getInstance() {
        if (instance == null) {
            instance = new FacilityByTypeOrWithAttrPage();
        }
        return instance;
    }

    private FacilityByTypeOrWithAttrPage() {
        super("Facility by type or with attr");
        final Container container = getContentPane();
        final JPanel buttonPanel = new JPanel();
        final JLabel attributeFacilityKindLabel = new JLabel("Attribute for kind");
        final JLabel facilityKindLabel = new JLabel("Facility kind");
        final JLabel attributeValueFromLabel = new JLabel("From");
        final JLabel attributeValueToLabel = new JLabel("To");
        final JCheckBox useAttribute = new JCheckBox("Use attribute");

        try {
            Service.getAllFacilityKinds().forEach(facilityKindComboBox::addItem);
            Service.getAllAttributeFacilityKinds().forEach(attributeFacilityKindComboBox::addItem);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        facilityKindComboBox.addActionListener(e -> {
            attributeFacilityKindComboBox.removeAllItems();
            try {
                Service.getAttributesByKind((FacilityKind) facilityKindComboBox.getSelectedItem())
                        .forEach(attributeFacilityKindComboBox::addItem);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        okButton.addActionListener(e -> {
            facilityList.clear();
            try {
                if (useAttribute.isSelected()) {
                    facilityList.addAll(Service.getFacilityWithParams((FacilityKind) facilityKindComboBox.getSelectedItem()
                            , (AttributeFacilityKind) attributeFacilityKindComboBox.getSelectedItem(),
                            parseInt(attrValueTextFromField.getText()), parseInt(attrValueTextToField.getText()),
                            useAttribute.isSelected()));
                } else {
                    facilityList.addAll(Service.getFacilityWithParams((FacilityKind) facilityKindComboBox.getSelectedItem()
                            , null,
                            null, null,
                            useAttribute.isSelected()));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            updateTable();
        });

        useAttribute.setSelected(true);
        useAttribute.addActionListener(e -> {
            boolean enabled = useAttribute.isSelected();
            attributeFacilityKindComboBox.setEnabled(enabled);
            attributeValueFromLabel.setEnabled(enabled);
            attributeValueToLabel.setEnabled(enabled);
        });

        container.add(facilityKindLabel);
        container.add(facilityKindComboBox);
        container.add(useAttribute);
        container.add(attributeFacilityKindLabel);
        container.add(attributeFacilityKindComboBox);
        container.add(attributeValueFromLabel);
        container.add(attrValueTextFromField);
        container.add(attributeValueToLabel);
        container.add(attrValueTextToField);
        buttonPanel.add(okButton);
        buttonPanel.add(backButton);
        container.add(buttonPanel);
    }

    @Override
    protected void updateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnHeader);
        if (facilityList != null) {
            facilityList.forEach(e -> model.addRow(new Object[]{e.getName(), e.getAddress(), e.getKind()}));
        }
        entityTable.setModel(model);
        if (isVisible()) {
            this.validateTree();
        }
        super.updateTable();
    }

}
