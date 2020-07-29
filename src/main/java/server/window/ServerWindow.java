package server.window;

import gui.Guis;
import server.Server;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ServerWindow extends Guis.MyFrame {

    public static void main( String[] args ) {
        ServerWindow serverWindow = new ServerWindow( "Server" );
    }

    // Server
    Server server;

    // Guis
    Guis.MyButton listenBtn;

    JScrollPane scrollPane;
    TextArea textArea;

    // Constructor
    public ServerWindow( String title ) throws HeadlessException {
        super( title );
    }

    @Override
    public void initOnClose() {

    }

    @Override
    public void initListeners() {
        listenBtn.addActionListener( new AbstractAction( ) {
            @Override
            public void actionPerformed( ActionEvent e ) {

                listenBtn.setEnabled( false );
            }
        } );
    }

    @Override
    public void initialize() {

        int width = 400;

        // This
        setSize( width, 400 );

        // Text area
        textArea = new TextArea( );
        scrollPane = new JScrollPane( textArea );
        scrollPane.setBounds( 10, 10, 200, 300 );
        add( scrollPane );

        // Listen button
        listenBtn = new Guis.MyButton( "Listen" );
        listenBtn.setXY( 250, scrollPane.getY() );
        add( listenBtn );
    }
}
