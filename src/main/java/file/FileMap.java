package file;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class FileMap {

    public static final String EXCEL_PATH = "EXCEL_PATH";
    public static final String PORT = "PORT";
    public static final String ADDRESS = "ADDRESS";

    private Map map = new <String, Object>HashMap();

    private static FileMap fileMap;

    public static FileMap getInstance() {
        if ( fileMap == null ) {
            fileMap = new FileMap();
        }
        return fileMap;
    }

    public Map getMap() {
        return map;
    }

}
