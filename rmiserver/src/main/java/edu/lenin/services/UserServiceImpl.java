package edu.lenin.services;

import edu.lenin.domain.interfaces.UserServiceInterface;
import edu.lenin.infrastructure.UserDAOImpl;
import edu.lenin.domain.entities.UserEntity;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;
import java.util.ArrayList;

public class UserServiceImpl extends UnicastRemoteObject implements UserServiceInterface {

    private final UserDAOImpl userDAO;

    public UserServiceImpl() throws RemoteException {
        super();
        this.userDAO = new UserDAOImpl(); // Inicializa el DAO
    }

    @Override
    public void createUser(UserEntity user) throws RemoteException {
        System.out.println("Servicio usuarios");
        userDAO.createUser(user);
    }

    @Override
    public UserEntity getUser(int id) throws RemoteException {
        return userDAO.getUser(id);
    }

    @Override
    public List<UserEntity> getAllUsers() throws RemoteException {
        return userDAO.getAllUsers();
    }

    @Override
    public void updateUser(int id, String newUsername, String nombre, UserEntity.Rol rol) throws RemoteException {
        userDAO.updateUser(id, newUsername, nombre, rol);
    }

    @Override
    public void deleteUser(int id) throws RemoteException {
        userDAO.deleteUser(id);
    }

    @Override
    public UserEntity login(String username, String password) throws RemoteException {
        return userDAO.authenticate(username, password);
    }
}
