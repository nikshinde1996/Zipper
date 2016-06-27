package Main;
/**
 * Created by Nikhil on 25-06-2016.
 */
import java.nio.file.Path;
import java.util.zip.*;
import java.io.*;
import java.nio.*;
import java.util.*;

public class UnZipper {
    private static File tounzip;
    private String name,currentpath,unzippath,parent;

    public UnZipper(File file){
         tounzip = file;
         parent = file.getParent();
    }
}
