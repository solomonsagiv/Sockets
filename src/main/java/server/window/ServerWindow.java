package server.window;

import file.FileMap;
import file.MyFile;
import gui.Guis;
import locals.L;
import server.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URISyntaxException;
import java.util.HashMap;

public class ServerWindow extends Guis.MyFrame {

    public static void main( String[] args ) throws URISyntaxException {
        ServerWindow serverWindow = new ServerWindow( "Server" );
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
    public static JTextArea textArea;

    Guis.MyTextField excelField;

    MyFile myFile;
    FileMap fileMap;

    HashMap< String, Object > map;

    // Constructor
    public ServerWindow( String title ) throws HeadlessException {
        super( title );
        fileMap = FileMap.getInstance( );
    }

    @Override
    public void initOnClose() {
        addWindowListener( new WindowAdapter( ) {
            @Override
            public void windowClosed( WindowEvent e ) {
                try {
                    server.close( );
                } catch ( Exception exception ) {
                    exception.printStackTrace( );
                }
                super.windowClosed( e );
            }
        } );
    }

    @Override
    public void initListeners() {
        listenBtn.addActionListener( new ActionListener( ) {
            @Override
            public void actionPerformed( ActionEvent actionEvent ) {

                // Port
                if ( !portField.getText( ).isEmpty( ) ) {
                    try {
                        port = L.INT( portField.getText( ) );
                    } catch ( Exception exception ) {
                        exception.printStackTrace( );
                    }
                }

                // Excel file
                if ( !excelField.getText( ).isEmpty( ) ) {
                    try {
                        excelFile = excelField.getText( );
                    } catch ( Exception exception ) {
                        exception.printStackTrace( );
                    }
                }

                // Listen
                server = new Server( port, excelFile );

                // Text area append status
                server.listen( );

                // Enable false
                listenBtn.setEnabled( false );
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

        int width = 400;
        int height = 480;

        try {
            myFile = new MyFile( "server-db.txt" );

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

        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );

        // This
        setSize( width, height );

        // Port
        portLbl = new Guis.MyLabel( "Port" );
        portLbl.setXY( 10, 10 );
        add( portLbl );

        portField = new Guis.MyTextField( );
        portField.setXY( portLbl.getX( ), portLbl.getY( ) + portLbl.getHeight( ) + 5 );
        add( portField );

        // Text area
        textArea = new JTextArea( );
        scrollPane = new JScrollPane( textArea );
        scrollPane.setBounds( portField.getX( ), portField.getY( ) + portField.getHeight( ) + 15, 200, 300 );
        add( scrollPane );

        // Listen button
        listenBtn = new Guis.MyButton( "Listen" );
        listenBtn.setXY( 250, scrollPane.getY( ) );
        add( listenBtn );

        // Excel field
        excelField = new Guis.MyTextField( );
        excelField.setXY( scrollPane.getX( ), scrollPane.getY( ) + scrollPane.getHeight( ) + 15 );
        excelField.setWidth( 370 );

        add( excelField );

        try {
            portField.setText( L.str( map.get( FileMap.PORT ) ) );
            excelField.setText( L.str( map.get( FileMap.EXCEL_PATH ) ) );
        } catch ( NullPointerException e ) {
        }
    }
}
