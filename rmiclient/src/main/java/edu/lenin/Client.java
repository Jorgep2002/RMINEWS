package edu.lenin;

import edu.lenin.domain.entities.UserEntity;
import edu.lenin.UserServiceClient;

import java.util.List;

public class Client {
  private UserServiceClient serviceClient;

  public Client(String ip, String port, String serviceName) {
    this.serviceClient = new UserServiceClient(ip, port, serviceName);
  }

  // Método para crear un usuario
  public boolean createUser(String username, String password, String nombre, UserEntity.Rol rol) {
    UserEntity user = new UserEntity(username, password, nombre, rol);
    try {
      return serviceClient.createUser(user);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  // Método para obtener un usuario por su nombre de usuario
  public UserEntity getUser(String username) {
    try {
      return serviceClient.getUser(username);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  // Método para obtener todos los usuarios
  public List<UserEntity> getAllUsers() {
    try {
      return serviceClient.getAllUsers();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  // Método para autenticar un usuario
  public boolean login(String username, String password) {
    try {
      return serviceClient.login(username, password);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  // Método para actualizar un usuario existente
  public boolean updateUser(String username,  String nombre, UserEntity.Rol rol) {
    try {
      serviceClient.updateUser(username,  nombre, rol);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  // Método para eliminar un usuario por su nombre de usuario
  public boolean deleteUser(String username) {
    try {
      serviceClient.deleteUser(username);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
}
