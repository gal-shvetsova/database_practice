package gui;

import dao.Service;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class RegisterSportsmanPage extends AbstractRegisterPage {

    public RegisterSportsmanPage() {
        super("Register sportsman");
        JComboBox<Sport> sportComboBox = new JComboBox<>();
        JComboBox<Club> clubComboBox = new JComboBox<>();
        JComboBox<Person> trainerComboBox = new JComboBox<>();
        JTextField category = new JTextField();

        category.setText("             ");

        Container container = getContentPane();
        Service.getAllClubs().forEach(clubComboBox::addItem);
        Service.getAllSports().forEach(sportComboBox::addItem);
        Service.getAllTrainers().forEach(trainerComboBox::addItem);

        container.add(sportComboBox);
        container.add(clubComboBox);
        container.add(trainerComboBox);
        container.add(category);
        container.add(okButton);
        container.add(cancelButton);

        okButton.addActionListener(e ->{
            Sportsman sportsman = new Sportsman(new Person(UUID.randomUUID(), Role.SPORTSMAN,
                    login.getText(),
                    password.getText(),
                    surnameText.getText(),
                    nameText.getText()), (Club) clubComboBox.getSelectedItem(),
                    (Sport) sportComboBox.getSelectedItem(),
                    Integer.parseInt(category.getText()),
                    (Person) trainerComboBox.getSelectedItem());
            Service.registerSportsman(sportsman);
            Manager.signIn(sportsman.getLogin(), sportsman.getPassword());
        });


        cancelButton.addActionListener(e -> {
            Manager.hideRegisterSportsman();
            Manager.showEnterPage();
        });

    }
}
