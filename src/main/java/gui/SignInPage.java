package gui;

import javax.swing.*;
import java.awt.*;

public class SignInPage extends Page {
    private final JTextField loginTextField = new JTextField("login");
    private final JTextField passwordTextField = new JTextField("password");

    public SignInPage() {
        super("Sign in");
        Container container = getContentPane();
        container.add(loginTextField);
        container.add(passwordTextField);
        JButton okButton = new JButton("Ok");
        container.add(okButton);
        JButton backButton = new JButton("Back");
        container.add(backButton);
        pack();

        okButton.addActionListener(e -> Manager.signIn(loginTextField.getText(), passwordTextField.getText()));
    }
}
