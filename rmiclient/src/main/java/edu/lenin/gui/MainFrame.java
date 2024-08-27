package edu.lenin.gui;

import edu.lenin.Client;
import edu.lenin.domain.entities.UserEntity;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class MainFrame extends JFrame {
    private JLabel welcomeLabel;
    private JButton manageUsersButton;
    private JButton newsButton;
    private Client client;
    private Integer username;
    private UserEntity.Rol userRole;

    public MainFrame(Client client, Integer username) {
        this.username = username;
        this.client = client;

        System.out.println(username);
        setTitle("Main Frame");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        welcomeLabel = new JLabel("Loading...", SwingConstants.CENTER);
        panel.add(welcomeLabel, BorderLayout.NORTH);

        add(panel);

        loadUserData();
    }

    private void loadUserData() {
        SwingUtilities.invokeLater(() -> {
            try {
                UserEntity user = client.getUser(username);
                System.out.println(user);
                if (user != null) {
                    userRole = user.getRol();
                    welcomeLabel.setText("Welcome, " + username);
                    updateUIForRole();
                } else {
                    JOptionPane.showMessageDialog(this, "User not found!");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Error loading user data.");
            }
        });
    }

    private void updateUIForRole() {
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new BorderLayout());

        if (userRole == UserEntity.Rol.USUARIO) {
            JLabel newsLabel = new JLabel("Noticias", SwingConstants.CENTER);
            contentPanel.add(newsLabel, BorderLayout.CENTER);
        } else if (userRole == UserEntity.Rol.ADMINISTRADOR) {
            manageUsersButton = new JButton("Gestionar Usuarios");
            newsButton = new JButton("Noticias");

            manageUsersButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    openManageUsersWindow();
                }
            });

            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new GridLayout(2, 1));
            buttonPanel.add(manageUsersButton);
            buttonPanel.add(newsButton);

            contentPanel.add(buttonPanel, BorderLayout.CENTER);
        }

        add(contentPanel, BorderLayout.CENTER);
        revalidate();
        repaint();
    }

    // Método para abrir la ventana de gestión de usuarios
    private void openManageUsersWindow() {
        List<UserEntity> users = client.getAllUsers();
        ManageUsersWindow manageUsersWindow = new ManageUsersWindow(client, users); // Pasar el cliente
        manageUsersWindow.setVisible(true);

        // Cerrar la ventana principal
        dispose();
    }
}
