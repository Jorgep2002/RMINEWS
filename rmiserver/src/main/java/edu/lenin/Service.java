package edu.lenin;

import edu.lenin.domain.entities.UserEntity;
import edu.lenin.domain.interfaces.UserServiceInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

public class Service extends UnicastRemoteObject implements UserServiceInterface {

  public Service() throws RemoteException {
    super();
  }

  @Override
  public void createUser(UserEntity user) throws RemoteException {

  }

  @Override
  public UserEntity getUser(String username) throws RemoteException {
    return null;
  }

  @Override
  public List<UserEntity> getAllUsers() throws RemoteException {
    return List.of();
  }

  @Override
  public void updateUser(UserEntity user) throws RemoteException {

  }

  @Override
  public void deleteUser(String username) throws RemoteException {

  }

  @Override
  public boolean login(String username, String password) throws RemoteException {
    return true;
  }
}
