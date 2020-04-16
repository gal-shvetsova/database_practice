package gui;

import model.Role;

import javax.swing.*;
import java.awt.*;

public class MainPage extends Page {
    private final JButton clubButton = new JButton("Club");
    private final JButton sportsmanButton = new JButton("Sportsman");
    private final JButton trainerButton = new JButton("Trainer");
    private final JButton sportButton = new JButton("Sport");
    private final JButton organizerButton = new JButton("Organizer");
    private final JButton competitionButton = new JButton("Competition");
    private final JButton competitionSportsmanButton = new JButton("Competition Sportsman");
    private final JButton attributeButton = new JButton("Attributes");
    private final JButton attributeFacilityButton = new JButton("Attribute facility");
    private final JButton facilityButton = new JButton("Facility");
    private final JButton facilityKindButton = new JButton("Facility type");
    private final JButton organizerCompetition = new JButton("Organizer competition");
    private final JButton personButton = new JButton("Person");

    public MainPage() {
        super("Main");
        Container container = getContentPane();
        Role role = Manager.getRole();
        switch (role) {
            case ADMIN:
                container.add(attributeButton);
                container.add(facilityKindButton);
                container.add(attributeFacilityButton);
                container.add(personButton);
                setListenersForAdmin();
            case TRAINER:
            case SPORTSMAN:
                container.add(clubButton);
                container.add(trainerButton);
                container.add(sportButton);
                container.add(facilityButton);
                setListenersForSportsman();
            case ORGANIZER:
                container.add(competitionButton);
                container.add(competitionSportsmanButton);
                container.add(organizerButton);
                container.add(sportsmanButton);
                container.add(organizerCompetition);
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
