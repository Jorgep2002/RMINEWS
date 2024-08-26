package edu.lenin.infrastructure;

import edu.lenin.domain.entities.UserEntity;
import edu.lenin.data.mysql.mysqlDatabase; // Importa la clase mysqlDatabase
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAOImpl  {


    public void createUser(UserEntity user) {
        String sql = "INSERT INTO users (username, password, nombre, rol) VALUES (?, ?, ?, ?)";
        try (Connection conn = mysqlDatabase.getConnection(); // Usa la conexión de mysqlDatabase
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getNombre());
            stmt.setString(4, user.getRol().name());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public UserEntity getUser(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
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


    public void updateUser(UserEntity user) {
        String sql = "UPDATE users SET password = ?, nombre = ?, rol = ? WHERE username = ?";
        try (Connection conn = mysqlDatabase.getConnection(); // Usa la conexión de mysqlDatabase
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getPassword());
            stmt.setString(2, user.getNombre());
            stmt.setString(3, user.getRol().name());
            stmt.setString(4, user.getUsername());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void deleteUser(String username) {
        String sql = "DELETE FROM users WHERE username = ?";
        try (Connection conn = mysqlDatabase.getConnection(); // Usa la conexión de mysqlDatabase
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, username);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
