package Main;

import javax.swing.*;
import java.awt.*;
import Helper.*;
import java.io.*;
import java.io.File.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;
import java.util.List;
import java.util.zip.*;

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
    private static JRadioButton zipsrb,ziprb,rarrb;
    private static JCheckBox c1,c2,c3,c4,c5;
    private static JComboBox cmprtype;
    private static ButtonGroup bgp = new ButtonGroup();
    private static JFileChooser fileChooser;
    private static String fpath="",zpath="",ofname="",password="";
    private static File ffile,zfile;

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
        urlPanel.add(browse=new JButton("Browse"),new GBC(1,0).setInsets(2,225,3,2));
        urlPanel.add(selectedFile = new JScrollPane(selectedUrl = new JTextArea(1,37),JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),new GBC(0,1,2,1).setInsets(0,3,5,3));
        selectedFile.setBorder(BorderFactory.createLineBorder(new Color(204,204,179)));
        selectedUrl.setEditable(false);

        urlPanel.add(new JLabel("Archive Name"),new GBC(0,3).setInsets(2,3,0,0));
        urlPanel.add(arcName = new JScrollPane(arcNameArea = new JTextArea(1,37),JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),new GBC(0,4,2,0).setInsets(0,3,5,3));
        arcName.setBorder(BorderFactory.createLineBorder(new Color(204,204,179)));
        arcNameArea.setEditable(false);

        fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setCurrentDirectory(new File("/home/nikhil"));

        browse.addActionListener(e->{
            int r = fileChooser.showOpenDialog(null);
            if(r != JFileChooser.APPROVE_OPTION)return;
            fpath = fileChooser.getSelectedFile().getAbsolutePath();
            ffile = new File(fpath);
            ofname = fileChooser.getSelectedFile().getName();
            selectedUrl.setText("  "+fpath);
            setZipArcName();
        });
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
        bgPanel.add(ziprb = new JRadioButton("Zip"));
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

        ziprb.addActionListener(e->{
            setZipArcName();
        });

        zipsrb.addActionListener(e->{
            setZipArcName();
        });

        rarrb.addActionListener(e->{
            setZipArcName();
        });

        passwd.addActionListener(e->{
            if(!fpath.equals("") || !zpath.equals("")) {
                JPasswordField pf = new JPasswordField();
                int okCxl = JOptionPane.showConfirmDialog(null, pf, "Enter Password", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

                if (okCxl == JOptionPane.OK_OPTION) {
                    password = new String(pf.getPassword());
                }
            }else{
                JOptionPane.showMessageDialog(null,"File/Folder not selected","Message",JOptionPane.ERROR_MESSAGE);
            }
        });

    }

    public void populatebuttonPanel(){
        buttonPanel = new JPanel();
        buttonPanel.add(compress = new JButton("Compress"));
        buttonPanel.add(cancel = new JButton("Cancel"));
        buttonPanel.add(help = new JButton("Help"));
        compress.setPreferredSize(new Dimension(135,20));
        cancel.setPreferredSize(new Dimension(135,20));
        help.setPreferredSize(new Dimension(135,20));

        compress.addActionListener(e->{
             if(!fpath.equals("")){
                 if(!rarrb.isSelected() && !ziprb.isSelected() && !ziprb.isSelected()){
                     JOptionPane.showMessageDialog(null,"Archive Type not selected","Message",JOptionPane.ERROR_MESSAGE);
                 }else{
                     if(rarrb.isSelected()){
                         makeRar();
                     }else if(ziprb.isSelected()){
                         makeZip();
                     }else if(zipsrb.isSelected()){
                         makeZips();
                     }
                 }
             }else{
                 JOptionPane.showMessageDialog(null,"File/Folder not selected","Message",JOptionPane.ERROR_MESSAGE);
             }
        });

        cancel.addActionListener(e->{
             selectedUrl.setText("");
             arcNameArea.setText("");
        });

        help.addActionListener(e->{

        });
    }

    void setZipArcName() {
        if (!fpath.equals("")) {
            if (ziprb.isSelected()) {
                zpath = fileChooser.getSelectedFile().getName() + ".zip";
            } else if (zipsrb.isSelected()) {

            } else if (rarrb.isSelected()) {
                zpath = fileChooser.getSelectedFile().getName() + ".rar";
            }
            arcNameArea.setText("  "+zpath);
        }
    }

    public static void makeZip(){
        if(password.equals("")){
            if(ffile.isDirectory()){
                System.out.println("Compressing Directory");
                try {
                    zipDir(Paths.get(fpath),Paths.get(fpath+".zip"));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else if(ffile.isFile()){
                System.out.println("Compressing File");
                zipFile();
            }
        }else{
            if(ffile.isDirectory()){
                System.out.println("Compressing Dir with Password");


            }else if (ffile.isFile()){
                System.out.println("Compressing file with Password");

            }
        }
        JOptionPane.showMessageDialog(null,ofname+" compressed Successfully.","Message",JOptionPane.PLAIN_MESSAGE);
        selectedUrl.setText(fpath = "");
        arcNameArea.setText(zpath = "");
        password="";
    }

    public static void makeRar(){

        if(password.equals("")){


        }else{


        }
        JOptionPane.showMessageDialog(null,ofname+" compressed Successfully.","Message",JOptionPane.PLAIN_MESSAGE);
        selectedUrl.setText(fpath = "");
        arcNameArea.setText(zpath = "");
        password="";
    }

    public static void makeZips(){
        if(password.equals("")){


        }else{


        }
        JOptionPane.showMessageDialog(null,ofname+"compressed Successfully.","Message",JOptionPane.PLAIN_MESSAGE);
        selectedUrl.setText(fpath = "");
        arcNameArea.setText(zpath = "");
        password="";
    }

    public static void zipFile(){
        String zippath = fpath+".zip";
        try {

            FileOutputStream fos = new FileOutputStream(zippath);
            ZipOutputStream zos = new ZipOutputStream(fos);

            ZipEntry ze = new ZipEntry(ffile.getName());
            zos.putNextEntry(ze);

            FileInputStream fis = new FileInputStream(ffile);
            byte[] buffer = new byte[1024];

            int len;
            while ((len = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, len);
            }

            zos.closeEntry();
            zos.close();
            fis.close();
            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void zipDir(final Path folder, final Path zipFilePath) throws IOException {
        try (
                FileOutputStream fos = new FileOutputStream(zipFilePath.toFile());
                ZipOutputStream zos = new ZipOutputStream(fos)
        ) {
            Files.walkFileTree(folder, new SimpleFileVisitor<Path>() {
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    zos.putNextEntry(new ZipEntry(folder.relativize(file).toString()));
                    Files.copy(file, zos);
                    zos.closeEntry();
                    return FileVisitResult.CONTINUE;
                }

                public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
                    zos.putNextEntry(new ZipEntry(folder.relativize(dir).toString() + "/"));
                    zos.closeEntry();
                    return FileVisitResult.CONTINUE;
                }
            });
        }
    }

}