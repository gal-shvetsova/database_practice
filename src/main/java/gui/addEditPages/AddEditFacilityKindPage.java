package gui.addEditPages;

import dao.Service;
import model.FacilityKind;

import javax.swing.*;
import java.awt.*;

public class AddEditFacilityKindPage extends AddEditPage<FacilityKind> {


    public AddEditFacilityKindPage(FacilityKind facilityKind) {
        super("Add/Edit facility kind", facilityKind);
        Container container = getContentPane();
        JTextField nameField = new JTextField();
        if (facilityKind != null) {
            nameField.setText(entity.getName());
        }
        container.add(nameField, BorderLayout.NORTH);
        okButton.addActionListener(e -> {
            okButton.setEnabled(false);
            cancelButton.setEnabled(false);
            entity = new FacilityKind(nameField.getText());
            if (isUpdate){
                Service.updateFacilityKind(oldEntity, entity);
            } else {
                Service.createFacilityKind(entity);
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
