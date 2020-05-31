package gui.addEditPages;

import dao.CompetitionParticipantDao;
import dao.Service;
import gui.Page;
import model.Competition;
import model.CompetitionParticipant;
import model.Person;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class AddEditCompetitionParticipant extends Page {

    public AddEditCompetitionParticipant(Competition competition) {
        super("Add competition participant");
        final JComboBox<Person> potentialParticipants = new JComboBox<>();
        final JLabel potentialParticipantsLabel = new JLabel("Sportsman");
        final JPanel panel = new JPanel();


        final JButton okButton = new JButton("Ok");
        final JButton cancelButton = new JButton("Cancel");

        final Container container = getContentPane();
        container.setLayout(new GridLayout(0, 1));

        container.add(potentialParticipantsLabel);
        container.add(potentialParticipants);

        setBounds(AddEditPage.LOCATION_X, AddEditPage.LOCATION_Y, AddEditPage.SIZE_WIDTH, AddEditPage.SIZE_HEIGHT);
        try {
            Service.getNotParticipantsOf(competition).forEach(potentialParticipants::addItem);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        okButton.addActionListener(e -> {
            try {
                CompetitionParticipantDao
                        .addParticipant(new CompetitionParticipant((Person) potentialParticipants.getSelectedItem(),
                                competition, -1));
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            setVisible(false);
            dispose();
        });

        cancelButton.addActionListener(e -> {
            setVisible(false);
            dispose();
        });

        container.add(okButton);
        container.add(cancelButton);


        setVisible(true);
    }
}
