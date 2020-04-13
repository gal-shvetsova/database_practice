package main.java.connection;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import java.util.Properties;

public class SshConnect {
    private final static int LOCAL_PORT = 5421;
    private final static int REMOTE_PORT = 1521;
    private final static String REMOTE_HOST = "84.237.50.81";
    private final static String HOST = "84.237.52.20";
    private final static String USER = "shvetsova";
    private final static String PASSWORD = "7jtwnvC";

    private Session session = null;

    public void connect(){
        if (session == null || session.isConnected()){
            return;
        }
        try {
            Properties config = new java.util.Properties();
            config.put("StrictHostKeyChecking", "no");
            JSch jsch = new JSch();
            session = jsch.getSession(USER, HOST, 22);
            session.setPassword(PASSWORD);
            session.setConfig(config);
            session.connect();
            session.setPortForwardingL(LOCAL_PORT, REMOTE_HOST, REMOTE_PORT);
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    public void close(){
        if (session != null && session.isConnected()) {
            session.disconnect();
        }
    }
}
