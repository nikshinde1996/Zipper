package Main;
/**
 * Created by Nikhil on 25-06-2016.
 */
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import Helper.*;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.*;

public class Zipper extends JPanel implements PropertyChangeListener{

     public static JPanel urlpanel ,progresspanel,buttonpanel,imagep,bpanel;
     private static JButton selectFile,zipfile;
     private static JTextArea selectedurl ;
     private static ZipFile zipFile;
     private static ZipParameters parameters;
     private static Zip4jConstants zip4jConstants;
     private static JComboBox<String> compressiontype;
     private static JProgressBar zippingprogress = new JProgressBar(0,100);
     private static JLabel status = new JLabel();
     private static JFileChooser chooser;
     private static String filepath = "",comp_level="AES_STRENGTH_128",zippath = "";
     private static JTextArea statust = new JTextArea();
     private static net.lingala.zip4j.progress.ProgressMonitor progressMonitor = new ProgressMonitor();
     private static JScrollPane selectedfile;

     protected static String gaps = "    ";
     protected static String status1 = "File not Selected ";
     protected static String status2 = "File Item Selected ";
     protected static String status3 = "Zipping Selected Item ...";
     protected static String status5 = "Zipping Selected Item ..";
     protected static String status6 = "Zipping Selected Item .";
     protected static String status4 = "Zipping Done Successfully";

     public Zipper(){
          setBackground(Color.WHITE);
          setUrlpanel();
          setButtonPanel();
          setProgressbar();

          setLayout(new GridBagLayout());
          add(urlpanel, new GBC(0,0).setInsets(5,5,3,5));
          add(buttonpanel,new GBC(0,1).setFill(GBC.BOTH).setInsets(3));
          add(progresspanel,new GBC(0,2).setFill(GBC.BOTH).setInsets(5,5,2,5));
     }

     public static void setUrlpanel(){
         urlpanel = new JPanel();
         urlpanel.add(selectedfile = new JScrollPane(selectedurl = new JTextArea(2,35),JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
         urlpanel.add(selectFile = new JButton("..."));

         selectedurl.setEditable(false);selectedurl.setToolTipText("Selcted file url");
         selectedurl.setBorder(BorderFactory.createLineBorder(Color.lightGray));
         selectFile.setPreferredSize(new Dimension(100,32));

         chooser = new JFileChooser();
         chooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
         chooser.setCurrentDirectory(new File("."));

         selectFile.addActionListener(e->{
             int r = chooser.showOpenDialog(null);
             if(r != JFileChooser.APPROVE_OPTION)return;
             filepath = chooser.getSelectedFile().getAbsolutePath();
             zippath = filepath + ".zip";
             selectedurl.setText(filepath);
             selectedurl.setFont(new Font("serif",Font.PLAIN,12));
             statust.setText(status2);
         });
     }

     public static void setButtonPanel(){
         buttonpanel = new JPanel(new BorderLayout());
         imagep = new JPanel(new BorderLayout());
         imagep.setPreferredSize(new Dimension(200,150));

         String[] types = Zip4jParameter.getCompLevels();

         compressiontype = new JComboBox<>(types);compressiontype.setPrototypeDisplayValue("           COMPRESSION LEVEL        ");
         ImageIcon imageIcon = new ImageIcon(new ImageIcon("res//zip3.png").getImage().getScaledInstance(200,180, Image.SCALE_DEFAULT));
         JLabel text,ilabel = new JLabel();
         ilabel.setIcon(imageIcon);
         zipfile = new JButton(new ImageIcon(new ImageIcon("res//zipbutton.png").getImage().getScaledInstance(155,80, Image.SCALE_DEFAULT)));
         zipfile.setContentAreaFilled(false);zipfile.setFocusPainted(true);

         bpanel = new JPanel(new GridBagLayout());
         bpanel.add(text = new JLabel("*Select Compression Level"),new GBC(0,0).setInsets(3));text.setFont(new Font("Serif", Font.BOLD, 16));
         bpanel.add(compressiontype,new GBC(0,1).setInsets(10,5,5,5));compressiontype.setPreferredSize(new Dimension(200,20));
         bpanel.add(zipfile,new GBC(0,2).setInsets(20,5,10,5));zipfile.setPreferredSize(new Dimension(50,10));
         imagep.add(ilabel,BorderLayout.CENTER);

         buttonpanel.add(imagep,BorderLayout.WEST);
         buttonpanel.add(bpanel,BorderLayout.CENTER);
         buttonpanel.setPreferredSize(new Dimension(ZipperGui.d.width/3-70,160));

         compressiontype.addActionListener(e->{
             comp_level = compressiontype.getSelectedItem().toString();
         });

         zipfile.addActionListener(e->{
              if(new File(filepath).isDirectory()){

                  Thread t = new Thread(new Runnable() {
                      @Override
                      public void run() {

                          try{

                              zipFile = new ZipFile(zippath);
                              File inputFileH = new File(filepath);
                              parameters = new ZipParameters();
                              progressMonitor = zipFile.getProgressMonitor();
                              progressMonitor.setPercentDone(progressMonitor.getPercentDone());
                              parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_ULTRA);
                              statust.setText(status3);
                              zipFile.createZipFileFromFolder(filepath,parameters,true,78237482);

                              long uncompressedSize = inputFileH.length();
                              File outputFileH = new File(zippath);
                              long comrpessedSize = outputFileH.length();

                              statust.setText(status4);
                              Thread.sleep(10000);
                              statust.setText(status1);
                              selectedurl.setText("");
                          }catch(Exception e){
                              JOptionPane.showMessageDialog(null,e);
                              statust.setText(status1);
                          }
                      }
                  });

                  Thread timer = new Thread(new Runnable() {
                      @Override
                      public void run() {
                          int progress = progressMonitor.getPercentDone();
                          zippingprogress.setValue(progress);
                      }
                  });

                  t.start();
                  timer.start();

              }else if(new File(filepath).isFile()){


                  Thread t = new Thread(new Runnable() {
                      @Override
                      public void run() {

                          try{

                              zipFile = new ZipFile(zippath);
                              File inputFileH = new File(filepath);
                              parameters = new ZipParameters();
                              progressMonitor = zipFile.getProgressMonitor();
                              progressMonitor.setPercentDone(progressMonitor.getPercentDone());
                              parameters.setCompressionLevel(Zip4jConstants.DEFLATE_LEVEL_ULTRA);
                              statust.setText(status3);
                              zipFile.createZipFile(new File(filepath),parameters,true,78237482);

                              long uncompressedSize = inputFileH.length();
                              File outputFileH = new File(zippath);
                              long comrpessedSize = outputFileH.length();

                              statust.setText(status4);
                              Thread.sleep(10000);
                              statust.setText(status1);
                              selectedurl.setText("");
                          }catch(Exception e){
                              JOptionPane.showMessageDialog(null,e);
                              statust.setText(status1);
                          }
                      }
                  });

                  Thread timer = new Thread(new Runnable() {
                      @Override
                      public void run() {
                          int progress = progressMonitor.getPercentDone();
                          zippingprogress.setValue(progress);
                      }
                  });

                  t.start();
                  timer.start();

              }
         });
     }

     public static void setProgressbar(){
         progresspanel = new JPanel();

         statust.setPreferredSize(new Dimension(ZipperGui.d.width/3-70,50));
         statust.setBorder(BorderFactory.createTitledBorder("Status"));
         statust.setFont(new Font("serif",Font.CENTER_BASELINE,18));
         statust.setText(gaps+status1);statust.setEditable(false);

         zippingprogress.setValue(0);zippingprogress.setStringPainted(true);
         zippingprogress.setPreferredSize(new Dimension(ZipperGui.d.width/3-70,20));

         progresspanel.add(new JScrollPane(statust),BorderLayout.NORTH);
         progresspanel.add(zippingprogress,BorderLayout.CENTER);
         progresspanel.setPreferredSize(new Dimension(ZipperGui.d.width/3-70,100));
         progresspanel.setBorder(BorderFactory.createEmptyBorder());
     }
}

