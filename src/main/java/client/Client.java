package client;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

    public static void main(String[] args) throws UnknownHostException, IOException {
        String azor = "127.0.0.1";
        Client client = new Client(azor, 3333);
        client.connect();

    }

    private String address = null;
    private int port;
    private Socket socket = null;
    private ObjectOutputStream output = null;
    private ObjectInputStream input = null;

    public Client(String address, int port) {
        this.address = address;
        this.port = port;
    }

    public void connect() {
        try {
            socket = new Socket(address, port);
        } catch (Exception e) {
            e.printStackTrace();
        }
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
        ObjectOutputStream outputStream;
        try {
            outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(object);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close() {
        try {
            socket.close();
            output.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
