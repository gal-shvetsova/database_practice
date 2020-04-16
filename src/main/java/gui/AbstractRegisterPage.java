package gui;

import javax.swing.*;
import java.awt.*;

public class AbstractRegisterPage extends Page {
    protected JTextField login = new JTextField();
    protected JTextField password = new JTextField();
    protected JTextField nameText = new JTextField();
    protected JTextField surnameText = new JTextField();
    protected JButton okButton = new JButton("Ok");
    protected JButton cancelButton = new JButton("Cancel");

    public AbstractRegisterPage(String name) {
        super(name);
        Container container = getContentPane();
        login.setText("               ");
        password.setText("               ");
        nameText.setText("                ");
        surnameText.setText("             ");
        container.add(login);
        container.add(password);
        container.add(nameText);
        container.add(surnameText);
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (b){
            login.setText("               ");
            password.setText("               ");
            nameText.setText("                ");
            surnameText.setText("             ");
        }
    }
}
