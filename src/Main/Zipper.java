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

public class Zipper {
    private static File tozip;
    private String currentpath,zippath;
    Calendar calendar = Calendar.getInstance();
    long millisecs = calendar.getTime().getTime();

    public Zipper(File file){
        tozip = file;
        currentpath = tozip.getAbsolutePath();
        zippath = currentpath+ "_" + millisecs + ".zip";
    }

    public   void zipFunction() {
        try {
            ZipFile zipFile = new ZipFile(zippath);
            File inputFileH = new File(currentpath);
            ZipParameters parameters = new ZipParameters();

            // COMP_DEFLATE is for compression
            // COMp_STORE no compression
            parameters.setCompressionMethod(Zip4jConstants.COMP_DEFLATE);
            // DEFLATE_LEVEL_ULTRA = maximum compression
            // DEFLATE_LEVEL_MAXIMUM
            // DEFLATE_LEVEL_NORMAL = normal compression
            // DEFLATE_LEVEL_FAST
            // DEFLATE_LEVEL_FASTEST = fastest compression
            parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_ULTRA);

            // file compressed
            zipFile.addFile(inputFileH, parameters);

            long uncompressedSize = inputFileH.length();
            File outputFileH = new File(zippath);
            long comrpessedSize = outputFileH.length();

            //System.out.println("Size "+uncompressedSize+" vs "+comrpessedSize);
            double ratio = (double)comrpessedSize/(double)uncompressedSize;
            System.out.println("File compressed with compression ratio : "+ ratio);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }}
