package Main;
/**
 * Created by Nikhil on 25-06-2016.
 */


import net.lingala.zip4j.core.*;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.model.*;
import net.lingala.zip4j.progress.*;
import net.lingala.zip4j.progress.ProgressMonitor;
import net.lingala.zip4j.util.*;
import com.sun.xml.internal.ws.api.server.Adapter;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import Helper.*;
import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.nio.file.Path;
import java.util.zip.*;
import java.io.*;
import java.nio.*;
import java.util.*;
import java.lang.String.*;

import static Helper.Zip4jParameter.*;

public class UnZipper extends JPanel implements PropertyChangeListener{
    public static JPanel uzurlpanel ,uzprogresspanel,uzbuttonpanel,uzimagep,uzbpanel;
    private static JButton uzselectFile,unzipfile;
    private static JTextArea uzselectedurl ;
    private static ZipFile zipFile;
    private static ZipParameters uzparameters;
    private static Zip4jConstants zip4jConstants;
    private static JComboBox<String> decompressiontype;
    private static JProgressBar unzippingprogress = new JProgressBar(0,100);
    private static JLabel uzstatus = new JLabel();
    private static JFileChooser uzchooser;
    private static String filepath = "",comp_level="AES_STRENGTH_128",zippath = "";
    private static JTextArea statust = new JTextArea();
    private static net.lingala.zip4j.progress.ProgressMonitor progressMonitor = new ProgressMonitor();
    private static JScrollPane selectedfile;

    protected static String gaps = "    ";
    protected static String status1 = "File not Selected ";
    protected static String status2 = "File Item Selected ";
    protected static String status3 = "UnZipping Selected Item ...";
    protected static String status5 = "UnZipping Selected Item ..";
    protected static String status6 = "UnZipping Selected Item .";
    protected static String status4 = "UnZipping Done Successfully";
    protected static Date date = new Date();

    public UnZipper(){
        setBackground(Color.WHITE);
        setUrlpanel();
        setButtonPanel();
        setProgressbar();
        setLayout(new GridBagLayout());

        add(uzurlpanel, new GBC(0,0).setInsets(5,5,3,5));
        add(uzbuttonpanel,new GBC(0,1).setFill(GBC.BOTH).setInsets(3));
        add(uzprogresspanel,new GBC(0,2).setFill(GBC.BOTH).setInsets(5,5,2,5));
    }

    public static void setUrlpanel(){
        uzurlpanel = new JPanel();
        uzurlpanel.add(selectedfile = new JScrollPane(uzselectedurl = new JTextArea(2,35),JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER));
        uzurlpanel.add(uzselectFile = new JButton("..."));;

        uzselectedurl.setEditable(false);
        uzselectedurl.setToolTipText("Selcted file url");
        uzselectedurl.setBorder(BorderFactory.createLineBorder(Color.lightGray));

        uzselectFile.setPreferredSize(new Dimension(100,32));

        uzchooser = new JFileChooser();
        uzchooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        uzchooser.setCurrentDirectory(new File("."));

        uzselectFile.addActionListener(e->{
            int r = uzchooser.showOpenDialog(null);
            if(r != JFileChooser.APPROVE_OPTION)return;
            zippath = uzchooser.getSelectedFile().getAbsolutePath();
            filepath = zippath.substring(0,zippath.lastIndexOf('.'))+date.getTime();
            uzselectedurl.setText(zippath);
            uzselectedurl.setFont(new Font("serif",Font.PLAIN,12));
            statust.setText(status2);
        });
    }

    public static void setButtonPanel(){
        uzbuttonpanel = new JPanel(new BorderLayout());
        uzimagep = new JPanel(new BorderLayout());
        uzimagep.setPreferredSize(new Dimension(200,150));

        String[] types = Zip4jParameter.getCompLevels();

        decompressiontype = new JComboBox<>(types);decompressiontype.setPrototypeDisplayValue("           COMPRESSION LEVEL        ");
        ImageIcon imageIcon = new ImageIcon(new ImageIcon("res//zip3.png").getImage().getScaledInstance(200,180, Image.SCALE_DEFAULT));
        JLabel text,ilabel = new JLabel();
        ilabel.setIcon(imageIcon);
        unzipfile = new JButton(new ImageIcon(new ImageIcon("res//unzipbutton.png").getImage().getScaledInstance(155,80, Image.SCALE_DEFAULT)));
        unzipfile.setContentAreaFilled(false);unzipfile.setFocusPainted(true);

        uzbpanel = new JPanel(new GridBagLayout());
        uzbpanel.add(text = new JLabel("*Select Decompression Level"),new GBC(0,0).setInsets(3));text.setFont(new Font("Serif", Font.BOLD, 16));
        uzbpanel.add(decompressiontype,new GBC(0,1).setInsets(10,5,5,5));decompressiontype.setPreferredSize(new Dimension(200,20));
        uzbpanel.add(unzipfile,new GBC(0,2).setInsets(20,5,10,5));unzipfile.setPreferredSize(new Dimension(50,10));
        uzimagep.add(ilabel,BorderLayout.CENTER);

        uzbuttonpanel.add(uzimagep,BorderLayout.WEST);
        uzbuttonpanel.add(uzbpanel,BorderLayout.CENTER);
        uzbuttonpanel.setPreferredSize(new Dimension(ZipperGui.d.width/3-70,160));

        decompressiontype.addActionListener(e->{
            comp_level = decompressiontype.getSelectedItem().toString();
        });

        unzipfile.addActionListener(e->{

                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        try{

                            zipFile = new ZipFile(zippath);
                            File inputFileH = new File(filepath);
                            progressMonitor = zipFile.getProgressMonitor();
                            progressMonitor.setPercentDone(progressMonitor.getPercentDone());
                            statust.setText(status3);
                            zipFile.extractAll(filepath);

                            statust.setText(status4);
                            Thread.sleep(10000);
                            statust.setText(status1);
                            uzselectedurl.setText("");
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
                        unzippingprogress.setValue(progress);
                    }
                });

                t.start();
                timer.start();

        });
    }

    public static void setProgressbar(){
        uzprogresspanel = new JPanel();
        statust.setPreferredSize(new Dimension(ZipperGui.d.width/3-70,50));
        statust.setBorder(BorderFactory.createTitledBorder("Status"));
        statust.setFont(new Font("serif",Font.CENTER_BASELINE,18));
        statust.setText(gaps+status1);statust.setEditable(false);

        unzippingprogress.setValue(0);unzippingprogress.setStringPainted(true);

        uzprogresspanel.add(new JScrollPane(statust),BorderLayout.NORTH);
        uzprogresspanel.add(unzippingprogress,BorderLayout.CENTER);
        uzprogresspanel.setPreferredSize(new Dimension(ZipperGui.d.width/3-70,100));
        unzippingprogress.setPreferredSize(new Dimension(ZipperGui.d.width/3-70,20));
        uzprogresspanel.setBorder(BorderFactory.createEmptyBorder());
    }
}

