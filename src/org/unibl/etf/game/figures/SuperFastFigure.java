package org.unibl.etf.game.figures;

import org.unibl.etf.game.cards.Deck;
import org.unibl.etf.game.players.Player;
import org.unibl.etf.shape.DiamondShape;
import org.unibl.etf.tools.Tuple;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SuperFastFigure extends Figure {
    private SpiralShape shape;
    public SuperFastFigure(DiamondShape d, Player p)
    {
        super(d,p);
        shape=new SpiralShape();
        shape.setColor(p.getColor());
        numberOfMoves*=2;
    }

    public SpiralShape getShape() {
        return shape;
    }

    public void setShape(SpiralShape shape) {
        this.shape = shape;
    }

    @Override
    public  void run()
    {
        System.out.println("super brza figura");
        int limit= Figure.futureMovements.size() / 2 + Math.min(Figure.futureMovements.size() % 2, 1);

        List<Tuple<Integer,Integer>> list = Stream.iterate(0, i -> i + 2)
                .limit(limit)
                .map(Figure.futureMovements::get)
                .collect(Collectors.toList());

        for (Tuple<Integer,Integer> i:list)
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