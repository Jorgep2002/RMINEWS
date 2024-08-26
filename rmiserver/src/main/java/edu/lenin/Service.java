package edu.lenin;

import edu.lenin.domain.interfaces.UserInterface;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Service extends UnicastRemoteObject implements UserInterface {

  public Service() throws RemoteException {
    super();
  }

  @Override
  public String getAllUsers() throws RemoteException {
    return "";
  }

  @Override
  public String getUser(String userId) throws RemoteException {
    return "";
  }

  @Override
  public String createUser(String username, String password) throws RemoteException {
    return "Created " + username + ", " + password  ;
  }

  @Override
  public String deleteUser(String userId) throws RemoteException {
    return "";
  }

  @Override
  public String modifyUser() throws RemoteException {
    return "";
  }

  @Override
  public String login(String username, String password) throws RemoteException {
    return "";
  }
}
