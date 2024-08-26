package edu.lenin.gui;

import edu.lenin.Client;
import edu.lenin.domain.entities.UserEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
    private Client client;

    public MainFrame(Client client) {
        this.client = client;
        setTitle("Main Frame");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        panel.add(new JScrollPane(textArea), BorderLayout.CENTER);

        JButton fetchUserButton = new JButton("Fetch User");
        panel.add(fetchUserButton, BorderLayout.SOUTH);

        fetchUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleFetchUser(textArea);
            }
        });

        add(panel);
    }

    private void handleFetchUser(JTextArea textArea) {
        String username = JOptionPane.showInputDialog(this, "Enter username:");

        if (username != null && !username.trim().isEmpty()) {
            UserEntity user = client.getUser(username);
            if (user != null) {
                textArea.setText(user.toString());
            } else {
                textArea.setText("User not found!");
            }
        }
    }
}
