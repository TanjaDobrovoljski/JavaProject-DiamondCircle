package org.unibl.etf.game.figures;

import org.unibl.etf.game.cards.Deck;
import org.unibl.etf.shape.Diamond;
import org.unibl.etf.shape.DiamondShape;
import org.unibl.etf.tools.Tuple;


import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

import static sample.Game.playField;

public class GhostFigure extends Figure{
    private int matrixDimension,rand;
    private Set<Diamond> list=new HashSet<Diamond>();


    public GhostFigure(DiamondShape ds, Deck deck)
    {
        super(ds,deck);
        this.matrixDimension=DiamondShape.random;
        Random random = new Random();
        rand = 0;
        while (true){
            rand = random.nextInt(matrixDimension);
            if(rand >= 2 && rand<= matrixDimension) break;
        }

    }

    public Set<Diamond> getList() {
        return list;
    }

    public void setList(Set<Diamond> list) {
        this.list = list;
    }

    public void playGhost(DiamondShape ds)
    {
        int tmpRand=rand;
        List<Tuple<Integer, Integer>> temp=ds.getmovements();
        while(tmpRand>0)
        {
            Tuple<Integer,Integer> element = temp.get(new Random().nextInt(temp.size()));

            try {
                temp.remove(element);
                list.add(new Diamond(element.getItem1(), element.getItem2()));
                tmpRand--;
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
        }
       /* for (Diamond d:list) {
            ImageView imageView=new ImageView();
            Image imProfile = new Image(d.getLogo()); //ucitavanje slike
            imageView.setImage(imProfile);
            imageView.setFitHeight(40);
            imageView.setFitWidth(40);
            playField.add(imageView,d.getxCoordinate(),d.getyCoordinate());
        }
*/
    }

    /*@Override
    public  void  run() {

        Platform.runLater(() -> {
            playGhost(dS);
            try{
                sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
    }*/
}