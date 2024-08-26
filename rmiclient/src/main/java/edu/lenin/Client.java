package edu.lenin;

import edu.lenin.domain.entities.UserEntity;
import edu.lenin.UserServiceClient;

import java.util.List;

public class Client {
  private UserServiceClient serviceClient;

  public Client(String ip, String port, String serviceName) {
    this.serviceClient = new UserServiceClient(ip, port, serviceName);
  }

  public boolean createUser(String username, String password, String nombre, UserEntity.Rol rol) {
    UserEntity user = new UserEntity(username, password, nombre, rol);
    try {
      return serviceClient.createUser(user);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  public UserEntity getUser(String username) {
    try {
      return serviceClient.getUser(username);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  public List<UserEntity> getAllUsers() {
    try {
      return serviceClient.getAllUsers();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }


  public boolean login(String username, String password) {
    try {
      return serviceClient.login(username, password);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
}
