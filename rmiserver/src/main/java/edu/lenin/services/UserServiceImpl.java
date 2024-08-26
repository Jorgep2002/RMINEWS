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

    protected UserServiceImpl() throws RemoteException {
        super();
        this.userDAO = new UserDAOImpl(); // Inicializa el DAO
    }

    @Override
    public void createUser(UserEntity user) throws RemoteException {
        userDAO.createUser(user);
    }

    @Override
    public UserEntity getUser(String username) throws RemoteException {
        return userDAO.getUser(username);
    }

    @Override
    public List<UserEntity> getAllUsers() throws RemoteException {
        List<UserEntity> users = new ArrayList<>();
        // Aquí debes agregar la lógica para obtener todos los usuarios desde el DAO
        // Ejemplo de cómo podrías hacerlo si agregas un método en el DAO:
        // users = userDAO.getAllUsers();
        return users;
    }

    @Override
    public void updateUser(UserEntity user) throws RemoteException {
        userDAO.updateUser(user);
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
