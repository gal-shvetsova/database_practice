package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInPage extends Page {

    protected final static int SIZE_WIDTH = 200;
    protected final static int SIZE_HEIGHT =180;
    protected final static int LOCATION_X = (screenSize.width - SIZE_WIDTH) / 2;
    protected final static int LOCATION_Y = (screenSize.height - SIZE_HEIGHT) / 2;

    private final JTextField loginTextField = new JTextField("login");
    private final JTextField passwordTextField = new JTextField("password");

    public SignInPage() {
        super("Sign in");
        JLabel loginLabel = new JLabel("Login");
        JLabel passwordLabel = new JLabel("Password");
        Container container = getContentPane();
        container.setLayout(new GridLayout(0,1));
        this.setBounds(LOCATION_X, LOCATION_Y, SIZE_WIDTH, SIZE_HEIGHT);
        container.add(loginLabel);
        container.add(loginTextField);
        container.add(passwordLabel);
        container.add(passwordTextField);
        JButton okButton = new JButton("Ok");
        container.add(okButton);
        JButton backButton = new JButton("Back");
        container.add(backButton);

        okButton.addActionListener(e -> Manager.signIn(loginTextField.getText(), passwordTextField.getText()));
        backButton.addActionListener(e -> {
            Manager.hideSignInPage();
            Manager.showEnterPage();
        });
    }
}
