package gui.addEditPages;

import dao.Service;
import model.AttributeFacilityKind;
import model.FacilityKind;

import javax.swing.*;
import java.awt.*;

public class AddEditAttributeFacilityKind extends AddEditPage<AttributeFacilityKind> {
    public AddEditAttributeFacilityKind(AttributeFacilityKind attributeFacilityKind) {
        super("Add/Edit attribute facility kind", attributeFacilityKind);
        Container container = getContentPane();
        JTextField nameField = new JTextField();
        JComboBox<FacilityKind> attributeFacilityKindComboBox = new JComboBox<>();

        Service.getAllFacilityKinds().forEach(attributeFacilityKindComboBox::addItem);

        container.add(nameField, BorderLayout.NORTH);
        if (attributeFacilityKind != null){
            nameField.setText(attributeFacilityKind.getName());
            attributeFacilityKindComboBox.setSelectedItem(attributeFacilityKind.getFacilityKind());
            attributeFacilityKindComboBox.setEnabled(false);
        }
        okButton.addActionListener(e -> {
            okButton.setEnabled(false);
            cancelButton.setEnabled(false);
            entity = new AttributeFacilityKind(nameField.getText(), oldEntity.getFacilityKind());
            if (isUpdate){
                Service.updateAttributeFacilityKind(oldEntity, entity);
            } else {
                Service.createAttributeFacilityKind(entity);
            }
            oldEntity = entity;
            okButton.setEnabled(true);
            cancelButton.setEnabled(true);
            setVisible(false);
            dispose();
        });

        pack();
        this.setVisible(true);
    }
}
