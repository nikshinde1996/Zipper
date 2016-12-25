package Main;

/**
 * Created by Nikhil on 23-06-2016.
 */

import javax.swing.*;
import java.awt.*;

public class ZipperGui extends JFrame{
    protected static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    protected static JPanel zipPanel,unzipPanel;
    protected static int FRAME_WTDTH,FRAME_HEIGHT;

    public ZipperGui(){
        setDimensions();

        setSize(new Dimension(FRAME_WTDTH,FRAME_HEIGHT));
        zipPanel = new Zipper();
        unzipPanel = new UnZipper();
        JTabbedPane jtb = new JTabbedPane();
        jtb.add("Zip",zipPanel);
      //  jtb.add("Unzip",unzipPanel);
        add(jtb);
        pack();

    }

    private static void setDimensions(){
        FRAME_WTDTH = d.width/3;
        FRAME_HEIGHT = d.height/2;
    }

    private static void makeGUI(){

    }

    public static void main(String args[]){
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                JFrame obj = new ZipperGui();
                obj.setVisible(true);
                obj.setTitle("CreZip");
            //    obj.setResizable(false);
                obj.setLocationRelativeTo(null);
            }
        });
    }



    class UnZipper extends JPanel{
        public UnZipper(){

        }
    }
}