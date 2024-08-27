package edu.lenin;

import edu.lenin.domain.entities.UserEntity;
import edu.lenin.domain.interfaces.UserServiceInterface;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.List;

public class UserServiceClient {
    private UserServiceInterface service;

    public UserServiceClient(String ip, String port, String serviceName) {
        try {
            String url = "rmi://" + ip + ":" + port + "/" + serviceName;
            this.service = (UserServiceInterface) Naming.lookup(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para crear un usuario
    public boolean createUser(UserEntity user) throws RemoteException {
        service.createUser(user);
        return true;
    }

    // Método para obtener un usuario por su id
    public UserEntity getUser(int id) throws RemoteException {
        return service.getUser(id);
    }

    // Método para obtener todos los usuarios
    public List<UserEntity> getAllUsers() throws RemoteException {
        return service.getAllUsers();
    }

    // Método para actualizar un usuario existente
    public void updateUser(int id, String newUsername, String nombre, UserEntity.Rol rol) throws RemoteException {
        service.updateUser(id, newUsername, nombre, rol);
    }

    // Método para eliminar un usuario por su id
    public void deleteUser(int id) throws RemoteException {
        service.deleteUser(id);
    }

    // Método para autenticar un usuario
    public UserEntity login(String username, String password) throws RemoteException {
        return service.login(username, password);
    }
}
