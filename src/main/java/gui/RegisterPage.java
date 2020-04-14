package gui;

import javax.swing.*;
import java.awt.*;

public class RegisterPage extends Page {
    private final JTextField loginTextField = new JTextField("login");
    private final JTextField passwordTextField = new JTextField("password");
    private final JTextField nameTextField = new JTextField("name");
    private final JTextField surnameTextField = new JTextField("surname");

    private final JButton okButton = new JButton("Ok");
    private final JButton backButton = new JButton("Back");

    public RegisterPage() {
        super("Register");
        Container container = getContentPane();
        container.add(loginTextField);
        container.add(passwordTextField);
        container.add(okButton);
        container.add(backButton);
        pack();
    }
}
