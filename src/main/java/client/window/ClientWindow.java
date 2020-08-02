package client.window;

import client.Client;
import gui.Guis;
import locals.L;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ClientWindow extends Guis.MyFrame {

    public static void main( String[] args ) {
        ClientWindow clientWindow = new ClientWindow( "Client" );
    }

    private int port;
    private String address;

    Client client;

    Guis.MyLabel addressLbl;
    Guis.MyTextField addressField;

    Guis.MyLabel portLbl;
    Guis.MyTextField portField;

    JScrollPane scrollPane;
    JTextArea textArea;

    Guis.MyTextField excelField;
    Guis.MyButton connectBtn;

    // Constructor
    public ClientWindow( String title ) throws HeadlessException {
        super( title );
    }

    @Override
    public void initOnClose() {

    }

    @Override
    public void initListeners() {
        connectBtn.addActionListener( new AbstractAction( ) {
            @Override
            public void actionPerformed( ActionEvent e ) {

                // Port
                if (!portField.getText().isEmpty()) {
                    try {
                        port = L.INT(portField.getText());
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }

                // Address
                if (!addressField.getText().isEmpty()) {
                    try {
                        address = addressField.getText();
                    } catch (Exception exception) {
                        exception.printStackTrace();
                    }
                }

                client = new Client(address, port);
            }
        } );
    }

    @Override
    public void initialize() {

        int width = 350;

        // This
        setSize( width, 400 );

        // Port
        portLbl = new Guis.MyLabel( "Port" );
        portLbl.setXY( 10, 10 );
        add( portLbl );

        portField = new Guis.MyTextField( );
        portField.setXY( portLbl.getX( ), portLbl.getY( ) + portLbl.getHeight( ) + 5 );
        portField.setText("3333");
        add( portField );

        // Address
        addressLbl = new Guis.MyLabel("Address");
        addressLbl.setXY(portLbl.getX() + portLbl.getWidth() + 15, portLbl.getY());
        add(addressLbl);

        addressField = new Guis.MyTextField();
        addressField.setXY(addressLbl.getX(), portField.getY());
        addressField.setText("127.0.0.1");
        add(addressField);

        // Text area
        textArea = new JTextArea( );
        scrollPane = new JScrollPane( textArea );
        scrollPane.setBounds( portField.getX( ), portField.getY( ) + portField.getHeight( ) + 15, width - portField.getX( ) * 2, 200 );
        add( scrollPane );

        // Connect btn
        connectBtn = new Guis.MyButton( "Connect" );
        connectBtn.setXY( 230, portField.getY( ) );
        connectBtn.setWidth( 100 );
        add( connectBtn );

        // Excel field
        excelField = new Guis.MyTextField( );
        excelField.setXY( scrollPane.getX( ), scrollPane.getY( ) + scrollPane.getHeight( ) + 15 );
        excelField.setWidth( width - scrollPane.getX( ) * 2 );
        add( excelField );
    }
}
