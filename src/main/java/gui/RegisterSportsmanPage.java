package gui;

import dao.Service;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.UUID;

public class RegisterSportsmanPage extends AbstractRegisterPage {
    private static RegisterSportsmanPage instance;

    protected final static int SIZE_WIDTH = 300;
    protected final static int SIZE_HEIGHT = 500;
    protected final static int LOCATION_X = (screenSize.width - SIZE_WIDTH) / 2;
    protected final static int LOCATION_Y = (screenSize.height - SIZE_HEIGHT) / 2;

    public static RegisterSportsmanPage getInstance() {
        if (instance == null) {
            instance = new RegisterSportsmanPage();
        }
        return instance;
    }

    private RegisterSportsmanPage() {
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

        setBounds(LOCATION_X, LOCATION_Y, SIZE_WIDTH, SIZE_HEIGHT);

        try {
            Service.getAllClubs().forEach(clubComboBox::addItem);
            Service.getAllSports().forEach(sportComboBox::addItem);
            Service.getAllTrainers().forEach(trainerComboBox::addItem);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

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

        okButton.addActionListener(e -> {
            Sportsman sportsman = new Sportsman(new Person(UUID.randomUUID(), Role.SPORTSMAN,
                    login.getText(),
                    password.getText(),
                    surnameText.getText(),
                    nameText.getText()), (Club) clubComboBox.getSelectedItem(),
                    (Sport) sportComboBox.getSelectedItem(),
                    Integer.parseInt(category.getText()),
                    (Person) trainerComboBox.getSelectedItem());
            try {
                Service.registerSportsman(sportsman);
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            try {
                if (PageManager.signIn(sportsman.getLogin(), sportsman.getPassword())) {
                    PageManager.hideUpperPage();
                    (new PageManager(MainPage.getInstance())).showPage();
                } else {
                    Utils.createErrorDialog(this,
                            "Signing in error", "Error");
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        cancelButton.addActionListener(e -> {
            PageManager.hideUpperPage();
            (new PageManager(RegisterPage.getInstance())).showPage();
        });
    }
}
