package gui;

import dao.Service;
import model.Person;
import model.Role;

import javax.swing.*;

public class Manager {
    private final static EnterPage ENTER_PAGE = new EnterPage();
    private final static SignInPage SIGN_IN_PAGE = new SignInPage();
    private static MainPage MAIN_PAGE;
    private static final ClubPage CLUB_PAGE = new ClubPage();

    private static Person person;

    public static Role getRole(){
        return person.getRole();
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
        MAIN_PAGE.setVisible(true);
    }

    public static void hideMainPage(){
        MAIN_PAGE.setVisible(false);
    }

    public static void showClubPage(){
        CLUB_PAGE.setVisible(true);
    }

    public static void hideClubPage(){
        CLUB_PAGE.setVisible(false);
    }

    public static void signIn(String login, String password) {
        person = Service.signIn(login, password);
        if (person == null){
            JOptionPane.showMessageDialog(SIGN_IN_PAGE,
                    "Error",
                    "Incorrect login/password",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            MAIN_PAGE = new MainPage();
            hideSignInPage();
            showMainPage();
        }
    }
}
