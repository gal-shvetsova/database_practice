package gui;

import dao.Service;
import gui.addEditPages.AddEditSportPage;
import model.Model;
import model.Role;
import model.Sport;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class SportPage extends AbstractPageWithList {
    private static SportPage instance;

    public static SportPage getInstance() {
        if (instance == null) {
            instance = new SportPage();
        }
        return instance;
    }

    private SportPage() {
        super("Sport");

        final Container container = getContentPane();
        final JPanel panel = new JPanel();

        container.add(entityList, BorderLayout.NORTH);
        updateList();

        if (PageManager.getRole().equals(Role.ADMIN)) {
            final JButton addButton = new JButton("Add");
            panel.add(addButton);
            panel.add(editButton);
            panel.add(removeButton);
            editButton.setEnabled(false);
            removeButton.setEnabled(false);
            addButton.addActionListener(e -> new AddEditSportPage(null));
            editButton.addActionListener(e -> new AddEditSportPage((Sport) entityList.getSelectedValue()));
            removeButton.addActionListener(e -> {
                try {
                    if (!Service.deleteSport((Sport) entityList.getSelectedValue())) {
                        Utils.createErrorDialog(this, "Can not delete sport", "Error");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(this,
                            "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            });
        }
        panel.add(backButton);
        container.add(panel, BorderLayout.SOUTH);
    }

    @Override
    public void updateList() {
        DefaultListModel<Model> model = new DefaultListModel<>();
        try {
            Service.getAllSports().forEach(model::addElement);
        } catch (SQLException ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(this,
                    "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        entityList.setModel(model);
        super.updateList();
    }
}
