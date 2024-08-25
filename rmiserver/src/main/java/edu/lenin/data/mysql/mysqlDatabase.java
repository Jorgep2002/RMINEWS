package edu.lenin.data.mysql;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class mysqlDatabase {

    // Datos de conexión a la base de datos
    private static final String DB_URL = "jdbc:mysql://localhost:3306/users";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "";  // Sin contraseña

    // Variable para manejar la conexión
    private static Connection connection = null;

    // Método para obtener la conexión
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
                System.out.println("Database connected successfully.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Failed to connect to the database.");
            }
        }
        return connection;
    }

    // Método para cerrar la conexión
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Database connection closed.");
            } catch (SQLException e) {
                e.printStackTrace();
                System.err.println("Failed to close the database connection.");
            }
        }
    }
}
