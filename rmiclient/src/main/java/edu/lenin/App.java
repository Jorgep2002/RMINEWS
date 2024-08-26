
package edu.lenin;

import edu.lenin.domain.entities.UserEntity;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Client" );
        Client client = new Client("localhost", "1802", "msg");
        client.createUser("Jorgep", "12312312", "Jorge Andres Pinilla Gonzalez", UserEntity.Rol.USUARIO);

    }
}
