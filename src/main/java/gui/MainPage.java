package gui;

import model.Role;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPage extends Page {
    private final JButton clubButton = new JButton("Club");
    private final JButton sportsmanButton = new JButton("Sportsman");
    private final JButton sportButton = new JButton("Sport");
    private final JButton competitionButton = new JButton("Competition");
    private final JButton competitionSportsmanButton = new JButton("Competition Sportsman");
    private final JButton attributeFacilityButton = new JButton("Attribute facility");
    private final JButton attributeFacilityKindsButton = new JButton("Attribute facility kind");
    private final JButton facilityButton = new JButton("Facility");
    private final JButton facilityKindButton = new JButton("Facility type");
    private final JButton personButton = new JButton("Person");

    public MainPage() {
        super("Main");
        Container container = getContentPane();
        container.setLayout(new GridLayout(4,3));
        Role role = Manager.getRole();
        switch (role) {
            case ADMIN:
                container.add(attributeFacilityButton);
                container.add(facilityKindButton);
                container.add(attributeFacilityKindsButton);
                container.add(personButton);
                setListenersForAdmin();
            case TRAINER:
            case SPORTSMAN:
                container.add(clubButton);
                container.add(sportButton);
                container.add(facilityButton);
                setListenersForSportsman();
            case ORGANIZER:
                container.add(competitionButton);
                container.add(competitionSportsmanButton);
                container.add(sportsmanButton);
                setListenersForOrganizer();
        }
    }

    private void setListenersForAdmin(){
        facilityKindButton.addActionListener(e -> {
            Manager.hideMainPage();
            Manager.showFacilityKindPage();
        });

        personButton.addActionListener(e -> {
            Manager.hideMainPage();
            Manager.showPersonPage();
        });

        attributeFacilityKindsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager.hideMainPage();
                Manager.showAttributeFacilityKindPage();
            }
        });
    }

    private void setListenersForSportsman(){
        clubButton.addActionListener(e -> {
            Manager.hideMainPage();
            Manager.showClubPage();
        });

        sportButton.addActionListener(e -> {
            Manager.hideMainPage();
            Manager.showSportPage();
        });

        facilityButton.addActionListener(e -> {
            Manager.hideMainPage();
            Manager.showFacilityPage();
        });
    }

    private void setListenersForOrganizer(){
        competitionButton.addActionListener(e -> {
            Manager.hideMainPage();
            Manager.showCompetitionPage();
        });

        sportsmanButton.addActionListener(e -> {
            Manager.hideMainPage();
            Manager.showSportsmanPage();
        });

    }
}
