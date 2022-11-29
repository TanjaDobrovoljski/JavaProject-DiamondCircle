package org.unibl.etf.game.figures;

import org.unibl.etf.game.cards.Deck;
import org.unibl.etf.game.players.Player;
import org.unibl.etf.shape.DiamondShape;
import org.unibl.etf.tools.Tuple;

import java.util.Arrays;
import java.util.List;
import static sample.Game.playField;

public class OrdinaryFigure extends Figure{
    private CircleShape shape;

    public CircleShape getShape() {
        return shape;
    }

    public void setShape(CircleShape shape) {
        this.shape = shape;
    }

    public OrdinaryFigure(DiamondShape d, Player p)
    {
        super(d,p);
        shape=new CircleShape();
        shape.setForeground(p.getColor());
    }

    @Override
    public  void run()
    {
        System.out.println("obicna figura");
        for (Tuple<Integer,Integer> i:Figure.futureMovements)
        {
            try {

                this.getD().drawMatrix(this,i.getItem1(),i.getItem2());

               /* this.getD().getButtons()[i.getItem1()][i.getItem2()].add(shape);
                Thread.sleep(1000);

                playField.revalidate();
                playField.repaint();
                System.out.println("broj polja "+ DiamondShape.getButtons().length);
                System.out.println(i.getItem1()+" "+i.getItem2());*/

                Thread.sleep(1000);


            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}