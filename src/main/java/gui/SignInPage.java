package gui;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class SignInPage extends Page {
    private static SignInPage instance;

    protected final static int SIZE_WIDTH = 200;
    protected final static int SIZE_HEIGHT = 180;
    protected final static int LOCATION_X = (screenSize.width - SIZE_WIDTH) / 2;
    protected final static int LOCATION_Y = (screenSize.height - SIZE_HEIGHT) / 2;

    private final JTextField loginTextField = new JTextField("login");
    private final JTextField passwordTextField = new JTextField("password");

    public static SignInPage getInstance() {
        if (instance == null) {
            instance = new SignInPage();
        }
        return instance;
    }

    private SignInPage() {
        super("Sign in");
        JLabel loginLabel = new JLabel("Login");
        JLabel passwordLabel = new JLabel("Password");
        Container container = getContentPane();
        container.setLayout(new GridLayout(0, 1));
        this.setBounds(LOCATION_X, LOCATION_Y, SIZE_WIDTH, SIZE_HEIGHT);
        container.add(loginLabel);
        container.add(loginTextField);
        container.add(passwordLabel);
        container.add(passwordTextField);
        JButton okButton = new JButton("Ok");
        container.add(okButton);
        JButton backButton = new JButton("Back");
        container.add(backButton);

        okButton.addActionListener(e -> {
            try {
                if (PageManager.signIn(loginTextField.getText(), passwordTextField.getText())) {
                    PageManager.hideUpperPage();
                    (new PageManager(MainPage.getInstance())).showPage();
                } else {
                    JOptionPane.showMessageDialog(this,
                            "Signing in error", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(this,
                        "Error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        backButton.addActionListener(e -> {
            PageManager.hideUpperPage();
            (new PageManager(EnterPage.getInstance())).showPage();
        });
    }
}
