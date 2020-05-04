package gui;

import javax.swing.*;
import java.awt.*;

public abstract class AbstractRegisterPage extends Page {
    protected JTextField login = new JTextField();
    protected JTextField password = new JTextField();
    protected JTextField nameText = new JTextField();
    protected JTextField surnameText = new JTextField();
    protected JButton okButton = new JButton("Ok");
    protected JButton cancelButton = new JButton("Cancel");
    protected JLabel loginLabel= new JLabel("Login");
    protected JLabel passwordLabel = new JLabel("Password");
    protected JLabel nameLabel = new JLabel("Name");
    protected JLabel surnameLabel = new JLabel("Surname");


    public AbstractRegisterPage(String name) {
        super(name);
        Container container = getContentPane();
        container.add(loginLabel);
        container.add(login);
        container.add(passwordLabel);
        container.add(password);
        container.add(nameLabel);
        container.add(nameText);
        container.add(surnameLabel);
        container.add(surnameText);
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (b){
            login.setText("");
            password.setText("");
            nameText.setText("");
            surnameText.setText("");
        }
    }
}
