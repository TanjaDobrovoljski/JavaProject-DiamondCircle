package org.unibl.etf.shape;

import javax.swing.*;
import java.awt.*;
import java.io.File;

public final class Diamond extends JPanel {
    private Integer positionX;
    private Integer positionY;
    private static String diamondImage; //slicica
    private static File file=new File("src/sample");
    private Image im;

    public Diamond()
    {
        diamondImage=file.getAbsolutePath()+"\\diamond.png";
        this.im=new ImageIcon(diamondImage).getImage();
    }

    public Diamond(Integer x,Integer y)
    {
        this.positionX=x;
        this.positionY=y;
        diamondImage=file.getAbsolutePath()+"\\diamond.png";
        this.im=new ImageIcon(diamondImage).getImage();
    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // paint the background image and scale it to fill the entire space
        g.drawImage(im,0,0,null);
    }
    public Integer getPositionX() {
        return positionX;
    }

    public void setPositionX(Integer positionX) {
        this.positionX = positionX;
    }

    public Integer getPositionY() {
        return positionY;
    }

    public  String getDiamondImage() {
        return diamondImage;
    }

    public  void setDiamondImage(String diamondImage) {
        Diamond.diamondImage = diamondImage;
    }


    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }
}