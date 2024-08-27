package edu.lenin.domain.interfaces;

import edu.lenin.domain.entities.UserEntity;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface UserServiceInterface extends Remote {

    // Crear un nuevo usuario
    void createUser(UserEntity user) throws RemoteException;

    // Obtener un usuario por su nombre de usuario
    UserEntity getUser(int id) throws RemoteException;

    // Obtener una lista de todos los usuarios
    List<UserEntity> getAllUsers() throws RemoteException;

    // Actualizar un usuario existente por id y permitir cambiar el username
    void updateUser(int id, String newUsername, String nombre, UserEntity.Rol rol) throws RemoteException;

    // Eliminar un usuario por id
    void deleteUser(int id) throws RemoteException;

    // Autenticar un usuario y devolver el objeto UserEntity en caso de éxito
    UserEntity login(String username, String password) throws RemoteException;
}
