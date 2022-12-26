package org.unibl.etf.game.figures;

import org.unibl.etf.game.cards.Deck;
import org.unibl.etf.game.players.Player;
import org.unibl.etf.shape.DiamondShape;
import org.unibl.etf.tools.Tuple;

import java.util.List;
import java.util.ListIterator;
import java.util.Timer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SuperFastFigure extends Figure {
    private SpiralShape shape;
    private int indexForFutureMovements=0,moves;
    private Timer timer;

    public SuperFastFigure(DiamondShape d, Player p)
    {
        super(d,p);
        shape=new SpiralShape();
        shape.setColor(p.getColor());
        numberOfMoves*=2;
        timer=new Timer();
    }

    public SpiralShape getShape() {
        return shape;
    }

    public void setShape(SpiralShape shape) {
        this.shape = shape;
    }


    /* int limit= Figure.futureMovements.size() / 2 + Math.min(Figure.futureMovements.size() % 2, 1);

        List<Tuple<Integer,Integer>> list = Stream.iterate(0, i -> i + 2)
                .limit(limit)
                .map(Figure.futureMovements::get)
                .collect(Collectors.toList());*/

    @Override
    public  void makeMove() { //ova metoda moze u figuru,pa da se samo za svaki child overriduje koliko prelazi

        moves = this.getNumberOfMoves();
        if (moves!=0 && this.firstMove==true) {
            moves++;
            this.firstMove=false;
        }
        for (ListIterator<Tuple<Integer, Integer>> it = this.futureMovements.listIterator(indexForFutureMovements); it.hasNext(); ) {
            Tuple<Integer, Integer> i = it.next();
            if (moves == 0) {
                // setTurn(false);
                break;
            }
            this.getD().drawMatrix(this, i.getItem1(), i.getItem2());
            passedMovements.add(i);
            indexForFutureMovements++;
            if (passedMovements.size() == futureMovements.size()) {
                this.setAlive(false);
                break;
            }

            moves--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }
}