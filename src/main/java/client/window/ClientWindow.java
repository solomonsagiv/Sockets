package client.window;

import gui.Guis;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ClientWindow extends Guis.MyFrame {

    public static void main( String[] args ) {
        ClientWindow clientWindow = new ClientWindow( "Client" );
    }

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
        add( portField );

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
