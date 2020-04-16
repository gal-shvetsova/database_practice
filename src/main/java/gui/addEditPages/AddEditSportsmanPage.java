package gui.addEditPages;

import dao.Service;
import model.*;

import javax.swing.*;
import java.awt.*;

public class AddEditSportsmanPage extends AddEditPage<Sportsman> {

    public AddEditSportsmanPage(Sportsman sportsman) {
        super("Add/edit sportsman", sportsman);
        Container container = getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        JTextField nameField = new JTextField();
        JTextField surnameField = new JTextField();
        JTextField categoryField = new JTextField();
        JComboBox<Sport> sportComboBox = new JComboBox<>();
        JComboBox<Person> trainerComboBox = new JComboBox<>();
        JComboBox<Club> clubComboBox = new JComboBox<>();

        Service.getAllTrainers().forEach(trainerComboBox::addItem);
        Service.getAllSports().forEach(sportComboBox::addItem);
        Service.getAllClubs().forEach(clubComboBox::addItem);

        if (sportsman != null) {
            nameField.setText(sportsman.getName());
            surnameField.setText(sportsman.getSurname());
            categoryField.setText(sportsman.getCategory().toString());
            sportComboBox.setSelectedItem(sportsman.getSport());
            trainerComboBox.setSelectedItem(sportsman.getTrainer());
            clubComboBox.setSelectedItem(sportsman.getClub());
        } else {
            nameField.setText("      name     ");
            surnameField.setText("      surname    ");
            categoryField.setText("     category    ");
            sportComboBox.setSelectedIndex(0);
            trainerComboBox.setSelectedIndex(0);
            clubComboBox.setSelectedIndex(0);
        }

        container.add(nameField);
        container.add(surnameField);
        container.add(categoryField);
        container.add(sportComboBox);
        container.add(trainerComboBox);
        container.add(clubComboBox);

        okButton.addActionListener(e -> {

            Person newPerson = new Person(entity.getId(), entity.getRole(), entity.getLogin(), entity.getPassword(),
                    surnameField.getText(), nameField.getText());

            entity = new Sportsman(newPerson, (Club) clubComboBox.getSelectedItem(),
                    (Sport) sportComboBox.getSelectedItem(), Integer.parseInt(categoryField.getText()),
                    (Person) trainerComboBox.getSelectedItem());

            okButton.setEnabled(false);
            cancelButton.setEnabled(false);
            Service.updateSportsman(oldEntity, entity);
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
