package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SignInPage extends Page{
    private final JTextField loginTextField = new JTextField("login");
    private final JTextField passwordTextField = new JTextField("password");
    private final JButton okButton = new JButton("Ok");
    private final JButton backButton = new JButton("Back");

    public SignInPage() {
        super("Sign in");
        Container container = getContentPane();
        container.add(loginTextField);
        container.add(passwordTextField);
        container.add(okButton);
        container.add(backButton);
        pack();

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manager.signIn(loginTextField.getText(), passwordTextField.getText());
            }
        });
    }
}
