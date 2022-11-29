package org.unibl.etf.game.figures;

import org.unibl.etf.game.cards.Deck;
import org.unibl.etf.game.players.Player;
import org.unibl.etf.shape.DiamondShape;
import org.unibl.etf.tools.Tuple;

import java.util.List;

public class LevitatingFigure extends Figure implements levitationInterface{
    private StarShape shape;
    public LevitatingFigure(DiamondShape d, Player p)
    {
        super(d,p);
        shape=new StarShape();
        shape.setForeground(p.getColor());
    }

    public StarShape getShape() {
        return shape;
    }

    public void setShape(StarShape shape) {
        this.shape = shape;
    }

    @Override
    public void levitate() {

    }

    @Override
    public  void run()
    {
        System.out.println("levitirajuca figura");
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
        }}
}