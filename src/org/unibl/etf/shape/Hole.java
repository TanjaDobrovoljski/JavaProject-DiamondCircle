package org.unibl.etf.shape;

import javax.swing.*;

import static sample.Game.playField;
import java.awt.*;
import java.io.File;
import java.io.Serializable;

public final class Hole extends JPanel  implements Serializable {
    private Integer positionX;
    private Integer positionY;
    private static File file=new File("src/sample");
    private String image;
    private Image im;

    public Hole()
    {
        image=file.getAbsolutePath()+"\\hole.png";
        this.im=new ImageIcon(image).getImage();

    }

    public Hole(Integer x, Integer y)
    {
        this.positionX=x;
        this.positionY=y;
        image=file.getAbsolutePath()+"\\hole.png";
        this.im=new ImageIcon(image).getImage();

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); // paint the background image and scale it to fill the entire space
        g.drawImage(im,0,0,null);
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
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

    public void setPositionY(Integer positionY) {
        this.positionY = positionY;
    }
}