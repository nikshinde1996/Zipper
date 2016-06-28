package Main;
/**
 * Created by Nikhil on 25-06-2016.
 */
import net.lingala.zip4j.core.*;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.*;
import net.lingala.zip4j.util.*;
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
         unzippath = parent + tounzip.getName().substring(0,tounzip.getName().lastIndexOf("."));
    }

    public  void UnZipperFucntion() {
        try {
            ZipFile zipFile = new ZipFile(tounzip);
            if (zipFile.isEncrypted()) {
                //zipFile.setPassword(password);
            }
            zipFile.extractAll(unzippath);
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println("File Decompressed");
    }
}
