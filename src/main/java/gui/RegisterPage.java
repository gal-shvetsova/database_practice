package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterPage extends Page {
    public RegisterPage() {
        super("Register");
        JButton backButton = new JButton("Back");
        JButton sportsmanButton = new JButton("Register as sportsman");
        JButton organizerButton = new JButton("Register as organizer");
        JButton trainerButton = new JButton("Register as trainer");
        Container container = getContentPane();

        container.add(sportsmanButton);
        container.add(organizerButton);
        container.add(trainerButton);

        sportsmanButton.addActionListener(e -> {
            Manager.hideRegisterPage();
            Manager.showRegisterSportsman();
        });

        backButton.addActionListener(e -> {
            Manager.hideRegisterPage();
            Manager.showEnterPage();
        });
        pack();
    }
}
