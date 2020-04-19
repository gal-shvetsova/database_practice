package gui;

import dao.Service;
import gui.addEditPages.AddEditSportsmanPage;
import model.Role;
import model.Sportsman;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SportsmanPage extends AbstractPageWithTable {
    private static SportsmanPage instance;

    private List<Sportsman> sportsmen;
    private final Object[] columnsHeader = new String[]{"Name", "Surname", "Sport", "Club"};

    public static SportsmanPage getInstance() {
        if (instance == null) {
            instance = new SportsmanPage();
        }
        return instance;
    }

    public SportsmanPage() {
        super("Sportsman");
        updateTable();
        final JPanel buttonPanel = new JPanel();

        updateTable();
        Container container = getContentPane();

        container.add(entityPane, BorderLayout.NORTH);
        if (PageManager.getRole().equals(Role.ADMIN)) {
            final JButton addButton = new JButton("Add");
            final JButton removeButton = new JButton("Remove");
            buttonPanel.add(editButton);
            buttonPanel.add(removeButton);
            editButton.setEnabled(false);
            addButton.addActionListener(e -> new AddEditSportsmanPage(null));
            editButton.addActionListener(e -> new AddEditSportsmanPage(sportsmen.get(entityTable.getSelectedRow())));
        }
        buttonPanel.add(backButton);

        container.add(buttonPanel, BorderLayout.SOUTH);
        backButton.addActionListener(e -> {
            PageManager.hideUpperPage();
            new PageManager(MainPage.getInstance()).showPage();
        });
    }

    @Override
    protected void updateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnsHeader);
        sportsmen = Service.getAllSportsmen();
        sportsmen.forEach(e -> model.addRow(new Object[]{e.getName(), e.getSurname(), e.getSport(), e.getClub()}));
        entityTable.setModel(model);
        super.updateTable();
    }

}
