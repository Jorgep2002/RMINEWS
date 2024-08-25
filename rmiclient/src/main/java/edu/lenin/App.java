
package edu.lenin;

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
        System.out.println(client.createUser("Jorge", "12345"));
    }
}
