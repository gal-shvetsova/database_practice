import connection.JdbcConnection;
import connection.SshConnect;
import dao.Service;
import gui.EnterPage;
import gui.PageManager;

import javax.swing.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {
    public static void main(String[] args) {
        Properties dbProperties;
        Properties sshProperties;
        try (InputStream inputStream =
                     Main.class.getClassLoader().getResourceAsStream("db.properties");
             InputStream inputStreamSsh = Main.class.getClassLoader().getResourceAsStream("ssh.properties")) {
            dbProperties = new Properties();
            dbProperties.load(inputStream);
            sshProperties = new Properties();
            sshProperties.load(inputStreamSsh);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (SshConnect ignored = new SshConnect(sshProperties);
             JdbcConnection jdbcConnection = new JdbcConnection(dbProperties)) {
            Service.setConnection(jdbcConnection);
            JFrame.setDefaultLookAndFeelDecorated(true);
            PageManager enterPageManager = new PageManager(EnterPage.getInstance());
            enterPageManager.showPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
