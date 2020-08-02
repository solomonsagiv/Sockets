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
    public DDEReader(String excelPath, MyData myData) {
        this.myData = myData;
        this.instructions = myData.getInstructions();
        conversation = new DDEConnection().createNewConversation(excelPath);
    }

    // Read data
    public MyData read() throws DDEException {

        ArrayList<ArrayList<Integer>> data = new ArrayList<>();
        String cell;
        ArrayList<Integer> line;

        // Rows
        for (int row = instructions.getStartRow(); row <= instructions.getEndRow(); row++) {

            line = new ArrayList<>();

            // Cols
            for (int col = instructions.getStartCol(); col <= instructions.getEndCol(); col++) {
                cell = R + row + C + col;
                int cellData = L.INT(conversation.request(cell));
                line.add(cellData);
            }

            // Append line
            data.add(line);
        }

        myData.setData( data );
        return myData;
    }

    // ---------- Getters and Setters ---------- //
    public MyInstructions getInstructions() {
        return instructions;
    }
    public void setInstructions(MyInstructions instructions) {
        this.instructions = instructions;
    }
}
