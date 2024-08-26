
package edu.lenin;

import edu.lenin.domain.entities.UserEntity;

/**
 * Hello world!
 *
 */

import edu.lenin.gui.LoginFrame;

import javax.swing.*;

public class App {
    public static void main(String[] args) {
        Client client = new Client("localhost", "1802", "msg");
        SwingUtilities.invokeLater(() -> new LoginFrame(client).setVisible(true));
    }
}