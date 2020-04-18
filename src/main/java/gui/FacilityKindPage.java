package gui;

import dao.Service;
import gui.addEditPages.AddEditFacilityKindPage;
import model.FacilityKind;
import model.Role;

import javax.swing.*;
import java.awt.*;

public class FacilityKindPage extends AbstractPageWithList {
    private static FacilityKindPage instance;

    private final JList<FacilityKind> facilityKindList =
            new JList<>(Service.getAllFacilityKinds().toArray(new FacilityKind[0]));
    private final JButton editButton = new JButton("Edit");

    public static FacilityKindPage getInstance(){
        if (instance == null){
            instance = new FacilityKindPage();
        }
        return instance;
    }

    private FacilityKindPage() {
        super("Facility kind");
        final JButton backButton = new JButton("Back");
        final JPanel buttonPanel = new JPanel();

        Container container = getContentPane();
        container.add(facilityKindList, BorderLayout.NORTH);


        if (PageManager.getRole().equals(Role.ADMIN)) {
            final JButton addButton = new JButton("Add");
            final JButton removeButton = new JButton("Remove");
            buttonPanel.add(addButton);
            buttonPanel.add(editButton);
            buttonPanel.add(removeButton);
            facilityKindList.addListSelectionListener(e -> editButton.setEnabled(true));
            editButton.setEnabled(false);
            addButton.addActionListener(e -> new AddEditFacilityKindPage(null));
            editButton.addActionListener(e -> new AddEditFacilityKindPage(facilityKindList.getSelectedValue()));
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
        DefaultListModel<FacilityKind> model = new DefaultListModel<>();
        Service.getAllFacilityKinds().forEach(model::addElement);
        facilityKindList.setModel(model);
        facilityKindList.addListSelectionListener(e -> editButton.setEnabled(true));
    }

    @Override
    public void setVisible(boolean b) {
        updateList();
        super.setVisible(b);
    }
}
