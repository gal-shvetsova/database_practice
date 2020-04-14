package gui;

import javax.swing.*;
import java.awt.*;

public class MainPage extends Page {
    public MainPage() {
        super("Main page");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container container = getContentPane();
        container.setLayout (new FlowLayout(FlowLayout.CENTER));
        JButton signIn = new JButton("Sign in");
        JButton register = new JButton("Register");
        JButton exit = new JButton("Exit");
        container.add(signIn);
        container.add(register);
        container.add(exit);
        pack();
        setVisible(true);
    }
}
