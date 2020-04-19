import gui.EnterPage;
import gui.PageManager;

import javax.swing.*;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        JFrame.setDefaultLookAndFeelDecorated(true);
        PageManager enterPageManager = new PageManager(EnterPage.getInstance());
        enterPageManager.showPage();
        System.out.println(UUID.randomUUID().toString());
    }
}
