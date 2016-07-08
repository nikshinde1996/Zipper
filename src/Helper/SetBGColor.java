package Helper;

import Main.UnZipper;
import Main.Zipper;
import Main.ZipperGui;

import java.awt.*;

public class SetBGColor {
    Color color;
    public SetBGColor(Color color){
        this.color = color;
        ZipperGui.p1.setBackground(color);
        Main.ZipperGui.panel.setBackground(color);
        Zipper.urlpanel.setBackground(color);
        Zipper.buttonpanel.setBackground(color);
        Zipper.progresspanel.setBackground(color);
        Zipper.imagep.setBackground(color);
        Zipper.bpanel.setBackground(color);
        ZipperGui.jTabbedPane.setBackground(color);
    }
}
