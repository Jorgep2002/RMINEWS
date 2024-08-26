package edu.lenin;

import edu.lenin.domain.entities.UserEntity;
import edu.lenin.domain.interfaces.UserServiceInterface;

import java.rmi.Naming;

public class Client {

  private String url;

  public Client(String ip, String port, String serviceName) {
    this.url = "rmi://" + ip + ":" + port + "/" + serviceName;
  }

  public void createUser(String username, String password, String nombre, UserEntity.Rol rol) {
    try {
      UserServiceInterface service = (UserServiceInterface) Naming.lookup(this.url);

      // Crear una instancia de UserEntity
      UserEntity user = new UserEntity(username, password, nombre, rol);

      // Llamar al método createUser en el servicio
      service.createUser(user);
      System.out.println("Usuario creado con éxito.");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
  
}
