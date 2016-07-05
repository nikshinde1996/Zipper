package Main;
/**
 * Created by Nikhil on 25-06-2016.
 */
import com.sun.deploy.panel.JavaPanel;
import com.sun.deploy.ref.Helpers;
import com.sun.org.apache.xpath.internal.operations.String;
import net.lingala.zip4j.core.*;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.*;
import net.lingala.zip4j.util.*;
import com.sun.xml.internal.ws.api.server.Adapter;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import Helper.*;
import javax.swing.*;
import java.awt.*;
import java.nio.file.Path;
import java.util.zip.*;
import java.io.*;
import java.nio.*;
import java.util.*;

import static Helper.Zip4jParameter.*;

public class Zipper extends JPanel{
     public static JPanel urlpanel ,progresspanel,buttonpanel;
     private static JButton selectFile;
     private static JTextArea selectedurl ;
     private static ZipFile zipFile;
     private static Zip4jConstants zip4jConstants;
     private static JComboBox<Object> compressiontype;
     private static JProgressBar zippingprogress = new JProgressBar();
     private static JLabel imagelabel,status = new JLabel();

     public Zipper(){
          setBackground(Color.WHITE);
          setUrlpanel();
          setButtonPanel();
          setProgressbar();
          setLayout(new GridBagLayout());

         add(urlpanel, new GBC(0,0));
         add(buttonpanel,new GBC(0,1).setFill(GBC.BOTH));
         add(progresspanel,new GBC(0,2).setFill(GBC.BOTH));

     }

     public static void setUrlpanel(){
         urlpanel = new JPanel();
         urlpanel.add(selectedurl = new JTextArea(2,35));
         selectedurl.setEditable(false);selectedurl.setToolTipText("Selcted file url");
         selectedurl.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY));
         urlpanel.add(selectFile = new JButton("..."));
         selectFile.setPreferredSize(new Dimension(100,32));
     }

     public static void setButtonPanel(){
         buttonpanel = new JPanel();
         buttonpanel.setPreferredSize(new Dimension(ZipperGui.d.width/3-70,250));
     }

     public static void setProgressbar(){
         progresspanel = new JPanel(); 
         progresspanel.add(zippingprogress,BorderLayout.CENTER);
         zippingprogress.setPreferredSize(new Dimension(ZipperGui.d.width/3-70,20));
         progresspanel.setBorder(BorderFactory.createEmptyBorder());
     }
}

