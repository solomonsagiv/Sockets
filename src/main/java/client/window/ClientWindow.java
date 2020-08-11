package client.window;

import client.Client;
import file.FileMap;
import file.MyFile;
import gui.Guis;
import locals.L;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ClientWindow extends Guis.MyFrame {

    public static void main( String[] args ) {
        ClientWindow clientWindow = new ClientWindow( "Client" );
    }

    private int port;
    private String address;
    private String path;

    Client client;

    Guis.MyLabel addressLbl;
    Guis.MyTextField addressField;

    Guis.MyLabel portLbl;
    Guis.MyTextField portField;

    JScrollPane scrollPane;
    JTextArea textArea;

    Guis.MyTextField excelField;
    Guis.MyButton connectBtn;

    MyFile myFile;
    FileMap fileMap;

    Map map;

    // Constructor
    public ClientWindow( String title ) throws HeadlessException {
        super( title );
        fileMap = FileMap.getInstance( );
    }

    @Override
    public void initOnClose() {
        addWindowListener( new WindowAdapter( ) {
            @Override
            public void windowClosed( WindowEvent e ) {
                client.close( );
                super.windowClosed( e );
            }
        } );
    }

    @Override
    public void initListeners() {
        connectBtn.addActionListener( new AbstractAction( ) {
            @Override
            public void actionPerformed( ActionEvent e ) {

                // Port
                if ( !portField.getText( ).isEmpty( ) ) {
                    try {
                        port = L.INT( portField.getText( ) );
                    } catch ( Exception exception ) {
                        exception.printStackTrace( );
                    }
                }

                // Address
                if ( !addressField.getText( ).isEmpty( ) ) {
                    try {
                        address = addressField.getText( );
                    } catch ( Exception exception ) {
                        exception.printStackTrace( );
                    }
                }

                // Excel file
                if ( !excelField.getText( ).isEmpty( ) ) {
                    try {
                        path = excelField.getText( );
                    } catch ( Exception exception ) {
                        exception.printStackTrace( );
                    }
                }

                client = new Client( address, port, path );
                client.connect( );
            }
        } );

        // Address
        addressField.addActionListener( new ActionListener( ) {
            @Override
            public void actionPerformed( ActionEvent e ) {
                String address = addressField.getText( );
                map.put( FileMap.ADDRESS, address );
                myFile.writeObject( map );
            }
        } );

        // Port
        portField.addActionListener( new ActionListener( ) {
            @Override
            public void actionPerformed( ActionEvent e ) {
                int port = L.INT( portField.getText( ) );
                map.put( FileMap.PORT, port );
                myFile.writeObject( map );
            }
        } );

        // Excel filed
        excelField.addActionListener( new ActionListener( ) {
            @Override
            public void actionPerformed( ActionEvent e ) {
                map.put( FileMap.EXCEL_PATH, excelField.getText( ) );
                myFile.writeObject( map );
            }
        } );
    }

    @Override
    public void initialize() {

        try {
            myFile = new MyFile( "client-db.txt" );

            if ( !myFile.exists( ) ) {
                myFile.createNewFile( );
            }

            try {
                map = ( HashMap< String, Object > ) myFile.readObject( );
            } catch ( Exception e ) {
                map = new HashMap<>( );
            }
        } catch ( Exception e ) {
        }

        int width = 350;

        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        // This
        setSize( width, 420 );

        // Port
        portLbl = new Guis.MyLabel( "Port" );
        portLbl.setXY( 10, 10 );
        add( portLbl );

        portField = new Guis.MyTextField( );
        portField.setXY( portLbl.getX( ), portLbl.getY( ) + portLbl.getHeight( ) + 5 );
        add( portField );

        // Address
        addressLbl = new Guis.MyLabel( "Address" );
        addressLbl.setXY( portLbl.getX( ) + portLbl.getWidth( ) + 15, portLbl.getY( ) );
        add( addressLbl );

        addressField = new Guis.MyTextField( );
        addressField.setXY( addressLbl.getX( ), portField.getY( ) );
        add( addressField );

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

        try {
            portField.setText( L.str( map.get( FileMap.PORT ) ) );
            excelField.setText( L.str( map.get( FileMap.EXCEL_PATH ) ) );
            addressField.setText( L.str( map.get( FileMap.ADDRESS ) ) );
        } catch ( NullPointerException e ) {
            e.printStackTrace();
        }
    }
}
