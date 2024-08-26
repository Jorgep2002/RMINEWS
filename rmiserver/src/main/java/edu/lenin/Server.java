package edu.lenin;

import edu.lenin.domain.interfaces.UserServiceInterface;
import edu.lenin.services.UserServiceImpl;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;

public class Server {
  private String ip;
  private String port;
  private String serviceName;
  private String uri;

  public Server(String ip, String port, String serviceName) {
    this.ip = ip;
    this.port = port;
    this.serviceName = serviceName;
    // "//92.168.0.1:1802/service"
    this.uri = "//" + this.ip + ":" + this.port + "/" + this.serviceName;
  }

  public boolean deploy() {
    try {
      System.setProperty("java.rmi.server.hostname", ip);
      UserServiceInterface service = (UserServiceInterface) new UserServiceImpl();
      LocateRegistry.createRegistry(Integer.parseInt(port));
      Naming.rebind(uri, service);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
}
