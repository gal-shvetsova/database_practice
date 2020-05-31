package gui;

import dao.Service;
import model.Competition;
import model.CompetitionParticipant;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WinnersOfCompetition extends AbstractFilterPage {
    private static WinnersOfCompetition instance;

    private final Object[] columnHeader = new String[]{"Sportsman", "Result"};
    private final List<CompetitionParticipant> competitionList = new ArrayList<>();
    private final JComboBox<Competition> competitionComboBox = new JComboBox<>();

    public static WinnersOfCompetition getInstance(){
        if (instance == null){
            instance = new WinnersOfCompetition();
        }
        return instance;
    }

    protected WinnersOfCompetition() {
        super("Winners of competition");
        final Container container = getContentPane();
        final JLabel competitionLabel = new JLabel("Competition");

        try {
            Service.getCompetitions().forEach(competitionComboBox::addItem);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        okButton.addActionListener(e -> {
            competitionList.clear();
            try {
                competitionList.addAll(Service.getWinnersOfCompetition((Competition)competitionComboBox.getSelectedItem()));
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
            updateTable();
        });

        container.add(competitionLabel);
        container.add(competitionComboBox);
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
            competitionList.forEach(e -> model.addRow(new Object[]{e.getParticipant(), e.getResult()}));
        }
        entityTable.setModel(model);
        if (isVisible()) {
            this.validateTree();
        }
        super.updateTable();
    }
}
