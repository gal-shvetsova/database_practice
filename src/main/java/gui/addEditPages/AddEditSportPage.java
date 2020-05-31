package gui.addEditPages;

import dao.Service;
import model.Sport;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AddEditSportPage extends AddEditPage<Sport> {
    public AddEditSportPage(Sport sport) {
        super("Add/edit sport", sport);
        final Container container = getContentPane();
        final JTextField nameField = new JTextField();
        final JLabel nameLabel = new JLabel("Name");
        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        panel.add(nameLabel);
        panel.add(nameField);

        container.add(panel, BorderLayout.NORTH);

        if (sport != null) {
            nameField.setText(entity.getName());
        }
        okButton.addActionListener(e -> {
            okButton.setEnabled(false);
            cancelButton.setEnabled(false);
            entity = new Sport(nameField.getText());
            try {
                if (isUpdate) {
                    Service.updateSport(oldEntity, entity);
                } else {
                    Service.createSport(new Sport(nameField.getText()));
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
