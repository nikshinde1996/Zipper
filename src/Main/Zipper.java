package Main;
/**
 * Created by Nikhil on 25-06-2016.
 */
import java.nio.file.Path;
import java.util.zip.*;
import java.io.*;
import java.nio.*;
import java.util.*;

public class Zipper {
    private static File tozip;
    private String name,parent,currentpath,zippath;

    public Zipper(File file){
        tozip = file;
        parent = tozip.getParent();
        name = tozip.getName().substring(0,tozip.getName().lastIndexOf('.'))+".zip";
        currentpath = tozip.getAbsolutePath();
        zippath = parent+name;
    }

    public void zipFunction(){

    }
}
