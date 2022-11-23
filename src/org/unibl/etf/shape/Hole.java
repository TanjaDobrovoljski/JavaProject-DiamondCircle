package org.unibl.etf.shape;

import static sample.Game.playField;
import java.awt.*;
import java.io.File;

public class Hole {
    private Integer positionX;
    private Integer positionY;
    private static File file=new File("src/sample");
    private String image;


    public Hole()
    {
        image=file.getAbsolutePath()+"\\hole.png";

    }

    public Hole(Integer x, Integer y)
    {
        this.positionX=x;
        this.positionY=y;
        image=file.getAbsolutePath()+"\\hole.png";

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