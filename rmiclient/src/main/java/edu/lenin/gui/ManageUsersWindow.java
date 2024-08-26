package edu.lenin.gui;

import edu.lenin.domain.entities.UserEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class ManageUsersWindow extends JFrame {

    private JTable usersTable;
    private DefaultTableModel tableModel;

    public ManageUsersWindow(List<UserEntity> users) {
        setTitle("Gestión de Usuarios");
        setSize(600, 400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Crear el modelo de la tabla
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Username");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Rol");

        // Llenar la tabla con los datos de los usuarios
        for (UserEntity user : users) {
            tableModel.addRow(new Object[]{user.getUsername(), user.getNombre(), user.getRol().name()});
        }

        // Crear la tabla
        usersTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(usersTable);

        // Agregar la tabla a la ventana
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }
}
