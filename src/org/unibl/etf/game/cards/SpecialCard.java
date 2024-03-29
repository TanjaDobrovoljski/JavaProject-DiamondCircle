package org.unibl.etf.game.cards;
import static sample.Game.playField;

import org.unibl.etf.game.figures.OrdinaryFigure;
import org.unibl.etf.game.figures.StarShape;
import org.unibl.etf.game.players.Player;
import org.unibl.etf.shape.*;
import org.unibl.etf.tools.Tuple;

import javax.swing.*;
import java.util.*;

public class SpecialCard extends Card{


    private int rand;
    private static Set<Hole> list= new HashSet<>();

    public SpecialCard() {
        super();
        this.image=this.getSlika()+"\\diamondCard.png";

    }

    public static Set<Hole> getList() {
        return list;
    }

    public static void setList(Set<Hole> list) {
        SpecialCard.list = list;
    }

    public void setHoles(){

        if(!list.isEmpty())
            list.clear();
        Random random = new Random();
        rand = 0;
        while (true){
            rand = random.nextInt(DiamondShape.random);
            if(rand >= 1 && rand<= DiamondShape.random) break;
        }
        int tmpRand=rand;
        List<Tuple<Integer, Integer>> temp=DiamondShape.movements();
        while(tmpRand>0)
        {
            Tuple<Integer,Integer> element = temp.get(new Random().nextInt(temp.size()));
            try {
                list.add(new Hole(element.getItem1(), element.getItem2()));
                tmpRand--;
            } catch (NullPointerException ex) {
                ex.printStackTrace();
            }
        }


       list.stream().forEach(hole -> DiamondShape.getButtons()[hole.getPositionX()][hole.getPositionY()].add(hole));
        playField.revalidate();
        playField.repaint();

    }

    public static void removeHoles() //(uklanjanje rupa)
    {
        var list=DiamondShape.getButtons();
        for (var i: list) {
            for (var k:i) {
                var list2= k.getComponents();
                for (var m:list2)
                {

                    if (m instanceof Hole)
                    {
                        playField.remove(m);
                        k.remove(m);


                    }
                }
            }
        }
    }

}