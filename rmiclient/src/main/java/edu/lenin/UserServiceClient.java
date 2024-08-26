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

    // Método para obtener un usuario por su nombre de usuario
    public UserEntity getUser(String username) throws RemoteException {
        return service.getUser(username);
    }

    // Método para obtener todos los usuarios
    public List<UserEntity> getAllUsers() throws RemoteException {
        return service.getAllUsers();
    }

    // Método para actualizar un usuario existente
    public void updateUser(UserEntity user) throws RemoteException {
        service.updateUser(user);
    }

    // Método para eliminar un usuario por su nombre de usuario
    public void deleteUser(String username) throws RemoteException {
        service.deleteUser(username);
    }

    // Método para autenticar un usuario
    public boolean login(String username, String password) throws RemoteException {
        return service.login(username, password);
    }
}
