package gui;

import javax.swing.*;
import java.awt.*;

public class RegisterPage extends Page {
    protected final static int SIZE_WIDTH = 200;
    protected final static int SIZE_HEIGHT =100;
    protected final static int LOCATION_X = (screenSize.width - SIZE_WIDTH) / 2;
    protected final static int LOCATION_Y = (screenSize.height - SIZE_HEIGHT) / 2;

    public RegisterPage() {
        super("Register");
        JButton backButton = new JButton("Back");
        JButton sportsmanButton = new JButton("Register as sportsman");
        JButton organizerTrainerButton = new JButton("Register as organizer/trainer");
        Container container = getContentPane();
        container.setLayout(new GridLayout(0,1));
        container.add(sportsmanButton);
        container.add(organizerTrainerButton);
        setBounds(LOCATION_X, LOCATION_Y, SIZE_WIDTH, SIZE_HEIGHT);
        sportsmanButton.addActionListener(e -> {
            Manager.hideRegisterPage();
            Manager.showRegisterSportsman();
        });

        organizerTrainerButton.addActionListener(e -> {
            Manager.hideRegisterPage();
            Manager.showRegisterOthers();
        });

        backButton.addActionListener(e -> {
            Manager.hideRegisterPage();
            Manager.showEnterPage();
        });
    }
}
