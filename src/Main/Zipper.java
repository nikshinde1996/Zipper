package Main;

import Helper.GBC;

import javax.swing.*;
import java.awt.*;
import Helper.*;
import Main.*;

/**
 * Created by nikhil on 25/12/16.
 */
class Zipper extends JPanel {
    private static JPanel urlPanel,infoPanel,buttonPanel;
    private static JPanel arcPanel,bgPanel;
    private static JButton browse,passwd,compress,cancel,help;
    private static JScrollPane selectedFile,arcName;
    private static JTextArea selectedUrl,arcNameArea;
//    private static ZipperGui zg = new ZipperGui();
    private static JRadioButton zipsrb,ziprb,rarrb;
    private static JCheckBox c1,c2,c3,c4,c5;
    private static JComboBox cmprtype;
    private static ButtonGroup bgp = new ButtonGroup();


    public Zipper(){
        populateUrlPanel();
        populateInfoPanel();
        populatebuttonPanel();

        setLayout(new GridBagLayout());
        add(urlPanel,new GBC(0,0).setInsets(5));
        add(infoPanel,new GBC(0,1).setInsets(5));
        add(buttonPanel,new GBC(0,2).setInsets(5));
    }

    public void populateUrlPanel(){
        urlPanel = new JPanel();
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        urlPanel.setSize(new Dimension(390,300));
        urlPanel.setLayout(new GridBagLayout());
        urlPanel.setBorder(BorderFactory.createTitledBorder("Url settings"));
        urlPanel.add(new JLabel("File/Folder Url"),new GBC(0,0).setInsets(0,3,0,0));
        urlPanel.add(new JButton("Browse"),new GBC(1,0).setInsets(2,225,3,2));
        urlPanel.add(selectedFile = new JScrollPane(selectedUrl = new JTextArea(1,37),JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),new GBC(0,1,2,1).setInsets(0,3,5,3));
        selectedFile.setBorder(BorderFactory.createLineBorder(new Color(204,204,179)));

        urlPanel.add(new JLabel("Archive Name"),new GBC(0,3).setInsets(2,3,0,0));
        urlPanel.add(arcName = new JScrollPane(arcNameArea = new JTextArea(1,37),JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),new GBC(0,4,2,0).setInsets(0,3,5,3));
        arcName.setBorder(BorderFactory.createLineBorder(new Color(204,204,179)));
    }

    public void populateInfoPanel(){
        infoPanel = new JPanel();
        arcPanel = new JPanel();
        bgPanel = new JPanel();

        arcPanel.setLayout(new GridBagLayout());
        arcPanel.add(c1=new JCheckBox("Delete files after archiving"),new GBC(0,0));
        arcPanel.add(c2=new JCheckBox("Create SFX archive"),new GBC(0,1));
        arcPanel.add(c3=new JCheckBox("Create sold archive"),new GBC(0,2));
        arcPanel.add(c4=new JCheckBox("Test archive files"),new GBC(0,3));
        arcPanel.add(c5=new JCheckBox("Lock archive"),new GBC(0,4));
        arcPanel.setBorder(BorderFactory.createTitledBorder("Archive options"));

        bgPanel = new JPanel();
        bgPanel.add(ziprb = new JRadioButton("Zip",true));
        bgPanel.add(zipsrb = new JRadioButton("Zips"));
        bgPanel.add(rarrb = new JRadioButton("Rar"));
        bgp.add(ziprb);bgp.add(zipsrb);bgp.add(rarrb);
        bgPanel.setBorder(BorderFactory.createTitledBorder("Archive Type"));

        String[] type = {"Normal","Deflated","Extra_Comp","Hoffman"};

        infoPanel.setSize(new Dimension(390,400));
        infoPanel.setLayout(new GridBagLayout());
        infoPanel.setBorder(BorderFactory.createTitledBorder("File settings"));
        infoPanel.add(bgPanel,new GBC(0,0));
        infoPanel.add(cmprtype = new JComboBox(type),new GBC(0,1).setInsets(10,3,5,10));
        infoPanel.add(passwd=new JButton("Set Password"),new GBC(0,2).setInsets(5,3,5,5));
        infoPanel.add(arcPanel,new GBC(1,0,3,0));
        cmprtype.setPreferredSize(new Dimension(180,20));
        passwd.setPreferredSize(new Dimension(180,20));


    }

    public void populatebuttonPanel(){
        buttonPanel = new JPanel();
        buttonPanel.add(compress = new JButton("Compress"));
        buttonPanel.add(cancel = new JButton("Cancel"));
        buttonPanel.add(help = new JButton("Help"));
        compress.setPreferredSize(new Dimension(135,20));
        cancel.setPreferredSize(new Dimension(135,20));
        help.setPreferredSize(new Dimension(135,20));
    }

}