package gui.addEditPages;

import dao.Service;
import gui.Page;
import model.*;

import javax.swing.*;
import java.awt.*;

public class AddEditPersonPage extends AddEditPage<Person> {

    public AddEditPersonPage(Person person) {
        super("Add/edit person", person);
        Container container = getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        JTextField nameField = new JTextField();
        JTextField surnameField = new JTextField();
        JComboBox<Role> roleComboBox = new JComboBox<>();

        roleComboBox.addItem(Role.ADMIN);
        roleComboBox.addItem(Role.ORGANIZER);
        roleComboBox.addItem(Role.TRAINER);
        roleComboBox.addItem(Role.SPORTSMAN);

        nameField.setText(person.getName());
        surnameField.setText(person.getSurname());
        roleComboBox.setSelectedItem(person.getRole());
        roleComboBox.setEnabled(false);

        container.add(nameField);
        container.add(surnameField);
        container.add(roleComboBox);

        okButton.addActionListener(e -> {
            entity = new Person(oldEntity.getId(), oldEntity.getRole(), oldEntity.getLogin(), oldEntity.getPassword(),
                    nameField.getText(), surnameField.getText());
            okButton.setEnabled(false);
            cancelButton.setEnabled(false);
            Service.updatePerson(entity);
            okButton.setEnabled(true);
            cancelButton.setEnabled(true);
            oldEntity = entity;
            setVisible(false);
            dispose();
        });

        this.setVisible(true);
    }
}
