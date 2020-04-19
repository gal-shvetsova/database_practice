package gui;

import javax.swing.*;
import java.awt.*;

public class RegisterPage extends Page {
    private static RegisterPage instance;

    protected final static int SIZE_WIDTH = 200;
    protected final static int SIZE_HEIGHT =100;
    protected final static int LOCATION_X = (screenSize.width - SIZE_WIDTH) / 2;
    protected final static int LOCATION_Y = (screenSize.height - SIZE_HEIGHT) / 2;

    public static RegisterPage getInstance(){
        if (instance == null){
            instance = new RegisterPage();
        }
        return instance;
    }

    private RegisterPage() {
        super("Register");
        JButton backButton = new JButton("Back");
        JButton sportsmanButton = new JButton("Register as sportsman");
        JButton organizerTrainerButton = new JButton("Register as organizer/trainer");

        Container container = getContentPane();
        container.setLayout(new GridLayout(0,1));
        container.add(sportsmanButton);
        container.add(organizerTrainerButton);
        container.add(backButton);
        setBounds(LOCATION_X, LOCATION_Y, SIZE_WIDTH, SIZE_HEIGHT);
        sportsmanButton.addActionListener(e -> {
            PageManager.hideUpperPage();
            (new PageManager(RegisterSportsmanPage.getInstance())).showPage();
        });

        organizerTrainerButton.addActionListener(e -> {
            PageManager.hideUpperPage();
            (new PageManager(RegisterOthersPage.getInstance())).showPage();
        });

        backButton.addActionListener(e -> {
            PageManager.hideUpperPage();
            (new PageManager(EnterPage.getInstance())).showPage();
        });
    }
}
