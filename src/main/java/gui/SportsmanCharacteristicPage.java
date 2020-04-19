package gui;

import dao.Service;
import gui.addEditPages.AddEditSportsmanCharacteristicPage;
import model.Role;
import model.SportsmanCharacteristic;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SportsmanCharacteristicPage extends AbstractPageWithTable {
    private static SportsmanCharacteristicPage instance;

    private final Object[] columnsHeader = new String[]{"Sportsman", "Sport",
            "Category", "Trainer", "Club"};
    private List<SportsmanCharacteristic> sportsmanCompetitionList;

    public static SportsmanCharacteristicPage getInstance() {
        if (instance == null) {
            instance = new SportsmanCharacteristicPage();
        }
        return instance;
    }
    protected SportsmanCharacteristicPage() {
        super("Sportsman characteristic");
        final JButton backButton = new JButton("Back");
        final JPanel buttonPanel = new JPanel();

        updateTable();
        Container container = getContentPane();
        container.add(entityPane, BorderLayout.NORTH);

        if (PageManager.getRole().equals(Role.ADMIN)) {
            final JButton addButton = new JButton("Add");

            buttonPanel.add(addButton);
            buttonPanel.add(editButton);
            buttonPanel.add(removeButton);
            addButton.addActionListener(e -> new AddEditSportsmanCharacteristicPage(null));
            removeButton.setEnabled(false);
            editButton.setEnabled(false);
            editButton.addActionListener(e ->
                    new AddEditSportsmanCharacteristicPage(sportsmanCompetitionList.
                            get(entityTable.getSelectedRow())));
            removeButton.addActionListener(e -> {
                if (!Service.deleteSportCharacteristic(sportsmanCompetitionList
                        .get(entityTable.getSelectedRow()))) {
                    Utils.createErrorDialog(this, "Can not delete sportsman competition", "Error");
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
        sportsmanCompetitionList = Service.getSportCharacteristic();
        sportsmanCompetitionList.forEach(e -> model.addRow(new Object[]{e.getSportsman(),
                e.getSport(), e.getCategory(),
                e.getTrainer(), e.getClub()}));
        entityTable.setModel(model);
       super.updateTable();
    }
}
