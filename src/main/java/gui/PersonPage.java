package gui;

import dao.Service;
import gui.addEditPages.AddEditPersonPage;
import model.Person;
import model.Role;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PersonPage extends Page{
    private JTable personTable;
    private List<Person> personList;
    private Object[] columnsHeader = new String[]{"Name", "Surname", "Role"};
    JButton editButton = new JButton("Edit");

    public PersonPage() {
        super("person");
        JButton backButton = new JButton("Back");

        updateTable();
        Container container = getContentPane();
        container.add(personTable);
        editButton.setEnabled(false);
        if (Manager.getRole().equals(Role.ADMIN)) {
            container.add(editButton);
        }
        container.add(backButton);

        editButton.addActionListener(e -> new AddEditPersonPage(personList.get(personTable.getSelectedRow())));

        backButton.addActionListener(e -> {
            Manager.hidePersonPage();
            Manager.showMainPage();
        });

        pack();

    }

    private void updateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnsHeader);
        personList = Service.getAllPerson();
        personList.forEach(e -> model.addRow(new Object[]{e.getName(), e.getSurname(), e.getRole()}));
        personTable = new JTable(model);
        personTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = personTable.getSelectionModel();
        selectionModel.addListSelectionListener(e -> editButton.setEnabled(true));
    }
}
