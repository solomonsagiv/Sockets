package server;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

    public static void main(String[] args) throws IOException {
        Server server = new Server(3333);
        server.listen();
    }

    private String status = "Server is close";

    private int port;
    private Socket socket = null;
    private ObjectOutputStream output = null;
    private ObjectInputStream input = null;
    private ServerSocket serverSocket = null;

    public Server(int port) {
        this.port = port;
    }

    public void listen() {
        new Thread(() -> {
            try {
                serverSocket = new ServerSocket(port);
                socket = serverSocket.accept();
                status = "Waiting for client on port " + port;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();
    }

    public Object recieve() {
        try {
            input = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            return input.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    // Send stream
    public void send(Object object, Socket socket) {
        try {
            output = new ObjectOutputStream(socket.getOutputStream());
            output.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() throws IOException {
        socket.close();
        input.close();
        status = "Server is close";
    }

    public String getStatus() {
        return status;
    }
}

