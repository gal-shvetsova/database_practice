package gui;

import dao.Service;
import gui.addEditPages.AddEditClubPage;
import gui.addEditPages.AddEditCompetitionPage;
import model.Club;
import model.Competition;
import model.Role;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.Instant;
import java.util.List;
import java.util.Vector;

public class CompetitionPage extends Page {
    private JTable competitionTable = new JTable();
    private JButton addButton = new JButton("Add");
    private JButton editButton = new JButton("Edit");
    private JButton removeButton = new JButton("Remove");
    private JButton backButton = new JButton("Back");
    private Object[] columnsHeader = new String[]{"Name", "Sport", "Facility", "Start date", "Finish date"};
    private List<Competition> competitionList;

    public CompetitionPage() {
        super("Competition");
        updateTable();
        Container container = getContentPane();
        container.add(competitionTable);
        editButton.setEnabled(false);
        if (Manager.getRole().equals(Role.ADMIN)) {
            container.add(addButton);
            container.add(editButton);
            container.add(removeButton);
        }
        container.add(backButton);

        addButton.addActionListener(e -> new AddEditCompetitionPage(null));
        editButton.addActionListener(e -> new AddEditCompetitionPage(competitionList.get(competitionTable.getSelectedRow())));

        backButton.addActionListener(e -> {
            Manager.hideClubPage();
            Manager.showMainPage();
        });
        pack();
    }

    public void updateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnsHeader);
        competitionList = Service.getCompetitions();
        competitionList.forEach(e -> model.addRow(new Object[]{e.getName(), e.getSport(), e.getFacility().getName(),
                e.getStartDate(), e.getFinishDate()}));
        competitionTable = new JTable(model);
        competitionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = competitionTable.getSelectionModel();
        selectionModel.addListSelectionListener(e -> editButton.setEnabled(true));
    }

}
