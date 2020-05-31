package gui.addEditPages;

import dao.Service;
import model.Club;
import model.Person;
import model.Sport;
import model.Sportsman;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AddEditSportsmanPage extends AddEditPage<Sportsman> {

    public AddEditSportsmanPage(Sportsman sportsman) {
        super("Add/edit sportsman", sportsman);
        final Container container = getContentPane();

        final JTextField nameField = new JTextField();
        final JTextField surnameField = new JTextField();
        final JTextField categoryField = new JTextField();
        final JComboBox<Sport> sportComboBox = new JComboBox<>();
        final JComboBox<Person> trainerComboBox = new JComboBox<>();
        final JComboBox<Club> clubComboBox = new JComboBox<>();

        final JLabel nameLabel = new JLabel("Name");
        final JLabel surnameLabel = new JLabel("Surname");
        final JLabel categoryLabel = new JLabel("Category");
        final JLabel sportLabel = new JLabel("Sport");
        final JLabel trainerLabel = new JLabel("Trainer");
        final JLabel clubLabel = new JLabel("Club");

        final JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(0, 1));
        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(surnameLabel);
        panel.add(surnameField);
        panel.add(categoryLabel);
        panel.add(categoryField);
        panel.add(sportLabel);
        panel.add(sportComboBox);
        panel.add(trainerLabel);
        panel.add(trainerComboBox);
        panel.add(clubLabel);
        panel.add(clubComboBox);

        container.add(panel, BorderLayout.NORTH);

        try {
            Service.getAllTrainers().forEach(trainerComboBox::addItem);
            Service.getAllSports().forEach(sportComboBox::addItem);
            Service.getAllClubs().forEach(clubComboBox::addItem);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (sportsman != null) {
            nameField.setText(sportsman.getName());
            surnameField.setText(sportsman.getSurname());
            categoryField.setText(sportsman.getCategory().toString());
            sportComboBox.setSelectedItem(sportsman.getSport());
            trainerComboBox.setSelectedItem(sportsman.getTrainer());
            clubComboBox.setSelectedItem(sportsman.getClub());
        }


        okButton.addActionListener(e -> {
            Person newPerson = new Person(entity.getId(), entity.getRole(), entity.getLogin(), entity.getPassword(),
                    surnameField.getText(), nameField.getText());

            entity = new Sportsman(newPerson, (Club) clubComboBox.getSelectedItem(),
                    (Sport) sportComboBox.getSelectedItem(), Integer.parseInt(categoryField.getText()),
                    (Person) trainerComboBox.getSelectedItem());

            okButton.setEnabled(false);
            cancelButton.setEnabled(false);
            try {
                Service.updateSportsman(oldEntity, entity);
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
