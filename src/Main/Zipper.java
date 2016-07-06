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
     public static JPanel urlpanel ,progresspanel,buttonpanel,imagep,bpanel;
     private static JButton selectFile,zipfile;
     private static JTextArea selectedurl ;
     private static ZipFile zipFile;
     private static Zip4jConstants zip4jConstants;
     private static JComboBox<Object> compressiontype;
     private static JProgressBar zippingprogress = new JProgressBar();
     private static JLabel status = new JLabel();

     public Zipper(){
          setBackground(Color.WHITE);
          setUrlpanel();
          setButtonPanel();
          setProgressbar();
          setLayout(new GridBagLayout());

         add(urlpanel, new GBC(0,0).setInsets(5,5,3,5));
         add(buttonpanel,new GBC(0,1).setFill(GBC.BOTH).setInsets(5));
         add(progresspanel,new GBC(0,2).setFill(GBC.BOTH).setInsets(5,5,2,5));

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
         buttonpanel = new JPanel(new BorderLayout());
         imagep = new JPanel(new BorderLayout());imagep.setPreferredSize(new Dimension(200,150));
         compressiontype = new JComboBox<>();compressiontype.setPrototypeDisplayValue("           COMPRESSION LEVEL        ");
         ImageIcon imageIcon = new ImageIcon(new ImageIcon("res//zip3.png").getImage().getScaledInstance(200,180, Image.SCALE_DEFAULT));
         JLabel text,ilabel = new JLabel();
         ilabel.setIcon(imageIcon);
         zipfile = new JButton();zipfile.setIcon(new ImageIcon(new ImageIcon("res//bimg1.png").getImage().getScaledInstance(155,80, Image.SCALE_DEFAULT)));

         bpanel = new JPanel(new GridBagLayout());
         bpanel.add(text = new JLabel("*Select Compression Level"),new GBC(0,0).setInsets(3));text.setFont(new Font("Serif", Font.BOLD, 16));
         bpanel.add(compressiontype,new GBC(0,1).setInsets(10,5,5,5));compressiontype.setPreferredSize(new Dimension(200,20));
         bpanel.add(zipfile,new GBC(0,2).setInsets(10,5,10,5));zipfile.setPreferredSize(new Dimension(50,20));
         imagep.add(ilabel,BorderLayout.CENTER);

         buttonpanel.add(imagep,BorderLayout.WEST);
         buttonpanel.add(bpanel,BorderLayout.CENTER);
         buttonpanel.setPreferredSize(new Dimension(ZipperGui.d.width/3-70,160));
     }

     public static void setProgressbar(){
         progresspanel = new JPanel();
         status = new JLabel();status.setPreferredSize(new Dimension(ZipperGui.d.width/3-70,50));
         status.setBorder(BorderFactory.createTitledBorder("Status"));

         progresspanel.add(status,BorderLayout.NORTH);
         progresspanel.add(zippingprogress,BorderLayout.CENTER);
         progresspanel.setPreferredSize(new Dimension(ZipperGui.d.width/3-70,100));
         zippingprogress.setPreferredSize(new Dimension(ZipperGui.d.width/3-70,20));
         progresspanel.setBorder(BorderFactory.createEmptyBorder());
     }
}

