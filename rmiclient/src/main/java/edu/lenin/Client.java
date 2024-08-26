package edu.lenin;

import edu.lenin.domain.interfaces.UserServiceInterface;

import java.rmi.Naming;

public class Client {

  private String url;

  public Client(String ip, String port, String serviceName) {
    this.url = "rmi://" + ip + ":" + port + "/" + serviceName;
  }

  public Object createUser(String username, String password) {
    try {
      UserServiceInterface service = (UserServiceInterface) Naming.lookup(this.url);
      return service.login(username, password);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  
}
