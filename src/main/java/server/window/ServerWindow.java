package server.window;

import gui.Guis;
import locals.L;
import server.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ServerWindow extends Guis.MyFrame {

    public static void main(String[] args) {
        ServerWindow serverWindow = new ServerWindow("Server");
    }

    int port;
    String excelFile;

    // Server
    Server server;

    // Guis
    Guis.MyButton listenBtn;

    Guis.MyLabel portLbl;
    Guis.MyTextField portField;

    JScrollPane scrollPane;
    JTextArea textArea;

    Guis.MyTextField excelField;

    // Constructor
    public ServerWindow(String title) throws HeadlessException {
        super(title);
    }

    @Override
    public void initOnClose() {
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                try {
                    server.close();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }
                super.windowClosed(e);
            }
        });
    }

    @Override
    public void initListeners() {

        listenBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {

                textArea.append("sdsdsdsdsdska;l");

                // Port
                if (!portField.getText().isEmpty()) {
                    try {
                        port = L.INT(portField.getText());
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }

                // Listen
                server = new Server(port);
                // Text area append status


                server.listen();

                // Enable false
                listenBtn.setEnabled(false);

                // DDE reader
            }
        });
    }

    @Override
    public void initialize() {

        int width = 400;
        int height = 450;

        port = 3333;
        excelFile = "excel file path";

        // This
        setSize(width, height);

        // Port
        portLbl = new Guis.MyLabel("Port");
        portLbl.setXY(10, 10);
        add(portLbl);

        portField = new Guis.MyTextField();
        portField.setText(L.str(port));
        portField.setXY(portLbl.getX(), portLbl.getY() + portLbl.getHeight() + 5);
        add(portField);

        // Text area
        textArea = new JTextArea();
        scrollPane = new JScrollPane(textArea);
        scrollPane.setBounds(portField.getX(), portField.getY() + portField.getHeight() + 15, 200, 300);
        add(scrollPane);

        // Listen button
        listenBtn = new Guis.MyButton("Listen");
        listenBtn.setXY(250, scrollPane.getY());
        add(listenBtn);

        // Excel field
        excelField = new Guis.MyTextField();
        excelField.setXY(scrollPane.getX(), scrollPane.getY() + scrollPane.getHeight() + 15);
        excelField.setWidth(370);
        excelField.setText(excelFile);
        add(excelField);
    }
}
