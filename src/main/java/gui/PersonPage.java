package gui;

import dao.Service;
import gui.addEditPages.AddEditPersonPage;
import model.Person;
import model.Role;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PersonPage extends Page {
    private static PersonPage instance;

    protected final static int SIZE_WIDTH = 500;
    protected final static int SIZE_HEIGHT = 500;
    protected final static int LOCATION_X = (screenSize.width - SIZE_WIDTH) / 2;
    protected final static int LOCATION_Y = (screenSize.height - SIZE_HEIGHT) / 2;
    private final JTable personTable = new JTable();
    private List<Person> personList;
    private final Object[] columnsHeader = new String[]{"Name", "Surname", "Role"};
    private final JButton editButton = new JButton("Edit");

    public static PersonPage getInstance(){
        if (instance == null){
            instance = new PersonPage();
        }
        return instance;
    }

    private PersonPage() {
        super("Person");
        final JButton backButton = new JButton("Back");
        final JPanel buttonPanel = new JPanel();

        setBounds(LOCATION_X, LOCATION_Y, SIZE_WIDTH, SIZE_HEIGHT);
        updateTable();
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(personTable, BorderLayout.NORTH);

        if (PageManager.getRole().equals(Role.ADMIN)) {
            buttonPanel.add(editButton);
            editButton.setEnabled(false);
            editButton.addActionListener(e
                    -> new AddEditPersonPage(personList.get(personTable.getSelectedRow())));
        }
        buttonPanel.add(backButton);
        container.add(buttonPanel, BorderLayout.SOUTH);

        backButton.addActionListener(e -> {
            PageManager.hideUpperPage();
            new PageManager(MainPage.getInstance()).showPage();
        });
    }

    private void updateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnsHeader);
        personList = Service.getAllPerson();
        personList.forEach(e -> model.addRow(new Object[]{e.getName(), e.getSurname(), e.getRole()}));
        personTable.setModel(model);
        personTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = personTable.getSelectionModel();
        selectionModel.addListSelectionListener(e -> editButton.setEnabled(true));
    }
}
