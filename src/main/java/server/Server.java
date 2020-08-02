package server;

import dde.DDEWriter;
import myData.MyData;
import server.window.ServerWindow;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    private String status = "Server is close";

    private int port;
    private Socket socket = null;
    private ObjectOutputStream output = null;
    private ObjectInputStream input = null;
    private ServerSocket serverSocket = null;
    DDEWriter ddeWriter;

    public Server( int port, String excelFile ) {
        this.port = port;
        ddeWriter = new DDEWriter( excelFile );
    }

    public void listen() {
        new Thread( () -> {
            try {
                serverSocket = new ServerSocket( port );
                socket = serverSocket.accept( );
                status = "Waiting for client on port " + port;

                while ( true ) {
                    recieve( );
                }
            } catch ( Exception e ) {
                e.printStackTrace( );
            }
        } ).start( );
    }

    public Object recieve() {
        try {
            Object obj = new ObjectInputStream( new BufferedInputStream( socket.getInputStream( ) ) ).readObject( );
            MyData mydata = ( MyData ) obj;

            ServerWindow.textArea.append( "Got data " + mydata.getData().size() );

            ddeWriter.writeData( mydata );
            return obj;
        } catch ( Exception e ) {
            e.printStackTrace( );
        }
        return null;
    }

    // Send stream
    public void send( Object object, Socket socket ) {
        try {
            output = new ObjectOutputStream( socket.getOutputStream( ) );
            output.writeObject( object );
        } catch ( IOException e ) {
            e.printStackTrace( );
        }
    }

    public void close() throws IOException {
        try {
            socket.close( );
        } catch ( Exception e) {
            e.printStackTrace();
        }

        try {
            input.close( );
        } catch ( Exception e ) {
            e.printStackTrace();
        }

    }

    public String getStatus() {
        return status;
    }
}

