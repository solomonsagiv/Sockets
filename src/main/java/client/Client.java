package client;

import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main( String[] args ) throws UnknownHostException, IOException {
        Client client = new Client( "10.0.0.5", 3333 );

    }

    private Socket socket = null;
    private ObjectOutputStream output = null;
    private ObjectInputStream input = null;
    private ServerSocket serverSocket = null;

    public Client( String address, int port ) {
        try {
            socket = new Socket( address, port );

            input = new ObjectInputStream( System.in );

            output = new ObjectOutputStream(socket.getOutputStream());

            String line = "";

            while ( !line.equals( "exit" ) ) {
                line = ( String ) input.readObject();
                output.writeObject( line );
            }

            socket.close();
            output.close();
        } catch ( Exception e ) {
            e.printStackTrace( );
        }
    }

    public InputStream receive( Socket socket ) throws IOException {
        System.out.println( socket );
        return socket.getInputStream( );
    }

    // Send stream
    public void send( Object object, Socket socket ) {
        ObjectOutputStream outputStream;
        try {
            outputStream = new ObjectOutputStream( socket.getOutputStream( ) );
            outputStream.writeObject( object );
        } catch ( IOException e ) {
            e.printStackTrace( );
        }
    }
}
