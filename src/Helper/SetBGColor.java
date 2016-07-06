package Helper;

import Main.Zipper;

import java.awt.*;

public class SetBGColor {
    Color color;
    public SetBGColor(Color color){
        this.color = color;
        Main.ZipperGui.panel.setBackground(color);
        Zipper.urlpanel.setBackground(color);
        Zipper.buttonpanel.setBackground(color);
        Zipper.progresspanel.setBackground(color);
        Zipper.imagep.setBackground(color);
        Zipper.bpanel.setBackground(color);
    }
}
