package dao;

import main.java.connection.SshConnect;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Locale;
import java.util.Properties;
import java.util.TimeZone;

public class JdbcConnection {

    private static final String USERNAME = "shvetsova";
    private static final String PASSWORD = "7jtwnvC";
    private static final TimeZone TIME_ZONE = TimeZone.getTimeZone("GMT+7");
    private static final String url = "jdbc:oracle:thin:@localhost:5421:XE";
    private static final String driverName = "oracle.jdbc.driver.OracleDriver";
    private static final boolean useSSH = true;
    private static final SshConnect sshConnect = new SshConnect();

    private static Connection getConnection(boolean a)  {
        if (useSSH){  //todo: close it
            sshConnect.connect();
        }
        Properties props = new Properties();
        props.setProperty("user", USERNAME);
        props.setProperty("password", PASSWORD);
        TimeZone.setDefault(TIME_ZONE);
        Locale.setDefault(Locale.ENGLISH);
        try {
            DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
            Class.forName(driverName).newInstance();
            return DriverManager.getConnection(url, props);
        } catch (Exception e){
            if (useSSH){
                sshConnect.close();
            }
            throw new RuntimeException(e);
        }
    }

    private static final Connection connection = getConnection(true);

    public static Connection getConnection() {
        return connection;
    }

    public static void close(){
        sshConnect.close();
    }
}
