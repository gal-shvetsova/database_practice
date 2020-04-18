package gui;

import dao.Service;
import gui.addEditPages.AddEditCompetitionPage;
import model.Competition;
import model.Role;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class CompetitionPage extends AbstractPageWithTable {
    private static CompetitionPage instance;

    private final JTable competitionTable = new JTable();
    private final Object[] columnsHeader = new String[]{"Name", "Sport",
            "Facility", "Start date", "Finish date", "Organizer"};
    private List<Competition> competitionList;
    private final JButton removeButton = new JButton("Remove");

    public static CompetitionPage getInstance() {
        if (instance == null) {
            instance = new CompetitionPage();
        }
        return instance;
    }

    private CompetitionPage() {
        super("Competition");
        final JButton backButton = new JButton("Back");
        final JPanel buttonPanel = new JPanel();

        updateTable();
        Container container = getContentPane();
        container.add(new JScrollPane(competitionTable), BorderLayout.NORTH);

        if (PageManager.getRole().equals(Role.ADMIN)) {
            final JButton addButton = new JButton("Add");

            buttonPanel.add(addButton);
            buttonPanel.add(editButton);
            buttonPanel.add(removeButton);
            addButton.addActionListener(e -> new AddEditCompetitionPage(null));
            removeButton.setEnabled(false);
            editButton.setEnabled(false);
            editButton.addActionListener(e ->
                    new AddEditCompetitionPage(competitionList.get(competitionTable.getSelectedRow())));
            removeButton.addActionListener(e -> {
                if (!Service.deleteCompetition(competitionList.get(competitionTable.getSelectedRow()))) {
                    Utils.createErrorDialog(this, "Can not delete competition", "Error");
                }
            });
        }

        buttonPanel.add(backButton);
        container.add(buttonPanel, BorderLayout.SOUTH);
        backButton.addActionListener(e -> {
            PageManager.hideUpperPage();
            (new PageManager(MainPage.getInstance())).showPage();
        });
    }

    @Override
    protected void updateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnsHeader);
        competitionList = Service.getCompetitions();
        competitionList.forEach(e -> model.addRow(new Object[]{e.getName(), e.getSport(), e.getFacility().getName(),
                e.getStartDate(), e.getFinishDate(), e.getOrganizer()}));
        competitionTable.setModel(model);
        competitionTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = competitionTable.getSelectionModel();
        selectionModel.addListSelectionListener(e -> {
            editButton.setEnabled(true);
            removeButton.setEnabled(true);
        });
    }
}
