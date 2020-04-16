package gui.addEditPages;

import dao.Service;
import model.Facility;
import model.FacilityKind;

import javax.swing.*;
import java.awt.*;

public class AddEditFacilityPage extends AddEditPage<Facility> {
    public AddEditFacilityPage(Facility facility) {
        super("Facility", facility);
        Container container = getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        JTextField nameField = new JTextField();
        JTextField addressField = new JTextField();
        JComboBox<FacilityKind> kindCheckBox = new JComboBox<>();
        Service.getAllFacilityKinds().forEach(kindCheckBox::addItem);

        if (facility != null) {
            nameField.setText(facility.getName());
            addressField.setText(facility.getAddress());
            kindCheckBox.setSelectedItem(facility.getKind());
        } else {
            nameField.setText("      name     ");
            addressField.setText("      address    ");
            kindCheckBox.setSelectedIndex(0);
        }

        container.add(nameField);
        container.add(addressField);
        container.add(kindCheckBox);

        okButton.addActionListener(e -> {
            entity = new Facility(nameField.getText(), addressField.getText(),
                    (FacilityKind) kindCheckBox.getSelectedItem());
            okButton.setEnabled(false);
            cancelButton.setEnabled(false);
            if (isUpdate) {
                Service.updateFacility(oldEntity, entity);
            } else {
                Service.createFacility(entity);
            }
            okButton.setEnabled(true);
            cancelButton.setEnabled(true);
            oldEntity = entity;
            setVisible(false);
            dispose();
        });

        pack();
        this.setVisible(true);
    }
}
