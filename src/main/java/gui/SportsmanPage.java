package gui;

import dao.Service;
import gui.addEditPages.AddEditSportsmanPage;
import model.Role;
import model.Sportsman;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class SportsmanPage extends Page {
    private JTable sportsmenTable;
    private List<Sportsman> sportsmen;
    private Object[] columnsHeader = new String[]{"Name", "Surname", "Sport", "Club"};
    JButton editButton = new JButton("Edit");

    public SportsmanPage() {
        super("Sportsman");
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");
        JButton backButton = new JButton("Back");

        updateTable();
        Container container = getContentPane();
        container.add(sportsmenTable);
        editButton.setEnabled(false);
        if (Manager.getRole().equals(Role.ADMIN)) {
            container.add(addButton);
            container.add(editButton);
            container.add(removeButton);
        }
        container.add(backButton);

        addButton.addActionListener(e -> new AddEditSportsmanPage(null));
        editButton.addActionListener(e -> new AddEditSportsmanPage(sportsmen.get(sportsmenTable.getSelectedRow())));
        backButton.addActionListener(e -> {
            Manager.hideSportsmanPage();
            Manager.showMainPage();
        });
        pack();

    }

    private void updateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnsHeader);
        sportsmen = Service.getAllSportsmen();
        sportsmen.forEach(e -> model.addRow(new Object[]{e.getName(), e.getSurname(), e.getSport(), e.getClub()}));
        sportsmenTable = new JTable(model);
        sportsmenTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = sportsmenTable.getSelectionModel();
        selectionModel.addListSelectionListener(e -> editButton.setEnabled(true));
    }

}
