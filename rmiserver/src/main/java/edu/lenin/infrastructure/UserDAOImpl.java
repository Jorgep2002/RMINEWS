package edu.lenin.infrastructure;

import edu.lenin.domain.entities.UserEntity;
import edu.lenin.data.mysql.mysqlDatabase; // Importa la clase mysqlDatabase
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl {

    // Método para crear un usuario
    public void createUser(UserEntity user) {
        String sql = "INSERT INTO usuarios (username, password, nombre, rol) VALUES (?, ?, ?, ?)";
        try (Connection conn = mysqlDatabase.getConnection(); // Usa la conexión de mysqlDatabase
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getNombre());
            stmt.setString(4, user.getRol().name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al crear el usuario");
            e.printStackTrace();
        }
    }

    // Método para obtener un usuario por su id
    public UserEntity getUser(int id) {
        String sql = "SELECT * FROM usuarios WHERE id = ?";
        try (Connection conn = mysqlDatabase.getConnection(); // Usa la conexión de mysqlDatabase
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String nombre = rs.getString("nombre");
                UserEntity.Rol rol = UserEntity.Rol.valueOf(rs.getString("rol"));
                return new UserEntity(id, username, password, nombre, rol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para obtener un usuario por su nombre de usuario
    public UserEntity getUserByUsername(String username) {
        String sql = "SELECT * FROM usuarios WHERE username = ?";
        try (Connection conn = mysqlDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String password = rs.getString("password");
                String nombre = rs.getString("nombre");
                UserEntity.Rol rol = UserEntity.Rol.valueOf(rs.getString("rol"));
                return new UserEntity(id, username, password, nombre, rol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para actualizar un usuario por id
    public void updateUser(int id, String newUsername, String nombre, UserEntity.Rol rol) {
        String sql = "UPDATE usuarios SET username = ?, nombre = ?, rol = ? WHERE id = ?";
        try (Connection conn = mysqlDatabase.getConnection(); // Usa la conexión de mysqlDatabase
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, newUsername); // Establece el nuevo username
            stmt.setString(2, nombre); // Establece el nuevo nombre
            stmt.setString(3, rol.name()); // Establece el nuevo rol
            stmt.setInt(4, id); // Establece el id del usuario a actualizar

            stmt.executeUpdate(); // Ejecuta la actualización en la base de datos

        } catch (SQLException e) {
            System.out.println("Error al actualizar el usuario");
            e.printStackTrace();
        }
    }

    // Método para eliminar un usuario por id
    public void deleteUser(int id) {
        String sql = "DELETE FROM usuarios WHERE id = ?";
        try (Connection conn = mysqlDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al eliminar el usuario");
            e.printStackTrace();
        }
    }

    // Método para obtener todos los usuarios
    public List<UserEntity> getAllUsers() {
        List<UserEntity> users = new ArrayList<>();
        String sql = "SELECT * FROM usuarios";
        try (Connection conn = mysqlDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String nombre = rs.getString("nombre");
                UserEntity.Rol rol = UserEntity.Rol.valueOf(rs.getString("rol"));
                UserEntity user = new UserEntity(id, username, password, nombre, rol);
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener todos los usuarios");
            e.printStackTrace();
        }
        return users;
    }

    // Método para verificar si un usuario existe por id
    public boolean userExists(int id) {
        String sql = "SELECT 1 FROM usuarios WHERE id = ?";
        try (Connection conn = mysqlDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error al verificar si el usuario existe");
            e.printStackTrace();
        }
        return false;
    }

    // Método para verificar las credenciales de un usuario
    // Modificado para devolver UserEntity
    public UserEntity authenticate(String username, String password) {
        String sql = "SELECT * FROM usuarios WHERE username = ?";
        try (Connection conn = mysqlDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String storedPassword = rs.getString("password");
                if (storedPassword.equals(password)) {
                    // Construir el objeto UserEntity con los datos recuperados
                    Integer id = rs.getInt("id"); // Asignar el id
                    String nombreUsuario = rs.getString("username"); // Asignar el username
                    String nombre = rs.getString("nombre"); // Asignar el nombre
                    // Asegúrate de que el valor de rol en la base de datos coincida con el enum
                    UserEntity.Rol rol = UserEntity.Rol.valueOf(rs.getString("rol"));
                    UserEntity user = new UserEntity(id, nombreUsuario, password, nombre, rol);

                    System.out.println(user);
                    return user; // Devolver el usuario autenticado
                }
            }
        } catch (SQLException e) {
            System.out.println("Error al autenticar el usuario");
            e.printStackTrace();
        }
        return null; // Retornar null si la autenticación falla
    }

}
