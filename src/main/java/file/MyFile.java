package file;

import java.io.*;

public class MyFile extends File {

    PrintWriter writer;
    String pathname;

    public MyFile( String pathname ) {
        super( pathname );
        this.pathname = pathname;
    }

    public void write( String s ) {
        try {
            writer = new PrintWriter( pathname, "UTF-8" );
        } catch ( FileNotFoundException e ) {
            e.printStackTrace( );
        } catch ( UnsupportedEncodingException e ) {
            e.printStackTrace( );
        }

        writer.write( s );
        writer.close( );
    }

    public void writeObject( Object serObj ) {
        try {
            FileOutputStream fileOut = new FileOutputStream( this );
            ObjectOutputStream objectOut = new ObjectOutputStream( fileOut );
            objectOut.writeObject( serObj );
            objectOut.close( );
            fileOut.close( );
        } catch ( Exception ex ) {
            ex.printStackTrace( );
        }
    }

    public Object readObject() throws IOException, ClassNotFoundException {
        FileInputStream fileIn = new FileInputStream( this );
        ObjectInputStream objectIn = new ObjectInputStream( fileIn );
        Object obj = objectIn.readObject( );
        objectIn.close( );
        return obj;
    }
}
