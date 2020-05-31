package gui.addEditPages;

import dao.Service;
import model.Club;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AddEditClubPage extends AddEditPage<Club> {

    public AddEditClubPage(Club club) {
        super("Add/edit club", club);
        final Container container = getContentPane();
        final JTextField nameField = new JTextField();
        final JPanel panel = new JPanel();
        final JLabel nameLabel = new JLabel("Name");

        panel.setLayout(new GridLayout(0, 1));
        panel.add(nameLabel);
        panel.add(nameField);

        container.add(panel, BorderLayout.NORTH);

        if (club != null) {
            nameField.setText(entity.getName());
        }

        okButton.addActionListener(e -> {
            okButton.setEnabled(false);
            cancelButton.setEnabled(false);
            entity = new Club(nameField.getText());
            try {
                if (isUpdate) {
                    Service.updateClub(oldEntity, entity);
                } else {
                    Service.createClub(entity);
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
