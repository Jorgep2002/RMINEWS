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
                return column == 1 || column == 2 || column == 3 || column == 4;
            }
        };

        tableModel.addColumn("Username");
        tableModel.addColumn("Nombre");
        tableModel.addColumn("Rol");
        tableModel.addColumn("Acción");
        tableModel.addColumn("Actualizar");

        // Llenar la tabla con los datos de los usuarios
        for (UserEntity user : users) {
            tableModel.addRow(new Object[]{
                    user.getUsername(),
                    user.getNombre(),
                    user.getRol().name(),
                    "Eliminar", // Texto para el botón de eliminar
                    "Actualizar" // Texto para el botón de actualizar
            });
        }

        // Crear la tabla
        usersTable = new JTable(tableModel);

        // Renderizador para los botones
        usersTable.getColumn("Acción").setCellRenderer(new ButtonRenderer());
        usersTable.getColumn("Actualizar").setCellRenderer(new ButtonRenderer());

        // Editor para los botones
        usersTable.getColumn("Acción").setCellEditor(new ButtonEditor(new JCheckBox(), "Eliminar"));
        usersTable.getColumn("Actualizar").setCellEditor(new ButtonEditor(new JCheckBox(), "Actualizar"));

        JScrollPane scrollPane = new JScrollPane(usersTable);
        getContentPane().add(scrollPane, BorderLayout.CENTER);
    }

    // Renderizador de botones para la tabla
    private class ButtonRenderer extends JButton implements javax.swing.table.TableCellRenderer {
        public ButtonRenderer() {
            setOpaque(true);
        }

        @Override
        public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
            setText((value == null) ? "" : value.toString());
            return this;
        }
    }

    // Editor de botones para la tabla
    private class ButtonEditor extends DefaultCellEditor {
        private String label;
        private boolean isPushed;

        public ButtonEditor(JCheckBox checkBox, String actionLabel) {
            super(checkBox);
            this.label = actionLabel;
        }

        @Override
        public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
            JButton button = new JButton();
            button.setText(label);
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if ("Eliminar".equals(label)) {
                        handleRemoveUser(row);
                    } else if ("Actualizar".equals(label)) {
                        handleUpdateUser(row);
                    }
                    fireEditingStopped();
                }
            });
            return button;
        }

        @Override
        public Object getCellEditorValue() {
            return label;
        }

        private void handleRemoveUser(int row) {
            String username = (String) tableModel.getValueAt(row, 0);
            int response = JOptionPane.showConfirmDialog(null, "¿Seguro que deseas eliminar el usuario " + username + "?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
            if (response == JOptionPane.YES_OPTION) {
                try {
                    boolean success = client.deleteUser(username);
                    if (success) {
                        tableModel.removeRow(row); // Remover la fila de la tabla si la eliminación es exitosa
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

        private void handleUpdateUser(int row) {
            String username = (String) tableModel.getValueAt(row, 0);
            String nombre = (String) tableModel.getValueAt(row, 1);
            String rolString = (String) tableModel.getValueAt(row, 2);

            UserEntity.Rol rol;
            try {
                rol = UserEntity.Rol.valueOf(rolString);
            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, "Rol no válido. Use ADMINISTRADOR o USUARIO.");
                return;
            }

            // Actualizar el usuario en la base de datos utilizando el cliente
            try {
                boolean success = client.updateUser(username, nombre, rol);
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
