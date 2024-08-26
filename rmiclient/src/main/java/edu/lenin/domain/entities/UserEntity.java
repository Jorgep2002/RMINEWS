package edu.lenin.domain.entities;

import java.io.Serializable;
import java.util.Map;

public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L; // Agrega un serialVersionUID para compatibilidad

    private String username;
    private String password;
    private String nombre;
    private Rol rol;

    // Enumeración para los roles
    public enum Rol {
        USUARIO,
        ADMINISTRADOR
    }

    // Constructor
    public UserEntity(String username, String password, String nombre, Rol rol) {
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.rol = rol;
    }

    // Método estático fromObject
    public static UserEntity fromObject(Map<String, Object> object) {
        String username = (String) object.get("username");
        String password = (String) object.get("password");
        String nombre = (String) object.get("nombre");
        Rol rol = Rol.valueOf((String) object.get("rol").toString().toUpperCase());

        return new UserEntity(username, password, nombre, rol);
    }

    // Getters y Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "username='" + username + '\'' +
                ", nombre='" + nombre + '\'' +
                ", rol=" + rol +
                '}';
    }
}
