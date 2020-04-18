package gui;

import dao.Service;
import gui.addEditPages.AddEditSportsmanPage;
import model.Role;
import model.Sportsman;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.List;

public class SportsmanPage extends Page {
    private static SportsmanPage instance;

    protected final static int SIZE_WIDTH = 500;
    protected final static int SIZE_HEIGHT = 500;
    protected final static int LOCATION_X = (screenSize.width - SIZE_WIDTH) / 2;
    protected final static int LOCATION_Y = (screenSize.height - SIZE_HEIGHT) / 2;
    private final JTable sportsmenTable = new JTable();
    private List<Sportsman> sportsmen;
    private final Object[] columnsHeader = new String[]{"Name", "Surname", "Sport", "Club"};
    private final JButton editButton = new JButton("Edit");

    public static SportsmanPage getInstance(){
        if (instance == null){
            instance = new SportsmanPage();
        }
        return instance;
    }

    public SportsmanPage() {
        super("Sportsman");
        updateTable();
        final JButton backButton = new JButton("Back");
        final JPanel buttonPanel = new JPanel();


        updateTable();
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        setBounds(LOCATION_X, LOCATION_Y, SIZE_WIDTH, SIZE_HEIGHT);
        container.add(new JScrollPane(sportsmenTable), BorderLayout.NORTH);
        if (PageManager.getRole().equals(Role.ADMIN)) {
            final JButton addButton = new JButton("Add");
            final JButton removeButton = new JButton("Remove");
            buttonPanel.add(addButton);
            buttonPanel.add(editButton);
            buttonPanel.add(removeButton);
            editButton.setEnabled(false);
            addButton.addActionListener(e -> new AddEditSportsmanPage(null));
            editButton.addActionListener(e -> new AddEditSportsmanPage(sportsmen.get(sportsmenTable.getSelectedRow())));
        }
        buttonPanel.add(backButton);

        container.add(buttonPanel, BorderLayout.SOUTH);
        backButton.addActionListener(e -> {
            PageManager.hideUpperPage();
            new PageManager(MainPage.getInstance());
        });
    }

    private void updateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnsHeader);
        sportsmen = Service.getAllSportsmen();
        sportsmen.forEach(e -> model.addRow(new Object[]{e.getName(), e.getSurname(), e.getSport(), e.getClub()}));
        sportsmenTable.setModel(model);
        sportsmenTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = sportsmenTable.getSelectionModel();
        selectionModel.addListSelectionListener(e -> editButton.setEnabled(true));
    }

}
