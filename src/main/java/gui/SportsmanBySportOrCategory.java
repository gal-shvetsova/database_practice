package gui;

import dao.Service;
import model.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class SportsmanBySportOrCategory extends AbstractFilterPage {
    private static SportsmanBySportOrCategory instance;


    private final Object[] columnHeader = new String[]{"Sportsman", "Sport", "Category", "Trainer"};
    private final List<SportsmanCharacteristic> sportsmanCharacteristicList = new ArrayList<>();
    private final JComboBox<Sport> sportComboBox = new JComboBox<>();
    private final JComboBox<Person> trainerComboBox = new JComboBox<>();
    private final JTextField categoryValueTextFromField = new JTextField();
    private final JTextField categoryValueTextToField = new JTextField();

    public static SportsmanBySportOrCategory getInstance() {
        if (instance == null) {
            instance = new SportsmanBySportOrCategory();
        }
        return instance;
    }

    private SportsmanBySportOrCategory() {
        super("Sportsman filter");
        final Container container = getContentPane();
        final JPanel buttonPanel = new JPanel();
        final JLabel sportLabel = new JLabel("Sport");
        final JLabel trainerLabel = new JLabel("Trainer");
        final JLabel categoryLabel = new JLabel("Category:");
        final JLabel categoryValueFromLabel = new JLabel("From");
        final JLabel categoryValueToLabel = new JLabel("To");
        final JCheckBox useCategory = new JCheckBox("Use category");
        final JCheckBox useTrainer = new JCheckBox("Use trainer");

        Service.getAllSports().forEach(sportComboBox::addItem);
        Service.getAllTrainers().forEach(trainerComboBox::addItem);

        useCategory.setSelected(true);
        useTrainer.setSelected(true);
        useCategory.addActionListener(e -> {
            boolean enabled = useCategory.isEnabled();
            categoryValueTextFromField.setEnabled(enabled);
            categoryValueTextToField.setEnabled(enabled);
            if (isVisible()) {
                this.validateTree();
            }
        });

        okButton.addActionListener(e -> {
            sportsmanCharacteristicList.clear();
            Person trainer = useTrainer.isSelected() ? (Person) trainerComboBox.getSelectedItem() : null;
            Integer from = useCategory.isSelected() ? parseInt(categoryValueTextFromField.getText()) : null,
                    to = useCategory.isSelected() ? parseInt(categoryValueTextToField.getText()) : null;

            sportsmanCharacteristicList.addAll(Service.getSportsmanBySportOrCategory(
                    (Sport) sportComboBox.getSelectedItem(),
                    trainer,
                    from,
                    to));
            updateTable();
        });

        container.add(sportLabel);
        container.add(sportComboBox);
        container.add(useTrainer);
        container.add(trainerLabel);
        container.add(trainerComboBox);
        container.add(useCategory);
        container.add(categoryLabel);
        container.add(categoryValueFromLabel);
        container.add(categoryValueTextFromField);
        container.add(categoryValueToLabel);
        container.add(categoryValueTextToField);
        buttonPanel.add(okButton);
        buttonPanel.add(backButton);
        container.add(buttonPanel);
    }

    @Override
    protected void updateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnHeader);
        if (sportsmanCharacteristicList != null) {
            sportsmanCharacteristicList.forEach(e -> model.addRow(new Object[]{e.getSportsman(), e.getSport(),
                    e.getCategory(), e.getTrainer()}));
        }
        entityTable.setModel(model);
        if (isVisible()) {
            this.validateTree();
        }
        super.updateTable();
    }
}
