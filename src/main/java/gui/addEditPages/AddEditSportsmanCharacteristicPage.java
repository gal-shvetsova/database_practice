package gui.addEditPages;

import dao.Service;
import model.*;

import javax.swing.*;
import java.awt.*;

public class AddEditSportsmanCharacteristicPage extends AddEditPage<SportsmanCharacteristic>{

    public AddEditSportsmanCharacteristicPage(SportsmanCharacteristic sportsmanCharacteristic) {
        super("Sportsman characteristic", sportsmanCharacteristic);
        final JLabel sportsmanLabel = new JLabel("Sportsman");
        final JLabel sportLabel = new JLabel("Sport");
        final JLabel categoryLabel = new JLabel("Category");
        final JLabel trainerLabel = new JLabel("Trainer");
        final JLabel clubLabel = new JLabel("Club");

        final JComboBox<Person> sportsmanComboBox = new JComboBox<>();
        final JComboBox<Sport> sportComboBox = new JComboBox<>();
        final JComboBox<Person> trainerComboBox = new JComboBox<>();
        final JComboBox<Club> clubComboBox = new JComboBox<>();

        final JTextField categoryTextField  = new JTextField();

        final Container container = getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER));

        Service.getAllSportsmen().forEach(sportsmanComboBox::addItem);
        Service.getAllSports().forEach(sportComboBox::addItem);
        Service.getAllTrainers().forEach(trainerComboBox::addItem);
        Service.getAllClubs().forEach(clubComboBox::addItem);

        entity = sportsmanCharacteristic;

        if (sportsmanCharacteristic != null){
            categoryTextField.setText(sportsmanCharacteristic.getCategory().toString());
            sportsmanComboBox.setSelectedItem(sportsmanCharacteristic.getSportsman());
            sportComboBox.setSelectedItem(sportsmanCharacteristic.getSport());
            trainerComboBox.setSelectedItem(sportsmanCharacteristic.getTrainer());
            clubComboBox.setSelectedItem(sportsmanCharacteristic.getClub());
            sportComboBox.setEnabled(false);
        }

        container.add(sportsmanLabel);
        container.add(sportsmanComboBox);
        container.add(sportLabel);
        container.add(sportComboBox);
        container.add(categoryLabel);
        container.add(categoryTextField);
        container.add(trainerLabel);
        container.add(trainerComboBox);
        container.add(clubLabel);
        container.add(clubComboBox);

        okButton.addActionListener(e -> {
            entity = new SportsmanCharacteristic((Person)trainerComboBox.getSelectedItem(),
                    (Sport)sportComboBox.getSelectedItem(), (Person)sportComboBox.getSelectedItem(),
                    (Club)clubComboBox.getSelectedItem(), Integer.parseInt(categoryTextField.getText()));
            okButton.setEnabled(false);
            cancelButton.setEnabled(false);
            if (!isUpdate){
                Service.createSportsmanCharacteristic(entity);
            } else {
                Service.updateSportsmanCharacteristic(oldEntity, entity);
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
