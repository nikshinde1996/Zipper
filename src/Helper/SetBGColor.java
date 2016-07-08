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
        ZipperGui.p2.setBackground(color);

        Zipper.urlpanel.setBackground(color);
        Zipper.buttonpanel.setBackground(color);
        Zipper.progresspanel.setBackground(color);
        Zipper.imagep.setBackground(color);
        Zipper.bpanel.setBackground(color);
        ZipperGui.jTabbedPane.setBackground(color);

        UnZipper.uzurlpanel.setBackground(color);
        UnZipper.uzbuttonpanel.setBackground(color);
        UnZipper.uzprogresspanel.setBackground(color);
        UnZipper.uzimagep.setBackground(color);
        UnZipper.uzbpanel.setBackground(color);
    }
}
