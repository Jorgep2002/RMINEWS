package edu.lenin.gui;

import edu.lenin.Client;
import edu.lenin.domain.entities.UserEntity;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ManageUsersWindow extends JFrame {

    private JTable usersTable;
    private DefaultTableModel tableModel;
    private Client client; // Agregar el cliente como un atributo de clase
    private JButton updateButton;
    private JButton deleteButton;

    // Modificar el constructor para aceptar un objeto Client
    public ManageUsersWindow(Client client, List<UserEntity> users) {
        this.client = client; // Asignar el cliente al atributo de clase
        setTitle("Gestión de Usuarios");
        setSize(800, 400); // Aumenta el tamaño para acomodar los botones
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Crear el modelo de la tabla
        tableModel = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Permitir editar las columnas de "Nombre" y "Rol"
                return column == 1 || column == 2 || column == 3;
            }
        };
        tableModel.addColumn("id");
        tableModel.addColumn("Username");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Rol");

        // Llenar la tabla con los datos de los usuarios
        for (UserEntity user : users) {
            tableModel.addRow(new Object[]{
                    user.getId(),
                    user.getUsername(),
                    user.getNombre(),
                    user.getRol().name()
            });
        }

        // Crear la tabla
        usersTable = new JTable(tableModel);

        // Crear los botones
        JPanel buttonPanel = new JPanel();
        updateButton = new JButton("Actualizar");
        deleteButton = new JButton("Eliminar");

        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        // Configurar los botones
        updateButton.setEnabled(false);
        deleteButton.setEnabled(false);

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleUpdateUser();
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleRemoveUser();
            }
        });

        // Añadir un Listener a la tabla para habilitar los botones cuando se selecciona una fila
        usersTable.getSelectionModel().addListSelectionListener(e -> {
            boolean rowSelected = usersTable.getSelectedRow() != -1;
            updateButton.setEnabled(rowSelected);
            deleteButton.setEnabled(rowSelected);
        });

        JScrollPane scrollPane = new JScrollPane(usersTable);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
        getContentPane().add(buttonPanel, BorderLayout.SOUTH);
    }

    private void handleRemoveUser() {
        int selectedRow = usersTable.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            int response = JOptionPane.showConfirmDialog(null, "¿Seguro que deseas eliminar el usuario " + id + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                try {
                    boolean success = client.deleteUser(id);
                    if (success) {
                        tableModel.removeRow(selectedRow); // Remover la fila de la tabla si la eliminación es exitosa
                        JOptionPane.showMessageDialog(null, "Usuario eliminado exitosamente.");
                    } else {
                        JOptionPane.showMessageDialog(null, "Error al eliminar el usuario.");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error al conectar con el servidor.");
                }
            }
        }
    }

    private void handleUpdateUser() {
        int selectedRow = usersTable.getSelectedRow();
        if (selectedRow != -1) {
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            String username = (String) tableModel.getValueAt(selectedRow, 1);
            String nombre = (String) tableModel.getValueAt(selectedRow, 2);
            String rolString = (String) tableModel.getValueAt(selectedRow, 3);

            UserEntity.Rol rol;
            try {
                rol = UserEntity.Rol.valueOf(rolString);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Rol no válido. Use ADMINISTRADOR o USUARIO.");
                return;
            }

            // Actualizar el usuario en la base de datos utilizando el cliente
            try {
                boolean success = client.updateUser(id, username, nombre, rol);
                if (success) {
                    JOptionPane.showMessageDialog(null, "Usuario actualizado exitosamente.");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar el usuario.");
                }
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error al conectar con el servidor.");
            }
        }
    }
}
