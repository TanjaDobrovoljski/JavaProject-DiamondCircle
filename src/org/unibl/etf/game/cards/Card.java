package org.unibl.etf.game.cards;

import java.io.File;
import java.io.Serializable;

public abstract class Card implements Serializable {
    protected String image;
    private static File file=new File("src/sample");


    public Card ()
    {
        this.image=file.getAbsolutePath();
    }

    public String getSlika() {
        return image;
    }

    public void setSlika(String slika) {
        this.image = slika;
    }
}