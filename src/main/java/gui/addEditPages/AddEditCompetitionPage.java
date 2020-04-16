package gui.addEditPages;

import dao.Service;
import model.Competition;
import model.Facility;
import model.Person;
import model.Sport;

import javax.swing.*;
import java.awt.*;
import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

public class AddEditCompetitionPage extends AddEditPage<Competition> {

    public AddEditCompetitionPage(Competition competition) {
        super("Add/edit competition", competition);
        Container container = getContentPane();
        JTextField nameField = new JTextField();
        JComboBox<Sport> sportComboBox = new JComboBox<>();
        JComboBox<Facility> facilityComboBox = new JComboBox<>();
        JComboBox<Person> organizerComboBox = new JComboBox<>();

        JTextField startDateText = new JTextField();
        JTextField finishDateText = new JTextField();

        Service.getAllSports().forEach(sportComboBox::addItem);
        Service.getAllFacilities().forEach(facilityComboBox::addItem);
        Service.getAllOrganizers().forEach(organizerComboBox::addItem);

        if (competition != null) {
            nameField.setText(competition.getName());
            startDateText.setText(competition.getStartDate().toString());
            finishDateText.setText(competition.getFinishDate().toString());
            sportComboBox.setSelectedItem(competition.getSport());
            facilityComboBox.setSelectedItem(competition.getFacility());
            organizerComboBox.setSelectedItem(competition.getOrganizer());
        }

        container.add(nameField, BorderLayout.NORTH);
        nameField.setText(entity.getName());
        okButton.addActionListener(e -> {
            okButton.setEnabled(false);
            cancelButton.setEnabled(false);
            TemporalAccessor startDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(startDateText.getText());
            TemporalAccessor finishDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").parse(startDateText.getText());

            entity = new Competition(0, nameField.getText(), (Sport) sportComboBox.getSelectedItem(),
                    (Facility) facilityComboBox.getSelectedItem(), Instant.from(startDate), Instant.from(finishDate),
                    (Person)organizerComboBox.getSelectedItem());

            if (isUpdate) {
                Service.updateCompetition(entity);
            } else {
                Service.createCompetition(entity);
            }
            okButton.setEnabled(true);
            cancelButton.setEnabled(true);
            oldEntity = entity;
            setVisible(false);
            dispose();
        });

        pack();
        this.setVisible(true);
    }
}
