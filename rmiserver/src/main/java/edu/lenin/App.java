package edu.lenin;

import edu.lenin.data.mysql.mysqlDatabase;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello RMI!");
        Server server = new Server(
            "localhost",
            "1802",
            "msg"
        );
        server.deploy();
        mysqlDatabase.getConnection();
    }
}
