package gui;

import dao.Service;
import model.Competition;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class FacilitiesByCompetitionPeriod extends AbstractFilterPage {
    private static FacilitiesByCompetitionPeriod instance;

    private final Object[] columnHeader = new String[]{"Facility", "Competition", "From", "To"};
    private final List<Competition> competitionList = new ArrayList<>();
    private final JTextField fromTextField = new JTextField();
    private final JTextField toTextField = new JTextField();

    public static FacilitiesByCompetitionPeriod getInstance(){
        if (instance == null){
            instance = new FacilitiesByCompetitionPeriod();
        }
        return instance;
    }

    protected FacilitiesByCompetitionPeriod() {
        super("Facilities by competition period");

        final Container container = getContentPane();
        final JLabel fromLabel = new JLabel("From");
        final JLabel toLabel = new JLabel("To");

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                competitionList.clear();
                String postfix = "T18:35:24.00Z";
                competitionList.addAll(Service.getCompetitionsByDate(
                        Instant.parse(fromTextField.getText() + postfix),
                        Instant.parse(toTextField.getText() + postfix)));
                updateTable();
            }
        });

        container.add(fromLabel);
        container.add(fromTextField);
        container.add(toLabel);
        container.add(toTextField);

        final JPanel buttonPanel = new JPanel();
        buttonPanel.add(okButton);
        buttonPanel.add(backButton);

        container.add(buttonPanel);
    }

    @Override
    protected void updateTable() {
        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnHeader);
        if (competitionList != null) {
            competitionList.forEach(e -> model.addRow(new Object[]{e.getFacility(), e, e.getStartDate(), e.getFinishDate()}));
        }
        entityTable.setModel(model);
        if (isVisible()) {
            this.validateTree();
        }
        super.updateTable();
    }
}
