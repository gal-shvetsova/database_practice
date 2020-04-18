package gui;

import model.FacilityKind;
import model.Role;
import sun.security.krb5.internal.PAData;

import javax.swing.*;
import java.awt.*;

public class MainPage extends Page {
    private static MainPage instance;

    protected final static int SIZE_WIDTH = 400;
    protected final static int SIZE_HEIGHT =300;
    protected final static int LOCATION_X = (screenSize.width - SIZE_WIDTH) / 2;
    protected final static int LOCATION_Y = (screenSize.height - SIZE_HEIGHT) / 2;
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

    public static MainPage getInstance(){
        if (instance == null){
            instance = new MainPage();
        }
        return instance;
    }

    private MainPage() {
        super("Main");
        Container container = getContentPane();
        container.setLayout(new GridLayout(4,3));
        setBounds(LOCATION_X, LOCATION_Y, SIZE_WIDTH, SIZE_HEIGHT);
        Role role = PageManager.getRole();
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
            PageManager.hideUpperPage();
            new PageManager(FacilityKindPage.getInstance()).showPage();
        });

        personButton.addActionListener(e -> {
            PageManager.hideUpperPage();
            new PageManager(PersonPage.getInstance()).showPage();
        });

        attributeFacilityKindsButton.addActionListener(e -> {
            PageManager.hideUpperPage();
            new PageManager(AttributeFacilityKindPage.getInstance()).showPage();
        });
    }

    private void setListenersForSportsman(){
        clubButton.addActionListener(e -> {
            PageManager.hideUpperPage();
            new PageManager(ClubPage.getInstance()).showPage();
        });

        sportButton.addActionListener(e -> {
            PageManager.hideUpperPage();
            new PageManager(SportPage.getInstance()).showPage();
        });

        facilityButton.addActionListener(e -> {
            PageManager.hideUpperPage();
            new PageManager(FacilityPage.getInstance());
        });
    }

    private void setListenersForOrganizer(){
        competitionButton.addActionListener(e -> {
            PageManager.hideUpperPage();
            new PageManager(CompetitionPage.getInstance());
        });

        sportsmanButton.addActionListener(e -> {
            PageManager.hideUpperPage();
            new PageManager(new SportsmanPage());
        });

    }
}
