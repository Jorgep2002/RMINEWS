package edu.lenin;

import edu.lenin.domain.entities.UserEntity;
import edu.lenin.domain.interfaces.UserServiceInterface;

import java.rmi.Naming;

public class UserServiceClient {
    private UserServiceInterface service;

    public UserServiceClient(String ip, String port, String serviceName) {
        try {
            String url = "rmi://" + ip + ":" + port + "/" + serviceName;
            this.service = (UserServiceInterface) Naming.lookup(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean createUser(UserEntity user) throws Exception {
        service.createUser(user);
        return true;
    }

    public UserEntity getUser(String username) throws Exception {
        return service.getUser(username);
    }

    public boolean login(String username, String password) throws Exception {
        return service.login(username, password);
    }
}
