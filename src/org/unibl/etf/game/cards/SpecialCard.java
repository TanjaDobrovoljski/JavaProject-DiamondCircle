package org.unibl.etf.game.cards;
import static sample.Game.playField;
import org.unibl.etf.shape.*;
import org.unibl.etf.tools.Tuple;

import java.util.Collections;
import java.util.List;

public class SpecialCard extends Card{

    public SpecialCard() {
        super();
        this.image=this.getSlika()+"\\diamondCard.png";
    }

    public void setHoles()
    {
        int min = 1,max =DiamondShape.random;
        int random=(int)Math.floor(Math.random()*(max-min+1)+min);
        List<Tuple<Integer,Integer>> list=DiamondShape.movements();

        Collections.shuffle(list);

        while(random>0)
        {
            DiamondShape.getButtons()[list.get(random).getItem1()][list.get(random).getItem2()].add(new Hole());
            random--;
        }
    }

    public void removeHoles() //refresh za duh figuru (uklanjanje dijamanata)
    {
        var list=DiamondShape.getButtons();
        for (var i: list) {
            for (var k:i) {
                var list2= k.getComponents();
                for (var m:list2)
                {

                    if (m instanceof Hole)
                    { playField.remove(m);
                        k.remove(m);

                    }
                }
            }
        }
    }

}