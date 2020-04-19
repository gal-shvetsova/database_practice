package gui.addEditPages;

import dao.Service;
import model.Person;
import model.Role;

import javax.swing.*;
import java.awt.*;

public class AddEditPersonPage extends AddEditPage<Person> {

    public AddEditPersonPage(Person person) {
        super("Add/edit person", person);
        final Container container = getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));
        final JTextField nameField = new JTextField();
        final JTextField surnameField = new JTextField();
        final JComboBox<Role> roleComboBox = new JComboBox<>();

        final JLabel nameLabel = new JLabel("Name");
        final JLabel surnameLabel = new JLabel("Surname");
        final JLabel roleLabel = new JLabel("Role");

        final JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0, 1));

        roleComboBox.addItem(Role.ADMIN);
        roleComboBox.addItem(Role.ORGANIZER);
        roleComboBox.addItem(Role.TRAINER);
        roleComboBox.addItem(Role.SPORTSMAN);

        nameField.setText(person.getName());
        surnameField.setText(person.getSurname());
        roleComboBox.setSelectedItem(person.getRole());
        roleComboBox.setEnabled(false);

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(surnameLabel);
        panel.add(surnameField);
        panel.add(roleLabel);
        panel.add(roleComboBox);

        container.add(panel, BorderLayout.NORTH);

        okButton.addActionListener(e -> {
            entity = new Person(oldEntity.getId(), oldEntity.getRole(), oldEntity.getLogin(), oldEntity.getPassword(),
                    surnameField.getText(), nameField.getText());
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
