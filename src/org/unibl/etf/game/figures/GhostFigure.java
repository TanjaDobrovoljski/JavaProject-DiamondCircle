package org.unibl.etf.game.figures;

import org.unibl.etf.game.cards.Deck;
import org.unibl.etf.game.players.Player;
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


    public GhostFigure(DiamondShape d, Player p)
    {
        super(d,p);
        this.matrixDimension=DiamondShape.random;
    }

    public Set<Diamond> getList() {
        return list;
    }

    public void setList(Set<Diamond> list) {
        this.list = list;
    }

    public void playGhost()
    {
        Random random = new Random();
        rand = 0;
        while (true){
            rand = random.nextInt(matrixDimension);
            if(rand >= 2 && rand<= matrixDimension) break;
        }
        int tmpRand=rand;
        List<Tuple<Integer, Integer>> temp=this.getD().getmovements();
        while(tmpRand>0)
        {
            Tuple<Integer,Integer> element = temp.get(new Random().nextInt(temp.size()));

            try {
                list.add(new Diamond(element.getItem1(), element.getItem2()));
                tmpRand--;
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
        }
       list.stream().forEach(diamond -> DiamondShape.getButtons()[diamond.getPositionX()][diamond.getPositionY()].add(diamond));
        list.clear();
    }

    @Override
    public  void  run() {
        while(true)
        {
            try {
                playGhost();
                System.out.println("duh figura se krece");
                Thread.sleep(5000);
                this.getD().removeDiamonds();
            }catch (NullPointerException | InterruptedException ex) {
                ex.printStackTrace();
            }
        }
    }
}