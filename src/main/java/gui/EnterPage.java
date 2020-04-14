package gui;

import javax.swing.*;
import java.awt.*;

public class EnterPage extends Page {
    private final JButton signInButton = new JButton("Sign in");
    private final JButton registerButton = new JButton("Register");

    public EnterPage() {
        super("Main page");
        Container container = getContentPane();
        container.add(signInButton);
        container.add(registerButton);
        pack();

        signInButton.addActionListener(e -> {
            Manager.hideEnterPage();
            Manager.showSignInPage();
        });

        registerButton.addActionListener(e -> {

        });

    }


}
