package edu.lenin;

import edu.lenin.domain.interfaces.UserInterface;

import java.rmi.Naming;

public class Client {

  private String url;

  public Client(String ip, String port, String serviceName) {
    this.url = "rmi://" + ip + ":" + port + "/" + serviceName;
  }

  public String createUser(String username, String password) {
    try {
      UserInterface service = (UserInterface) Naming.lookup(this.url);
      return service.createUser(username, password);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }
  
}
