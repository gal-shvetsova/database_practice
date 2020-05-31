package gui;

import dao.Service;
import model.Competition;
import model.Facility;
import model.Person;
import model.Sport;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class CompetitionByOrganizerOrTime extends AbstractFilterPage {
    private static CompetitionByOrganizerOrTime instance;

    private final Object[] columnHeader = new String[]{"Competition"};
    private final List<Competition> competitionList = new ArrayList<>();
    private final JComboBox<Facility> facilityComboBox = new JComboBox<>();
    private final JComboBox<Person> organizerComboBox = new JComboBox<>();
    private final JComboBox<Sport> sportComboBox = new JComboBox<>();
    private final JTextField fromTextField = new JTextField();
    private final JTextField toTextField = new JTextField();

    private final JRadioButton useDate = new JRadioButton("By date", false);
    private final JRadioButton useSport = new JRadioButton("By sport", false);
    private final JRadioButton useFacility = new JRadioButton("By facility", true);
    private final JRadioButton useOrganizer = new JRadioButton("By organizer", true);

    public static CompetitionByOrganizerOrTime getInstance() {
        if (instance == null) {
            instance = new CompetitionByOrganizerOrTime();
        }
        return instance;
    }

    private CompetitionByOrganizerOrTime() {
        super("Competition filter");
        final JLabel facilityLabel = new JLabel("Facility");
        final JLabel organizerLabel = new JLabel("Organizer");
        final JLabel sportLabel = new JLabel("Sport");
        final JLabel fromLabel = new JLabel("From");
        final JLabel toLabel = new JLabel("To");

        Container container = getContentPane();
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(useDate);
        buttonGroup.add(useSport);
        buttonGroup.add(useFacility);
        buttonGroup.add(useOrganizer);

        final JPanel radioPanel = new JPanel();
        radioPanel.add(useDate);
        radioPanel.add(useSport);
        radioPanel.add(useFacility);
        radioPanel.add(useOrganizer);

        try {
            Service.getAllSports().forEach(sportComboBox::addItem);
            Service.getAllOrganizers().forEach(organizerComboBox::addItem);
            Service.getAllFacilities().forEach(facilityComboBox::addItem);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        okButton.addActionListener(e -> {
            competitionList.clear();
            try {
                if (useDate.isSelected()) {
                    String postfix = "T18:35:24.00Z";
                    competitionList.addAll(Service.getCompetitionsByDate(
                            Instant.parse(fromTextField.getText() + postfix),
                            Instant.parse(toTextField.getText() + postfix)));
                } else if (useSport.isSelected()) {
                    competitionList.addAll(Service.getCompetitionsBySport((Sport) sportComboBox.getSelectedItem()));
                } else if (useOrganizer.isSelected()) {
                    competitionList.addAll(Service.getCompetitionsByOrganizer((Person) organizerComboBox.getSelectedItem()));
                } else if (useFacility.isSelected()) {
                    competitionList.addAll(Service.getCompetitionsByFacility((Facility) facilityComboBox.getSelectedItem()));
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            updateTable();
        });

        container.add(radioPanel);
        container.add(fromLabel);
        container.add(fromTextField);
        container.add(toLabel);
        container.add(toTextField);
        container.add(sportLabel);
        container.add(sportComboBox);
        container.add(facilityLabel);
        container.add(facilityComboBox);
        container.add(organizerLabel);
        container.add(organizerComboBox);
        final JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(backButton);
        container.add(buttonPanel);
    }

    @Override
    protected void updateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnHeader);
        if (competitionList != null) {
            competitionList.forEach(e -> model.addRow(new Object[]{e}));
        }
        entityTable.setModel(model);
        if (isVisible()) {
            this.validateTree();
        }
        super.updateTable();
    }
}
