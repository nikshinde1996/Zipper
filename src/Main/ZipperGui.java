package Main;
/**
 * Created by Nikhil on 23-06-2016.
 */
import Helper.*;
import javax.swing.*;
import java.awt.*;

public class ZipperGui extends JFrame{
    protected static Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
    public static JPanel p1,p2;
    public static JTabbedPane jTabbedPane = new JTabbedPane();

    public ZipperGui(){
        setSize(new Dimension(d.width/3,d.height/2+50));

        try{
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        }catch(Exception e){
            e.printStackTrace();
        }

        jTabbedPane.add("Zipper",p1 = new Zipper());
        jTabbedPane.add("UnZipper",p2 = new UnZipper());
        jTabbedPane.setPreferredSize(new Dimension(d.width/3-20,d.height+30));

        add(jTabbedPane,BorderLayout.CENTER);
        Helper.SetBGColor s = new SetBGColor(Color.orange);
    }

    public static void main(String args[]){
        EventQueue.invokeLater(new Runnable(){
            public void run(){
                JFrame obj = new ZipperGui();
                obj.setVisible(true);
                obj.setLocationRelativeTo(null);
                obj.setIconImage(new ImageIcon("res//zipicon.png").getImage());
                obj.setTitle("CreZip");
            }
        });
    }
}