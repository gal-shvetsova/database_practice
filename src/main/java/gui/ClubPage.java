package gui;

import dao.Service;
import gui.addEditPages.AddEditClubPage;
import model.Club;
import model.Model;
import model.Role;

import javax.swing.*;
import java.awt.*;

public class ClubPage extends AbstractPageWithList {
    private static ClubPage instance;

    public static ClubPage getInstance() {
        if (instance == null) {
            instance = new ClubPage();
        }
        return instance;
    }

    private ClubPage() {
        super("Club");
        updateList();

        final Container container = getContentPane();
        final JPanel buttonPanel = new JPanel();

        container.add(entityList, BorderLayout.NORTH);

        if (PageManager.getRole().equals(Role.ADMIN)) {
            final JButton addButton = new JButton("Add");

            editButton.setEnabled(false);
            removeButton.setEnabled(false);
            buttonPanel.add(addButton);
            buttonPanel.add(editButton);
            buttonPanel.add(removeButton);
            addButton.addActionListener(e -> new AddEditClubPage(null));
            editButton.addActionListener(e -> new AddEditClubPage((Club)entityList.getSelectedValue()));
            removeButton.addActionListener(e -> {
                if (!Service.deleteClub((Club)entityList.getSelectedValue())) {
                    Utils.createErrorDialog(this, "Can not delete club", "Error");
                }
            });
            entityList.addListSelectionListener(e -> editButton.setEnabled(true));
        }

        final JButton backButton = new JButton("Back");
        buttonPanel.add(backButton);

        container.add(buttonPanel, BorderLayout.SOUTH);
        backButton.addActionListener(e -> {
            PageManager.hideUpperPage();
            (new PageManager(MainPage.getInstance())).showPage();
        });
    }

    @Override
    public void updateList() {
        DefaultListModel<Model> model = new DefaultListModel<>();
        Service.getAllClubs().forEach(model::addElement);
        entityList.setModel(model);
        super.updateList();
    }
}
