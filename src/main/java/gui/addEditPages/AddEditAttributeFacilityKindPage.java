package gui.addEditPages;

import dao.Service;
import model.AttributeFacilityKind;
import model.FacilityKind;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AddEditAttributeFacilityKindPage extends AddEditPage<AttributeFacilityKind> {
    public AddEditAttributeFacilityKindPage(AttributeFacilityKind attributeFacilityKind) {
        super("Add/Edit attribute facility kind", attributeFacilityKind);
        final Container container = getContentPane();
        final JPanel panel = new JPanel();
        final JTextField nameField = new JTextField();
        final JComboBox<FacilityKind> attributeFacilityKindComboBox = new JComboBox<>();

        final JLabel nameLabel = new JLabel("Name");
        final JLabel attributeFacilityKindLabel = new JLabel("Facility kind");

        panel.setLayout(new GridLayout(0, 1));

        try {
            Service.getAllFacilityKinds().forEach(attributeFacilityKindComboBox::addItem);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(attributeFacilityKindLabel);
        panel.add(attributeFacilityKindComboBox);

        container.add(panel, BorderLayout.NORTH);

        if (attributeFacilityKind != null) {
            nameField.setText(attributeFacilityKind.getName());
            attributeFacilityKindComboBox.setSelectedItem(attributeFacilityKind.getFacilityKind());
            attributeFacilityKindComboBox.setEnabled(false);
        }
        okButton.addActionListener(e -> {
            okButton.setEnabled(false);
            cancelButton.setEnabled(false);

            entity = new AttributeFacilityKind(nameField.getText(), (FacilityKind) attributeFacilityKindComboBox.getSelectedItem());
            try {
                if (isUpdate) {
                    Service.updateAttributeFacilityKind(oldEntity, entity);
                } else {
                    Service.createAttributeFacilityKind(entity);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            oldEntity = entity;
            okButton.setEnabled(true);
            cancelButton.setEnabled(true);
            setVisible(false);
            dispose();
        });

        this.setVisible(true);
    }
}
