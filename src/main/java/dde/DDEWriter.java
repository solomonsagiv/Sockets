package dde;

import com.pretty_tools.dde.DDEException;
import com.pretty_tools.dde.client.DDEClientConversation;

public class DDEWriter {

    private String excelPath = "C://Users/user/Desktop/DDE/[SPXT.xlsx]Trading";
    private DDEConnection ddeConnection = new DDEConnection();
    private DDEClientConversation conversation;

    // Constructor
    public DDEWriter() {
        this.conversation = ddeConnection.createNewConversation(excelPath);
    }

    // Write the data to the excel
    private void writeData() {
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