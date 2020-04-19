package gui;

import dao.Service;
import gui.addEditPages.AddEditFacilityKindPage;
import model.FacilityKind;
import model.Model;
import model.Role;

import javax.swing.*;
import java.awt.*;

public class FacilityKindPage extends AbstractPageWithList {
    private static FacilityKindPage instance;

    public static FacilityKindPage getInstance() {
        if (instance == null) {
            instance = new FacilityKindPage();
        }
        return instance;
    }

    private FacilityKindPage() {
        super("Facility kind");
        final JButton backButton = new JButton("Back");
        final JPanel buttonPanel = new JPanel();

        Container container = getContentPane();
        container.add(entityList, BorderLayout.NORTH);


        if (PageManager.getRole().equals(Role.ADMIN)) {
            final JButton addButton = new JButton("Add");

            buttonPanel.add(addButton);
            buttonPanel.add(editButton);
            buttonPanel.add(removeButton);
            entityList.addListSelectionListener(e -> editButton.setEnabled(true));
            editButton.setEnabled(false);
            removeButton.setEnabled(false);
            addButton.addActionListener(e -> new AddEditFacilityKindPage(null));
            removeButton.addActionListener(e -> {
                if (!Service.deleteFacilityKind((FacilityKind) entityList.getSelectedValue())) {
                    Utils.createErrorDialog(this, "Can not delete facility kind", "Error");
                }
            });
            editButton.addActionListener(e -> new AddEditFacilityKindPage((FacilityKind) entityList.getSelectedValue()));
        }
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
        Service.getAllFacilityKinds().forEach(model::addElement);
        entityList.setModel(model);
        super.updateList();
    }

    @Override
    public void setVisible(boolean b) {
        updateList();
        super.setVisible(b);
    }
}
