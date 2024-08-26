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

    // Método para obtener un usuario por su nombre de usuario
    public UserEntity getUser(String username) {
        String sql = "SELECT * FROM usuarios WHERE username = ?";
        try (Connection conn = mysqlDatabase.getConnection(); // Usa la conexión de mysqlDatabase
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String password = rs.getString("password");
                String nombre = rs.getString("nombre");
                UserEntity.Rol rol = UserEntity.Rol.valueOf(rs.getString("rol"));
                return new UserEntity(username, password, nombre, rol);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Método para actualizar un usuario
    public void updateUser(UserEntity user) {
        String sql = "UPDATE usuarios SET password = ?, nombre = ?, rol = ? WHERE username = ?";
        try (Connection conn = mysqlDatabase.getConnection(); // Usa la conexión de mysqlDatabase
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getNombre());
            stmt.setString(3, user.getRol().name());
            stmt.setString(4, user.getUsername());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error al actualizar el usuario");
            e.printStackTrace();
        }
    }

    // Método para eliminar un usuario
    public void deleteUser(String username) {
        String sql = "DELETE FROM usuarios WHERE username = ?";
        try (Connection conn = mysqlDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
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
                String username = rs.getString("username");
                String password = rs.getString("password");
                String nombre = rs.getString("nombre");
                UserEntity.Rol rol = UserEntity.Rol.valueOf(rs.getString("rol"));
                UserEntity user = new UserEntity(username, password, nombre, rol);
                users.add(user);
            }
        } catch (SQLException e) {
            System.out.println("Error al obtener todos los usuarios");
            e.printStackTrace();
        }
        return users;
    }

    // Método para verificar si un usuario existe
    public boolean userExists(String username) {
        String sql = "SELECT 1 FROM usuarios WHERE username = ?";
        try (Connection conn = mysqlDatabase.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error al verificar si el usuario existe");
            e.printStackTrace();
        }
        return false;
    }
}
