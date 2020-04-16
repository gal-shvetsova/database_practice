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
}
