package edu.lenin.domain.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;


public interface UserInterface extends Remote{

    //admin methods
    public String getAllUsers() throws RemoteException;
    public String getUser(String userId) throws RemoteException;
    public String createUser(String username, String password) throws RemoteException;
    public String deleteUser(String userId) throws RemoteException;
    public String modifyUser() throws RemoteException;

    // user
    public String login(String username, String password) throws RemoteException;

}
