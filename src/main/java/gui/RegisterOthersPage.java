package gui;

import dao.Service;
import model.*;

import javax.swing.*;
import java.awt.*;
import java.util.UUID;

public class RegisterOthersPage extends AbstractRegisterPage {
    private static RegisterOthersPage instance;

    protected final static int SIZE_WIDTH = 250;
    protected final static int SIZE_HEIGHT = 300;
    protected final static int LOCATION_X = (screenSize.width - SIZE_WIDTH) / 2;
    protected final static int LOCATION_Y = (screenSize.height - SIZE_HEIGHT) / 2;

    public static RegisterOthersPage getInstance(){
        if (instance == null){
            instance = new RegisterOthersPage();
        }
        return instance;
    }

    private RegisterOthersPage() {
        super("Register trainer/organizer");

        JComboBox<Role> roleComboBox = new JComboBox<>();
        JLabel roleLabel = new JLabel("Role");

        roleComboBox.addItem(Role.TRAINER);
        roleComboBox.addItem(Role.ORGANIZER);
        roleComboBox.setSelectedIndex(0);

        Container container = getContentPane();
        container.setLayout(new GridLayout(0,1));
        setBounds(LOCATION_X, LOCATION_Y, SIZE_WIDTH, SIZE_HEIGHT);
        container.add(roleLabel);
        container.add(roleComboBox);

        okButton.addActionListener(e ->{
            Person person = new Person(UUID.randomUUID(), ((Role) roleComboBox.getSelectedItem()),
                    login.getText(),
                    password.getText(),
                    surnameText.getText(),
                    nameText.getText());
            Service.registerPerson(person);
            if (PageManager.signIn(person.getLogin(), person.getPassword())){
                PageManager.hideUpperPage();
                (new PageManager(MainPage.getInstance())).showPage();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Signing in error", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        cancelButton.addActionListener(e -> {
            PageManager.hideUpperPage();
            (new PageManager(RegisterPage.getInstance())).showPage();
        });

        container.add(okButton);
        container.add(cancelButton);
    }
}
