package client;

import com.pretty_tools.dde.DDEException;
import dde.DDEReader;
import myData.MyData;
import myData.MyInstructions;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.SocketException;

public class Client {

    private String address = null;
    private int port;
    private String path;
    private Socket socket = null;
    private ObjectOutputStream output = null;
    private ObjectInputStream input = null;

    public Client( String address, int port, String path ) {
        this.address = address;
        this.port = port;
        this.path = path;
    }

    public void connect() {
        try {
            socket = new Socket( address, port );
            start( );
        } catch ( Exception e ) {
            e.printStackTrace( );
        }
    }

    public void start() {
        new Thread( () -> {
            MyInstructions instructions = new MyInstructions( 1, 50, 1, 5 );
            MyData myData = new MyData( instructions );

            DDEReader ddeReader = new DDEReader( path, myData );

            while ( true ) {
                try {
                    MyData data = ddeReader.read( );
                    send( data, socket );

                    Thread.sleep( 25000 );
                } catch ( InterruptedException | DDEException e ) {
                    e.printStackTrace( );
                }
            }
        } ).start( );
    }

    public Object recieve() {
        try {
            input = new ObjectInputStream( new BufferedInputStream( socket.getInputStream( ) ) );
            return input.readObject( );
        } catch ( Exception e ) {
            e.printStackTrace( );
        }
        return null;
    }

    // Send stream
    public void send( Object object, Socket socket ) {
        ObjectOutputStream outputStream;
        try {
            outputStream = new ObjectOutputStream( socket.getOutputStream( ) );
            outputStream.writeObject( object );
        } catch ( SocketException socketException ) {
            System.exit( 0 );
        } catch ( IOException e ) {
            e.printStackTrace( );
        }
    }

    public void close() {
        try {
            socket.close( );
        } catch ( Exception e ) {
            e.printStackTrace( );
        }

        try {
            output.close( );
        } catch ( Exception e ) {
            e.printStackTrace( );
        }
    }
}