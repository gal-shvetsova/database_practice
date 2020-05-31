package gui.addEditPages;

import dao.Service;
import model.AttributeFacility;
import model.AttributeFacilityKind;
import model.Facility;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AddEditAttributeFacilityPage extends AddEditPage<AttributeFacility> {
    public AddEditAttributeFacilityPage(AttributeFacility attributeFacility) {
        super("Add attribute facility", attributeFacility);
        final Container container = getContentPane();

        final JComboBox<AttributeFacilityKind> attrFacilityKindComboBox = new JComboBox<>();
        final JComboBox<Facility> facilityComboBox = new JComboBox<>();
        final JTextField valueTextField = new JTextField();

        final JLabel attrFacilityKindLabel = new JLabel("Attribute");
        final JLabel facilityLabel = new JLabel("Facility");
        final JLabel valueLabel = new JLabel("Value");

        final JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(0, 1));

        try {
            Service.getAllAttributeFacilityKinds().forEach(attrFacilityKindComboBox::addItem);
            Service.getFacilitiesByKind(((AttributeFacilityKind) attrFacilityKindComboBox
                    .getSelectedItem()).getFacilityKind()).forEach(facilityComboBox::addItem);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        attrFacilityKindComboBox.addActionListener(e -> {
                    facilityComboBox.removeAllItems();
                    try {
                        Service.getFacilitiesByKind(((AttributeFacilityKind) attrFacilityKindComboBox
                                .getSelectedItem()).getFacilityKind()).forEach(facilityComboBox::addItem);
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        JOptionPane.showMessageDialog(this,
                                "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
        );

        panel.add(attrFacilityKindLabel);
        panel.add(attrFacilityKindComboBox);
        panel.add(facilityLabel);
        panel.add(facilityComboBox);
        panel.add(valueLabel);
        panel.add(valueTextField);


        okButton.addActionListener(e -> {
            okButton.setEnabled(false);
            cancelButton.setEnabled(false);
            try {
                Service.createAttributeFacility(
                        new AttributeFacility((AttributeFacilityKind) attrFacilityKindComboBox.getSelectedItem(),
                                (Facility) facilityComboBox.getSelectedItem(), Integer.parseInt(valueTextField.getText())));
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            okButton.setEnabled(true);
            cancelButton.setEnabled(true);
            setVisible(false);
            dispose();
        });

        container.add(panel, BorderLayout.NORTH);

        this.setVisible(true);
    }
}
