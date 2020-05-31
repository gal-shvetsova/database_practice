package gui.addEditPages;

import dao.Service;
import model.FacilityKind;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AddEditFacilityKindPage extends AddEditPage<FacilityKind> {


    public AddEditFacilityKindPage(FacilityKind facilityKind) {
        super("Add/Edit facility kind", facilityKind);
        final Container container = getContentPane();
        final JTextField nameField = new JTextField();
        final JLabel nameLabel = new JLabel("Name");
        final JPanel panel = new JPanel();

        if (facilityKind != null) {
            nameField.setText(entity.getName());
        }
        panel.setLayout(new GridLayout(0, 1));
        panel.add(nameLabel);
        panel.add(nameField);

        container.add(panel, BorderLayout.NORTH);
        okButton.addActionListener(e -> {
            okButton.setEnabled(false);
            cancelButton.setEnabled(false);
            entity = new FacilityKind(nameField.getText());
            try {
                if (isUpdate) {
                    Service.updateFacilityKind(oldEntity, entity);
                } else {
                    Service.createFacilityKind(entity);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
