package Main;
/**
 * Created by Nikhil on 23-06-2016.
 */
import Helper.*;

import java.io.File;
import java.util.Calendar;

import com.sun.xml.internal.ws.api.server.Adapter;
import net.lingala.zip4j.core.ZipFile;
import net.lingala.zip4j.exception.ZipException;
import net.lingala.zip4j.model.ZipParameters;
import net.lingala.zip4j.util.Zip4jConstants;

import java.io.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ZipperGui extends JFrame{
    protected static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    public static JPanel panel = new JPanel();
    public ZipperGui(){
        setSize(d.width/3,d.height/2);
        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch(Exception e){
            e.printStackTrace();
        }


        panel.add(new Zipper(),BorderLayout.CENTER);
        panel.setBorder(BorderFactory.createLineBorder(Color.green,4));

        add(panel,BorderLayout.CENTER);
        Helper.SetBGColor s = new SetBGColor(Color.WHITE);
    }

    public static void main(String args[]){
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                JFrame obj = new ZipperGui();
                obj.setVisible(true);
                obj.setLocationRelativeTo(null);
                obj.setIconImage(new ImageIcon("res//zipicon.png").getImage());
            }
        });
    }
}