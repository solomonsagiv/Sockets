package dde;

import com.pretty_tools.dde.DDEException;
import com.pretty_tools.dde.client.DDEClientConversation;
import locals.L;
import myData.MyData;

import java.util.ArrayList;

public class DDEWriter {

    private DDEConnection ddeConnection = new DDEConnection();
    private DDEClientConversation conversation;

    final String R = "R", C = "C";

    // Constructor
    public DDEWriter( String excelPath ) {
        this.conversation = ddeConnection.createNewConversation(excelPath);
    }

    // Write the data to the excel
    public void writeData( MyData myData ) throws DDEException {
        ArrayList<ArrayList<Integer>> data = myData.getData();

        int row = myData.getInstructions().getStartRow();

        for ( ArrayList<Integer> line: data ) {

            int col = myData.getInstructions().getStartCol();

            for ( Integer i: line ) {
                String cell = R + row + C + col;
                conversation.poke( cell, L.str( i ) );
                col++;
            }
            row++;
        }
    }

    // Close
    public void close() {
        try {
            conversation.disconnect();
        } catch (DDEException e) {
            e.printStackTrace();
        }
    }
}