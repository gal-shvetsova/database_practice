package gui;

import javax.swing.*;
import java.awt.*;

public class EnterPage extends Page {

    public EnterPage() {
        super("Main page");
        Container container = getContentPane();
        JButton signInButton = new JButton("Sign in");
        container.add(signInButton);
        JButton registerButton = new JButton("Register");
        container.add(registerButton);
        pack();

        signInButton.addActionListener(e -> {
            Manager.hideEnterPage();
            Manager.showSignInPage();
        });

        registerButton.addActionListener(e -> {
            Manager.showRegisterPage();
            Manager.hideEnterPage();
        });

    }


}
