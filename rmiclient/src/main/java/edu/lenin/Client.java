package edu.lenin;

import edu.lenin.domain.entities.UserEntity;

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

  // Método para obtener un usuario por su id
  public UserEntity getUser(int id) {
    try {
      return serviceClient.getUser(id);
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
  public UserEntity login(String username, String password) {
    try {
      return serviceClient.login(username, password);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  // Método para actualizar un usuario existente
  public boolean updateUser(int id, String newUsername, String nombre, UserEntity.Rol rol) {
    try {
      serviceClient.updateUser(id, newUsername, nombre, rol);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }

  // Método para eliminar un usuario por su id
  public boolean deleteUser(int id) {
    try {
      serviceClient.deleteUser(id);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
}
