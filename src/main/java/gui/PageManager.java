package gui;

import dao.Service;
import model.Person;
import model.Role;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Queue;

public class PageManager{
    private static Person person;

    private static Queue<PageManager> pageManagers = new LinkedList<>();
    private final Page page;

    public PageManager(Page page) {
        this.page = page;
    }

    public void showPage(){
        pageManagers.add(this);
        page.setVisible(true);
    }

    public void hidePage(){
        page.setVisible(false);
        page.dispose();
    }

    public static Role getRole(){
        return person.getRole();
    }

    public static void hideUpperPage(){
        if (pageManagers.isEmpty()){
            return;
        }
        pageManagers.poll().hidePage();
    }

    public static boolean signIn(String login, String password) throws SQLException {
        person = Service.signIn(login, password);
        return person != null;
    }
}
