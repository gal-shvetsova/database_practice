package gui;

import javax.swing.*;
import java.awt.*;

public class EnterPage extends Page {
    private static EnterPage instance;

    protected final static int SIZE_WIDTH = 200;
    protected final static int SIZE_HEIGHT = 100;
    protected final static int LOCATION_X = (screenSize.width - SIZE_WIDTH) / 2;
    protected final static int LOCATION_Y = (screenSize.height - SIZE_HEIGHT) / 2;

    public static EnterPage getInstance(){
        if (instance == null){
            instance = new EnterPage();
        }
        return instance;
    }

    private EnterPage() {
        super("Enter page");
        Container container = getContentPane();
        container.setLayout(new GridLayout(0, 1));
        JButton signInButton = new JButton("Sign in");
        container.add(signInButton);
        JButton registerButton = new JButton("Register");
        container.add(registerButton);
        setBounds(LOCATION_X, LOCATION_Y, SIZE_WIDTH, SIZE_HEIGHT);
        setResizable(false);

        signInButton.addActionListener(e -> {
            PageManager.hideUpperPage();
            (new PageManager(SignInPage.getInstance())).showPage();
        });

        registerButton.addActionListener(e -> {
            PageManager.hideUpperPage();
            (new PageManager(RegisterPage.getInstance())).showPage();
        });
    }


}
