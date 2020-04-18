package gui;

import dao.Service;
import gui.addEditPages.AddEditClubPage;
import model.Club;
import model.Role;

import javax.swing.*;
import java.awt.*;

public class ClubPage extends AbstractPageWithList {
    private final JList<Club> clubList = new JList<>(Service.getAllClubs().toArray(new Club[0]));
    private final JButton removeButton = new JButton("Remove");
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

        container.add(clubList, BorderLayout.NORTH);

        if (PageManager.getRole().equals(Role.ADMIN)) {
            final JButton addButton = new JButton("Add");

            editButton.setEnabled(false);
            removeButton.setEnabled(false);
            buttonPanel.add(addButton);
            buttonPanel.add(editButton);
            buttonPanel.add(removeButton);
            addButton.addActionListener(e -> new AddEditClubPage(null));
            editButton.addActionListener(e -> new AddEditClubPage(clubList.getSelectedValue()));
            removeButton.addActionListener(e -> {
                if (!Service.deleteClub(clubList.getSelectedValue())) {
                    Utils.createErrorDialog(this, "Can not delete club", "Error");
                }
            });
            clubList.addListSelectionListener(e -> editButton.setEnabled(true));
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
        DefaultListModel<Club> model = new DefaultListModel<>();
        Service.getAllClubs().forEach(model::addElement);
        clubList.setModel(model);
        clubList.addListSelectionListener(e -> {
            editButton.setEnabled(true);
            removeButton.setEnabled(true);
        });
    }

    @Override
    public void setVisible(boolean b) {
        updateList();
        super.setVisible(b);
    }
}
