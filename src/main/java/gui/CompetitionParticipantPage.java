package gui;

import dao.Service;
import gui.addEditPages.AddEditCompetitionParticipant;
import model.Competition;
import model.CompetitionParticipant;
import model.Model;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class CompetitionParticipantPage extends AbstractPageWithList {

    private static CompetitionParticipantPage instance;

    private Competition selectedCompetition;

    public static CompetitionParticipantPage getInstance() {
        if (instance == null) {
            instance = new CompetitionParticipantPage();
        }
        return instance;
    }

    private CompetitionParticipantPage() {
        super("Competition sportsman");
        final JComboBox<Competition> competitionComboBox = new JComboBox<>();
        final JPanel buttonPanel = new JPanel();

        final Container container = getContentPane();
        container.add(competitionComboBox, BorderLayout.NORTH);
        container.add(entityList, BorderLayout.CENTER);

        try {
            Service.getCompetitions().forEach(competitionComboBox::addItem);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        selectedCompetition = competitionComboBox.getItemAt(0);
        updateList();
        removeButton.setEnabled(false);
        addButton.addActionListener(e -> {
            new AddEditCompetitionParticipant(selectedCompetition);
        });

        removeButton.addActionListener(e ->
        {
            try {
                Service.deleteCompetitionParticipant((CompetitionParticipant) entityList.getSelectedValue());
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        competitionComboBox.addActionListener(e -> {
            selectedCompetition = (Competition) competitionComboBox.getSelectedItem();
            updateList();
            repaint();
        });

        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(backButton);

        container.add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    protected void updateList() {
        DefaultListModel<Model> model = new DefaultListModel<>();
        try {
            Service.getPersonsByCompetition(selectedCompetition).forEach(model::addElement);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        entityList.setModel(model);
        super.updateList();
    }
}
