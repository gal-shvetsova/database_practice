package gui;

import javax.swing.*;
import java.sql.SQLException;

public class Utils {
    public static void createErrorDialog(JFrame frame, SQLException ex) {
        JOptionPane.showMessageDialog(frame,
                "Error occurred. Error code: " + ex.getErrorCode(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
    }

    public static void createErrorDialog(JFrame frame, String message, String tittle) {
        JOptionPane.showMessageDialog(frame,
                message,
                tittle,
                JOptionPane.ERROR_MESSAGE);
    }
}
