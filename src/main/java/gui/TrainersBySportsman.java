package gui;

import dao.Service;
import model.Person;
import model.Sport;
import model.SportsmanCharacteristic;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class TrainersBySportsman extends AbstractFilterPage {
    private static TrainersBySportsman instance;

    private final Object[] columnHeader = new String[]{"Trainer"};
    private final List<Person> trainerList = new ArrayList<>();
    private final JComboBox<Person> sportsmanComboBox = new JComboBox<>();
    private final JComboBox<Sport> sportComboBox = new JComboBox<>();
    private final JRadioButton useSport = new JRadioButton("Sport", false);
    private final JRadioButton useSportsman = new JRadioButton("Sportsman", true);


    public static TrainersBySportsman getInstance(){
        if (instance == null){
            instance = new TrainersBySportsman();
        }

        return instance;
    }

    protected TrainersBySportsman() {
        super("Trainers by sportsman");
        final Container container = getContentPane();
        final JPanel buttonPanel = new JPanel();
        final JLabel sportsmanLabel = new JLabel("Sportsman");
        final JLabel sportLabel = new JLabel("Sport");

        Service.getAllSportsmen().forEach(sportsmanComboBox::addItem);
        Service.getAllSports().forEach(sportComboBox::addItem);

        okButton.addActionListener(e -> {
            trainerList.clear();
            if (useSportsman.isSelected()) {
                trainerList.addAll(Service.getTrainersForSportsman((Person) sportsmanComboBox.getSelectedItem()));
            } else {
                trainerList.addAll(Service.getTrainersBySport((Sport)sportComboBox.getSelectedItem()));
            }
            updateTable();
        });

        JPanel radioPanel = new JPanel();
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(useSportsman);
        buttonGroup.add(useSport);
        radioPanel.add(useSportsman);
        radioPanel.add(useSport);
        container.add(radioPanel);
        container.add(useSport);
        container.add(useSportsman);
        container.add(sportLabel);
        container.add(sportComboBox);
        container.add(sportsmanLabel);
        container.add(sportsmanComboBox);
        buttonPanel.add(okButton);
        buttonPanel.add(backButton);
        container.add(buttonPanel);
    }

    @Override
    protected void updateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnHeader);
        if (trainerList != null) {
            trainerList.forEach(e -> model.addRow(new Object[]{e}));
        }
        entityTable.setModel(model);
        if (isVisible()) {
            this.validateTree();
        }
        super.updateTable();
    }
}
