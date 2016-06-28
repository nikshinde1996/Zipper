package Main;
/**
 * Created by Nikhil on 23-06-2016.
 */
import Helper.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ZipperGui extends JFrame{
    protected static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    protected static JPanel zippanel,zbuttonpanel,zactionpanel;
    protected static JPanel unzippanel,uzbuttonpanel,uzactionpanel;
    protected static JButton zselect,uzselect,zip,unzip;
    protected static JLabel imagelabel;
    protected static JTabbedPane tabbedPane = new JTabbedPane();
    protected static JProgressBar zprogress,uzprogress;
    protected static JFileChooser fileChooser = new JFileChooser(System.getProperty("user.home"));
    protected static Dimension bd ;
    protected static File zfile,uzfile;

    public ZipperGui(){
         setSize(d.width/3,d.height/3+40);
        try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
        } catch (Exception ex) {ex.printStackTrace();}

         setZippanel();
         setUnzippanel();

         tabbedPane.addTab("Zipper",zippanel);
         tabbedPane.addTab("UnZipper",unzippanel);
         add(tabbedPane,BorderLayout.CENTER);
    }

    public static void setZippanel(){
         zippanel = new JPanel();
         zbuttonpanel = new JPanel();
         zactionpanel = new JPanel();
         bd = new Dimension(100,30);
         fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

         ImageIcon img = new ImageIcon(new ImageIcon("res//zip1.png").getImage().getScaledInstance(d.width/5-82,d.height/4-30,Image.SCALE_DEFAULT));
         imagelabel= new JLabel(img);

         zbuttonpanel.setLayout(new GridBagLayout());
         zbuttonpanel.add(zselect = new JButton("        Select File       "),new GBC(0,0).setInsets(10,10,10,10));
         zbuttonpanel.add(zip = new JButton("  Zip Selected File  "),new GBC(0,1).setInsets(10,10,10,10));
         zbuttonpanel.setBorder(BorderFactory.createTitledBorder("Zipper"));
         zbuttonpanel.setPreferredSize(new Dimension(d.width/6-60,d.height/4-30));
         zselect.setMargin(new Insets(5,65,5,35));
         zip.setMargin(new Insets(5,19,5,19));

         zactionpanel.add(zprogress = new JProgressBar(SwingConstants.HORIZONTAL));
         zactionpanel.setBorder(BorderFactory.createTitledBorder("Zipping Status"));
         zprogress.setPreferredSize(new Dimension(360,20));

         zippanel.setLayout(new GridBagLayout());
         zippanel.add(imagelabel,new GBC(0,0));
         zippanel.add(zbuttonpanel,new GBC(1,0));
         zippanel.add(zactionpanel,new GBC(0,1,2,1));
         zippanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,2,true));

         zselect.addActionListener(e->{
             int r = fileChooser.showOpenDialog(null);
             if(r == JFileChooser.APPROVE_OPTION){zfile = fileChooser.getSelectedFile();}
         });

         zip.addActionListener(e->{
             Zipper zipp = new Zipper(zfile);
             zipp.zipFunction();
         });
    }

    public static void setUnzippanel(){
        unzippanel = new JPanel();
        uzbuttonpanel = new JPanel();
        uzactionpanel = new JPanel();
        bd = new Dimension(100,100);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        ImageIcon img = new ImageIcon(new ImageIcon("res//unzip2.png").getImage().getScaledInstance(d.width/5-82,d.height/4-30,Image.SCALE_DEFAULT));
        imagelabel= new JLabel(img);

        uzbuttonpanel.setLayout(new GridBagLayout());
        uzbuttonpanel.add(uzselect = new JButton("         Select File           "),new GBC(0,0).setInsets(10,10,10,10));
        uzbuttonpanel.add(unzip = new JButton("  UnZip Selected File  "),new GBC(0,1).setInsets(10,10,10,10));
        uzbuttonpanel.setBorder(BorderFactory.createTitledBorder("UnZipper"));
        uzbuttonpanel.setPreferredSize(new Dimension(d.width/6-60,d.height/4-30));
        uzselect.setMargin(new Insets(5,35,5,35));
        unzip.setMargin(new Insets(5,10,5,10));

        uzactionpanel.add(uzprogress = new JProgressBar(SwingConstants.HORIZONTAL));
        uzactionpanel.setBorder(BorderFactory.createTitledBorder("Unzipping Status"));
        uzprogress.setPreferredSize(new Dimension(360,20));

        unzippanel.setLayout(new GridBagLayout());
        unzippanel.add(imagelabel,new GBC(0,0));
        unzippanel.add(uzbuttonpanel,new GBC(1,0));
        unzippanel.add(uzactionpanel,new GBC(0,1,2,1));
        unzippanel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY,2,true));

        uzselect.addActionListener(e->{
            int r = fileChooser.showOpenDialog(null);
            if(r == JFileChooser.APPROVE_OPTION){uzfile = fileChooser.getSelectedFile();}
        });

        unzip.addActionListener(e->{

        });
    }

    public static void main(String args[]){
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
               JFrame jframe = new ZipperGui();
               jframe.setVisible(true);
               jframe.setResizable(false);
               jframe.setLocationRelativeTo(null);
               jframe.setIconImage(new ImageIcon("res//zipicon.png").getImage());
               jframe.setTitle("CreZip");
            }
        });
    }
}
