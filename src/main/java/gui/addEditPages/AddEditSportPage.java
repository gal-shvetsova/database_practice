package gui.addEditPages;

import dao.Service;
import model.Sport;

import javax.swing.*;
import java.awt.*;

public class AddEditSportPage extends AddEditPage<Sport> {
    public AddEditSportPage(Sport sport) {
        super("Add/edit sport", sport);
        Container container = getContentPane();
        JTextField nameField = new JTextField();
        container.add(nameField, BorderLayout.NORTH);
        if (sport != null){
            nameField.setText(entity.getName());
        }
        okButton.addActionListener(e -> {
            okButton.setEnabled(false);
            cancelButton.setEnabled(false);
            if (isUpdate) {
                Service.updateSport(oldEntity, entity);
            } else {
                Service.createSport(new Sport(nameField.getText()));
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
