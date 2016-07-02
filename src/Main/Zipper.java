package Main;
/**
 * Created by Nikhil on 25-06-2016.
 */
import net.lingala.zip4j.core.*;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.*;
import net.lingala.zip4j.util.*;

import javax.swing.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.nio.file.Path;
import java.util.zip.*;
import java.io.*;
import java.nio.*;
import java.util.*;

public class Zipper extends  SwingWorker<Void,Void> implements PropertyChangeListener{
    private static File tozip;
    private String currentpath,zippath;
    Calendar calendar = Calendar.getInstance();
    long millisecs = calendar.getTime().getTime();
    private ZipperGui zgui = new ZipperGui();

    public Zipper(File file){
        tozip = file;
        currentpath = tozip.getAbsolutePath();
        zippath = currentpath+ "_" + millisecs + ".zip";
    }

    public  void zipFunction(Zipper z) throws IOException {
        FileOutputStream fos = new FileOutputStream(zippath);
        ZipOutputStream zos = new ZipOutputStream(fos);
        z.addDirToZipArchive(zos,new File(currentpath),null);
        zos.flush();
        fos.flush();
        zos.close();
        fos.close();
        execute();
    }

    public void addDirToZipArchive(ZipOutputStream zos,File fileToZip,String ParentDir) throws IOException{
          if(fileToZip==null || !fileToZip.exists()){
              return;
          }
          String zipEntryName = fileToZip.getName();

        if (ParentDir != null && !ParentDir.isEmpty()) {
            zipEntryName = ParentDir + "//" +fileToZip.getName();
        }

        if(fileToZip.isDirectory()){
            for(File f:fileToZip.listFiles()){
                addDirToZipArchive(zos,f,zipEntryName);
            }
        }else{
            byte[] buffer = new byte[1024];
            FileInputStream fis = new FileInputStream(fileToZip);
            zos.putNextEntry(new ZipEntry(zipEntryName));
            int length;
            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }
            zos.closeEntry();
            fis.close();
        }
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

    }

    @Override
    protected Void doInBackground() throws Exception {
        int progress = 0;
        Random r = new Random();
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){}
        progress+=r.nextInt(5);
        zgui.zprogress.setValue(Math.min(progress,100));
        return null;
    }

    protected void done(){
        JOptionPane.showMessageDialog(null,"Zipping Done");
    }
}

