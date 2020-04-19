package gui.addEditPages;

import dao.Service;
import model.Facility;
import model.FacilityKind;

import javax.swing.*;
import java.awt.*;

public class AddEditFacilityPage extends AddEditPage<Facility> {
    public AddEditFacilityPage(Facility facility) {
        super("Facility", facility);
        final Container container = getContentPane();

        final JTextField nameField = new JTextField();
        final JTextField addressField = new JTextField();
        final JComboBox<FacilityKind> kindCheckBox = new JComboBox<>();

        final JLabel nameLabel = new JLabel("Name");
        final JLabel addressLabel = new JLabel("Address");
        final JLabel kindLabel = new JLabel("Kind");

        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,1));

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(addressLabel);
        panel.add(addressField);
        panel.add(kindLabel);
        panel.add(kindCheckBox);

        Service.getAllFacilityKinds().forEach(kindCheckBox::addItem);

        if (facility != null) {
            nameField.setText(facility.getName());
            addressField.setText(facility.getAddress());
            kindCheckBox.setSelectedItem(facility.getKind());
        }

        container.add(panel, BorderLayout.NORTH);

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

        this.setVisible(true);
    }
}
