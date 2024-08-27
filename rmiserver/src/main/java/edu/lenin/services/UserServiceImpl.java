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
    public UserEntity getUser(String username) throws RemoteException {
        return userDAO.getUser(username);
    }

    @Override
    public List<UserEntity> getAllUsers() throws RemoteException {
        List<UserEntity> users = new ArrayList<>();

        users = userDAO.getAllUsers();
        return users;
    }

    @Override
    public void updateUser(String username,  String nombre, UserEntity.Rol rol) throws RemoteException {
        userDAO.updateUser(username, nombre, rol);
    }

    @Override
    public void deleteUser(String username) throws RemoteException {
        userDAO.deleteUser(username);
    }

    @Override
    public boolean login(String username, String password) throws RemoteException {
        UserEntity user = userDAO.getUser(username);
        return user != null && user.getPassword().equals(password);
    }
}
