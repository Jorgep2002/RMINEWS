package edu.lenin.domain.interfaces;

import edu.lenin.domain.entities.UserEntity;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserServiceInterface extends Remote {

    // Crear un nuevo usuario
    void createUser(UserEntity user) throws RemoteException;

    // Obtener un usuario por su nombre de usuario
    UserEntity getUser(String username) throws RemoteException;

    // Obtener una lista de todos los usuarios
    List<UserEntity> getAllUsers() throws RemoteException;

    // Actualizar un usuario existente
    void updateUser(String username,  String nombre, UserEntity.Rol rol) throws RemoteException;

    // Eliminar un usuario por su nombre de usuario
    void deleteUser(String username) throws RemoteException;

    // Autenticar un usuario
    boolean login(String username, String password) throws RemoteException;
}