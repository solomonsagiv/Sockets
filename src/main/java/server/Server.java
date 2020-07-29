package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main( String[] args ) throws IOException {
        Server server = new Server( 3333 );
    }

    private Socket socket = null;
    private ObjectOutputStream output = null;
    private ObjectInputStream input = null;
    private ServerSocket serverSocket = null;

    public Server( int port ) {
        try {
            serverSocket = new ServerSocket(port);
            System.out.println( "Waiting for client" );

            socket = serverSocket.accept();

            input = new ObjectInputStream( new BufferedInputStream( socket.getInputStream() ));

            System.out.println(input.readObject() );

            socket.close();
            input.close();

        } catch ( Exception e ) {
            e.printStackTrace();
        }
    }

    public Socket getMySocket( int port ) {
        try {
            ServerSocket serverSocket = new ServerSocket( port );
            return serverSocket.accept( );
        } catch ( Exception e ) {
            return null;
        }
    }

    // Recieve stream
    @SuppressWarnings( "resource" )
    public Object reciever( Socket socket ) {
        ObjectInputStream inputStream;
        try {
            inputStream = new ObjectInputStream( socket.getInputStream( ) );
            try {
                return inputStream.readObject( );
            } catch ( ClassNotFoundException e ) {
                e.printStackTrace( );
            }
        } catch ( IOException e ) {
            e.printStackTrace( );
        }
        return null;
    }

    // Send stream
    public void send( Object object, Socket socket ) {
        PrintStream printStream;
        try {
            printStream = new PrintStream( socket.getOutputStream( ) );
            printStream.println( object );
        } catch ( IOException e ) {
            e.printStackTrace( );
        }
    }

}

