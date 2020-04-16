package gui;

import dao.Service;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class RegisterOthersPage extends AbstractRegisterPage {
    public RegisterOthersPage() {
        super("Register trainer/organizer");
        JComboBox<Role> roleComboBox = new JComboBox<>();
        roleComboBox.addItem(Role.TRAINER);
        roleComboBox.addItem(Role.ORGANIZER);

        roleComboBox.setSelectedIndex(0);

        Container container = getContentPane();
        container.add(roleComboBox);

        okButton.addActionListener(e ->{
            Person person = new Person(UUID.randomUUID(), ((Role) roleComboBox.getSelectedItem()),
                    login.getText(),
                    password.getText(),
                    surnameText.getText(),
                    nameText.getText());
            Service.registerPerson(person);
            Manager.signIn(person.getLogin(), person.getPassword());
        });


        cancelButton.addActionListener(e -> {
            Manager.hideRegisterOthers();
            Manager.showEnterPage();
        });

    }
}
