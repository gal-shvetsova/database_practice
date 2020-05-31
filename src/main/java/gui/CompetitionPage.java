package gui;

import dao.Service;
import gui.addEditPages.AddEditCompetitionPage;
import model.Competition;
import model.Role;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.util.List;

public class CompetitionPage extends AbstractPageWithTable {
    private static CompetitionPage instance;

    private final Object[] columnsHeader = new String[]{"Name", "Sport",
            "Facility", "Start date", "Finish date", "Organizer"};
    private List<Competition> competitionList;

    public static CompetitionPage getInstance() {
        if (instance == null) {
            instance = new CompetitionPage();
        }
        return instance;
    }

    private CompetitionPage() {
        super("Competition");
        final JPanel buttonPanel = new JPanel();

        updateTable();
        Container container = getContentPane();

        container.add(entityPane, BorderLayout.NORTH);

        if (PageManager.getRole().equals(Role.ADMIN)) {
            buttonPanel.add(addButton);
            buttonPanel.add(editButton);
            buttonPanel.add(removeButton);
            addButton.addActionListener(e -> new AddEditCompetitionPage(null));
            removeButton.setEnabled(false);
            editButton.setEnabled(false);
            editButton.addActionListener(e ->
                    new AddEditCompetitionPage(competitionList.get(entityTable.getSelectedRow())));
            removeButton.addActionListener(e -> {
                try {
                    if (!Service.deleteCompetition(competitionList.get(entityTable.getSelectedRow()))) {
                        Utils.createErrorDialog(this, "Can not delete competition", "Error");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this,
                            "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
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
        try {
            competitionList = Service.getCompetitions();
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        competitionList.forEach(e -> model.addRow(new Object[]{e.getName(), e.getSport(), e.getFacility().getName(),
                e.getStartDate(), e.getFinishDate(), e.getOrganizer()}));
        entityTable.setModel(model);
        super.updateTable();
    }
}
