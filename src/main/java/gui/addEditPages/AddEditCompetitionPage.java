package gui.addEditPages;

import dao.Service;
import model.Competition;
import model.Facility;
import model.Person;
import model.Sport;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;
import java.util.UUID;

public class AddEditCompetitionPage extends AddEditPage<Competition> {

    public AddEditCompetitionPage(Competition competition) {
        super("Add/edit competition", competition);
        final Container container = getContentPane();

        final JComboBox<Sport> sportComboBox = new JComboBox<>();
        final JComboBox<Facility> facilityComboBox = new JComboBox<>();
        final JComboBox<Person> organizerComboBox = new JComboBox<>();

        final JTextField nameField = new JTextField();
        final JTextField startDateText = new JTextField();
        final JTextField finishDateText = new JTextField();

        final JLabel nameLabel = new JLabel("Name");
        final JLabel sportLabel = new JLabel("Sport");
        final JLabel facilityLabel = new JLabel("Facility");
        final JLabel organizerLabel = new JLabel("Organizer");
        final JLabel startDateLabel = new JLabel("Start date");
        final JLabel finishDateLabel = new JLabel("Finish date");

        final JPanel panel = new JPanel();

        panel.setLayout(new GridLayout(0, 1));

        try {
            Service.getAllSports().forEach(sportComboBox::addItem);
            Service.getAllFacilities().forEach(facilityComboBox::addItem);
            Service.getAllOrganizers().forEach(organizerComboBox::addItem);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        if (competition != null) {
            nameField.setText(competition.getName());
            startDateText.setText(competition.getStartDate().toString());
            finishDateText.setText(competition.getFinishDate().toString());
            sportComboBox.setSelectedItem(competition.getSport());
            facilityComboBox.setSelectedItem(competition.getFacility());
            organizerComboBox.setSelectedItem(competition.getOrganizer());
        }

        panel.add(nameLabel);
        panel.add(nameField);
        panel.add(sportLabel);
        panel.add(sportComboBox);
        panel.add(facilityLabel);
        panel.add(facilityComboBox);
        panel.add(organizerLabel);
        panel.add(organizerComboBox);
        panel.add(startDateLabel);
        panel.add(startDateText);
        panel.add(finishDateLabel);
        panel.add(finishDateText);

        container.add(panel, BorderLayout.NORTH);
        okButton.addActionListener(e -> {
            okButton.setEnabled(false);
            cancelButton.setEnabled(false);
            TemporalAccessor startDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(startDateText.getText());
            TemporalAccessor finishDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(startDateText.getText());
            String postfix = "T18:35:24.00Z";
            entity = new Competition(UUID.randomUUID(), nameField.getText(), (Sport) sportComboBox.getSelectedItem(),
                    (Facility) facilityComboBox.getSelectedItem(), Instant.parse(startDateText.getText() + postfix),
                    Instant.parse(finishDateText.getText() + postfix),
                    (Person) organizerComboBox.getSelectedItem());

            try {
                if (isUpdate) {
                    Service.updateCompetition(entity);
                } else {
                    Service.createCompetition(entity);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
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
