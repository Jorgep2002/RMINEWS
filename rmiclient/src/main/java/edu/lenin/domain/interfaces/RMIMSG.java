package edu.lenin.domain.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface RMIMSG extends Remote {
  public String sayHello(String msg) throws RemoteException;  
}
