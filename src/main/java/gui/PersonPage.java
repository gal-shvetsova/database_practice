package gui;

import dao.Service;
import gui.addEditPages.AddEditPersonPage;
import model.Person;
import model.Role;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PersonPage extends AbstractPageWithTable {
    private static PersonPage instance;

    private List<Person> personList;
    private final Object[] columnsHeader = new String[]{"Name", "Surname", "Role"};

    public static PersonPage getInstance() {
        if (instance == null) {
            instance = new PersonPage();
        }
        return instance;
    }

    private PersonPage() {
        super("Person");
        final JButton backButton = new JButton("Back");
        final JPanel buttonPanel = new JPanel();

        updateTable();
        Container container = getContentPane();
        container.add(entityPane, BorderLayout.NORTH);

        if (PageManager.getRole().equals(Role.ADMIN)) {
            buttonPanel.add(editButton);
            editButton.setEnabled(false);
            editButton.addActionListener(e
                    -> new AddEditPersonPage(personList.get(entityTable.getSelectedRow())));
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
        personList = Service.getAllPerson();
        personList.forEach(e -> model.addRow(new Object[]{e.getName(), e.getSurname(), e.getRole()}));
        entityTable.setModel(model);
        super.updateTable();
    }
}
