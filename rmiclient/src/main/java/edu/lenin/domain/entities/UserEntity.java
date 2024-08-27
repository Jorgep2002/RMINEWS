package edu.lenin.domain.entities;

import java.io.Serializable;

public class UserEntity implements Serializable {
    private static final long serialVersionUID = 1L; // Agrega un serialVersionUID para compatibilidad

    private Integer id; // Cambiado de int a Integer para permitir null
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
    // Constructor con todos los campos
    public UserEntity(Integer id, String username, String password, String nombre, Rol rol) {
        this.id = id; // Inicializar el campo id
        this.username = username;
        this.password = password;
        this.nombre = nombre;
        this.rol = rol;
    }

    // Constructor sin el id
    public UserEntity(String username, String password, String nombre, Rol rol) {
        this(null, username, password, nombre, rol); // Llama al constructor principal con id como null
    }
    // Getters y Setters para el campo id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    // Getters y Setters para los otros campos
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
        return "UserEntity{" +
                "id=" + id +  // Incluir el id en el método toString
                ", username='" + username + '\'' +
                ", nombre='" + nombre + '\'' +
                ", rol=" + rol +
                '}';
    }
}
