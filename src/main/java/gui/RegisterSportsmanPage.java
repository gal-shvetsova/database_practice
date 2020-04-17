package gui;

import dao.Service;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class RegisterSportsmanPage extends AbstractRegisterPage {
    protected final static int SIZE_WIDTH = 300;
    protected final static int SIZE_HEIGHT =500;
    protected final static int LOCATION_X = (screenSize.width - SIZE_WIDTH) / 2;
    protected final static int LOCATION_Y = (screenSize.height - SIZE_HEIGHT) / 2;


    public RegisterSportsmanPage() {
        super("Register sportsman");
        JComboBox<Sport> sportComboBox = new JComboBox<>();
        JComboBox<Club> clubComboBox = new JComboBox<>();
        JComboBox<Person> trainerComboBox = new JComboBox<>();
        JTextField category = new JTextField();
        JLabel sportLabel = new JLabel("Sport");
        JLabel clubLabel = new JLabel("Club");
        JLabel trainerLabel = new JLabel("Trainer");
        JLabel categoryLabel = new JLabel("Category");

        Container container = getContentPane();
        container.setLayout(new GridLayout(0,1));

        setBounds(LOCATION_X, LOCATION_Y, SIZE_WIDTH, SIZE_HEIGHT);

        Service.getAllClubs().forEach(clubComboBox::addItem);
        Service.getAllSports().forEach(sportComboBox::addItem);
        Service.getAllTrainers().forEach(trainerComboBox::addItem);

        container.add(sportLabel);
        container.add(sportComboBox);
        container.add(clubLabel);
        container.add(clubComboBox);
        container.add(trainerLabel);
        container.add(trainerComboBox);
        container.add(categoryLabel);
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
