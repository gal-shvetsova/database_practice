package gui.addEditPages;

import dao.Service;
import model.AttributeFacility;
import model.AttributeFacilityKind;
import model.Facility;

import javax.swing.*;
import java.awt.*;

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

        Service.getAllAttributeFacilityKinds().forEach(attrFacilityKindComboBox::addItem);
        Service.getFacilitiesByKind(((AttributeFacilityKind) attrFacilityKindComboBox
                .getSelectedItem()).getFacilityKind()).forEach(facilityComboBox::addItem);

        attrFacilityKindComboBox.addActionListener(e -> {
                    facilityComboBox.removeAllItems();
                    Service.getFacilitiesByKind(((AttributeFacilityKind) attrFacilityKindComboBox
                            .getSelectedItem()).getFacilityKind()).forEach(facilityComboBox::addItem);
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
            Service.createAttributeFacility(
                    new AttributeFacility((AttributeFacilityKind) attrFacilityKindComboBox.getSelectedItem(),
                            (Facility) facilityComboBox.getSelectedItem(), Integer.parseInt(valueTextField.getText())));
            okButton.setEnabled(true);
            cancelButton.setEnabled(true);
            setVisible(false);
            dispose();
        });

        container.add(panel, BorderLayout.NORTH);

        this.setVisible(true);
    }
}
