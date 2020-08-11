package dde;

import com.pretty_tools.dde.DDEException;
import com.pretty_tools.dde.client.DDEClientConversation;
import locals.L;
import myData.MyData;
import myData.MyInstructions;

import java.util.ArrayList;

public class DDEReader {

    // Variables
    DDEClientConversation conversation;

    final String R = "R", C = "C";
    MyData myData;
    MyInstructions instructions;

    // Constructor
    public DDEReader( String excelPath, MyData myData ) {
        this.myData = myData;
        this.instructions = myData.getInstructions( );
        conversation = new DDEConnection( ).createNewConversation( excelPath );
    }

    public static void main( String[] args ) throws DDEException {
        MyInstructions instructions = new MyInstructions( 1, 50, 1, 5 );

        String excelPath = "C:/Users/user/Desktop/DDE/[DDE.xlsm]Yogi";

        MyData myData = new MyData( instructions );

        DDEReader ddeReader = new DDEReader( excelPath, myData );

        for ( ArrayList< Integer > line : ddeReader.read( ).getData( ) ) {
            System.out.println( line );
        }
    }

    // Read data
    public MyData read() throws DDEException {

        ArrayList< ArrayList< Integer > > data = new ArrayList<>( );
        String cell;
        ArrayList< Integer > line;

        // Rows
        for ( int row = instructions.getStartRow( ); row <= instructions.getEndRow( ); row++ ) {

            line = new ArrayList<>( );

            // Cols
            for ( int col = instructions.getStartCol( ); col <= instructions.getEndCol( ); col++ ) {
                cell = R + row + C + col;
                System.out.println( cell );
                int cellData = 0;
                try {
                    cellData = L.INT( conversation.request( cell ).replaceAll( "\\s+", "" ) );
                } catch ( NumberFormatException e ) {
                }
                line.add( cellData );
            }

            // Append line
            data.add( line );
        }

        myData.setData( data );
        return myData;
    }

    // ---------- Getters and Setters ---------- //
    public MyInstructions getInstructions() {
        return instructions;
    }

    public void setInstructions( MyInstructions instructions ) {
        this.instructions = instructions;
    }
}

// C:/Users/user/Desktop/DDE/[DDE.xlsm]yogi