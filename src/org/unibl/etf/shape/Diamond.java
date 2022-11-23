package org.unibl.etf.shape;

import java.io.File;

public final class Diamond {
    private Integer positionX;
    private Integer positionY;
    private static String diamondImage; //slicica
    private static File file=new File("src/sample");

    public Diamond()
    {
        diamondImage=file.getAbsolutePath()+"\\diamond.png";
    }
    public Diamond(Integer x,Integer y)
    {
        this.positionX=x;
        this.positionY=y;
        diamondImage=file.getAbsolutePath()+"\\diamond.png";
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