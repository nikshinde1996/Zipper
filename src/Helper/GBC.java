package Helper;
/**
 * Created by Nikhil on 16-06-2016.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
public class GBC extends GridBagConstraints {

    public GBC(int gridx,int gridy){
        this.gridx = gridx;
        this.gridy = gridy;
        this.anchor = WEST;
    }

    public GBC(int gridx,int gridy,int gridWidth,int gridHeight){
        this.gridx = gridx;
        this.gridwidth = gridWidth;
        this.gridheight = gridHeight;
        this.gridy = gridy;
    }

    public GBC setAnchor(int anchor){
        this.anchor = anchor;
        return this;
    }

    public GBC setFill(int fill){
        this.fill = fill;
        return this;
    }

    public GBC setWeights(double weightx,double weighty){
        this.weightx = weightx;
        this.weighty = weighty;
        return this;
    }

    public GBC setInsets(int dis){
        this.insets = new Insets(dis,dis,dis,dis);
        return this;
    }

    public GBC setInsets(int top,int left,int bottom,int right){
        this.insets = new Insets(top,left,bottom,right);
        return this;
    }

    public GBC setIpad(int ipadx,int ipady){
        this.ipadx = ipadx;
        this.ipady = ipady;
        return this;
    }
    public GBC setPreferredSize(int width,int height){
        this.gridwidth = width;
        this.gridheight = height;
        return this;
    }
}