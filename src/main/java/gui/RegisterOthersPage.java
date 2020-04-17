package gui;

import dao.Service;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class RegisterOthersPage extends AbstractRegisterPage {
    protected final static int SIZE_WIDTH = 250;
    protected final static int SIZE_HEIGHT = 300;
    protected final static int LOCATION_X = (screenSize.width - SIZE_WIDTH) / 2;
    protected final static int LOCATION_Y = (screenSize.height - SIZE_HEIGHT) / 2;

    public RegisterOthersPage() {
        super("Register trainer/organizer");

        JComboBox<Role> roleComboBox = new JComboBox<>();
        JLabel roleLabel = new JLabel("Role");

        roleComboBox.addItem(Role.TRAINER);
        roleComboBox.addItem(Role.ORGANIZER);
        roleComboBox.setSelectedIndex(0);

        Container container = getContentPane();
        container.setLayout(new GridLayout(0,1));
        setBounds(LOCATION_X, LOCATION_Y, SIZE_WIDTH, SIZE_HEIGHT);
        container.add(roleLabel);
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

        container.add(okButton);
        container.add(cancelButton);
      //  pack();
    }
}
