package gui;

import dao.Service;
import model.Facility;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public abstract class AbstractPageWithTable extends Page {
    protected final static int SIZE_WIDTH = 500;
    protected final static int SIZE_HEIGHT = 500;
    protected final static int LOCATION_X = (screenSize.width - SIZE_WIDTH) / 2;
    protected final static int LOCATION_Y = (screenSize.height - SIZE_HEIGHT) / 2;
    protected final JButton editButton = new JButton("Edit");
    protected final JButton addButton = new JButton("Add");
    protected final JButton removeButton = new JButton("Remove");
    protected final JButton backButton = new JButton("Back");
    protected final JTable entityTable = new JTable();
    protected JScrollPane entityPane = new JScrollPane(entityTable);

    public AbstractPageWithTable(String name) {
        super(name);
        setBounds(LOCATION_X, LOCATION_Y, SIZE_WIDTH, SIZE_HEIGHT);
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        backButton.addActionListener(e -> {
            PageManager.hideUpperPage();
            new PageManager(MainPage.getInstance()).showPage();
        });
    }

    protected void updateTable(){
        entityTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        ListSelectionModel selectionModel = entityTable.getSelectionModel();
        selectionModel.addListSelectionListener(e -> {
            editButton.setEnabled(true);
            removeButton.setEnabled(true);
        });
        getContentPane().remove(entityPane);
        entityPane = new JScrollPane(entityPane);
        getContentPane().add(entityPane, BorderLayout.NORTH);

    }

    @Override
    public void setVisible(boolean b) {
        editButton.setEnabled(false);
        removeButton.setEnabled(false);
        if (b) {
            updateTable();
        }
        super.setVisible(b);
    }
}
