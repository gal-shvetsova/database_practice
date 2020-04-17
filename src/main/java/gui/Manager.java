package gui;

import dao.Service;
import model.Facility;
import model.FacilityKind;
import model.Person;
import model.Role;

import javax.swing.*;

public class Manager {
    private final static EnterPage ENTER_PAGE = new EnterPage();
    private final static SignInPage SIGN_IN_PAGE = new SignInPage();
    private static MainPage mainPage;
    private static ClubPage clubPage;
    private static SportPage sportPage;
    private static FacilityKindPage facilityKindPage;
    private static FacilityPage facilityPage;
    private static CompetitionPage competitionPage;
    private static SportsmanPage sportsmanPage;
    private static PersonPage personPage;
    private static final RegisterPage REGISTER_PAGE = new RegisterPage();
    private static final RegisterSportsmanPage REGISTER_SPORTSMAN_PAGE = new RegisterSportsmanPage();
    private static final RegisterOthersPage REGISTER_OTHERS_PAGE = new RegisterOthersPage();

    private static Person person;
    private static AttributeFacilityKindPage attributeFacilityKindPage;

    public static Role getRole(){
        return Role.SPORTSMAN;
    }

    public static void showEnterPage(){
        ENTER_PAGE.setVisible(true);
    }

    public static void hideEnterPage(){
        ENTER_PAGE.setVisible(false);
    }

    public static void showSignInPage(){
        SIGN_IN_PAGE.setVisible(true);
    }

    public static void hideSignInPage(){
        SIGN_IN_PAGE.setVisible(false);
    }

    public static void showMainPage(){
        mainPage = new MainPage();
        mainPage.setVisible(true);
    }

    public static void hideMainPage(){
        mainPage.setVisible(false);
    }

    public static void showClubPage(){
        clubPage = new ClubPage();
        clubPage.setVisible(true);
    }

    public static void hideClubPage(){
        clubPage.setVisible(false);
        clubPage.dispose();
    }

    public static void signIn(String login, String password) {
        person = Service.signIn(login, password);
        if (person == null){
          //  JOptionPane.showMessageDialog(SIGN_IN_PAGE,
              //      "Error",
               //     "Incorrect login/password",
                //    JOptionPane.ERROR_MESSAGE);
        } else {
            mainPage = new MainPage();
            clubPage = new ClubPage();
            hideSignInPage();
            hideRegisterPage();
            hideRegisterSportsman();
            hideRegisterOthers();
            showMainPage();
        }
    }

    public static void showSportPage() {
        sportPage = new SportPage();
        sportPage.setVisible(true);
    }

    public static void hideSportPage(){
        sportPage.setVisible(false);
        sportPage.dispose();
        sportPage = null;
    }

    public static void showFacilityKindPage(){
        facilityKindPage = new FacilityKindPage();
        facilityKindPage.setVisible(true);
    }

    public static void hideFacilityKindPage(){
        facilityKindPage.setVisible(false);
        facilityKindPage.dispose();
        facilityKindPage = null;
    }

    public static void showFacilityPage(){
        facilityPage = new FacilityPage();
        facilityPage.setVisible(true);
    }

    public static void hideFacilityPage() {
        facilityPage.setVisible(false);
        facilityPage.dispose();
        facilityPage = null;
    }

    public static void showCompetitionPage(){
        competitionPage = new CompetitionPage();
        competitionPage.setVisible(true);
    }

    public static void hideCompetitionPage(){
        competitionPage.setVisible(false);
        competitionPage.dispose();
        competitionPage = null;
    }

    public static void showSportsmanPage(){
        sportsmanPage = new SportsmanPage();
        sportsmanPage.setVisible(true);
    }

    public static void hideSportsmanPage() {
        sportsmanPage.setVisible(false);
        sportsmanPage.dispose();
    }

    public static void showPersonPage(){
        personPage = new PersonPage();
        personPage.setVisible(true);
    }

    public static void hidePersonPage(){
        personPage.setVisible(false);
        personPage.dispose();
    }

    public static void showRegisterPage(){
        REGISTER_PAGE.setVisible(true);
    }

    public static void hideRegisterPage(){
        REGISTER_PAGE.setVisible(false);
    }

    public static void showRegisterSportsman(){
        REGISTER_SPORTSMAN_PAGE.setVisible(true);
    }

    public static void hideRegisterSportsman(){
        REGISTER_SPORTSMAN_PAGE.setVisible(false);
    }

    public static void showRegisterOthers(){
        REGISTER_OTHERS_PAGE.setVisible(true);
    }

    public static void hideRegisterOthers(){
        REGISTER_OTHERS_PAGE.setVisible(false);
    }

    public static void showAttributeFacilityKindPage() {
        attributeFacilityKindPage = new AttributeFacilityKindPage();
        attributeFacilityKindPage.setVisible(true);
    }

    public static void hideAttributeFacilityKindPage() {
        attributeFacilityKindPage.setVisible(false);
        attributeFacilityKindPage.dispose();
    }
}
