package org.unibl.etf.game.figures;

import org.unibl.etf.game.cards.Deck;
import org.unibl.etf.game.players.Player;
import org.unibl.etf.shape.DiamondShape;
import org.unibl.etf.tools.Tuple;

import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Timer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static sample.Game.figureCoordinates;

public class SuperFastFigure extends Figure {
    private SpiralShape shape;
    private int indexForFutureMovements=0,moves=0;


    public SuperFastFigure(DiamondShape d, Player p)
    {
        super(d,p);
        shape=new SpiralShape();
        shape.setColor(p.getColor());

    }

    public SpiralShape getShape() {
        return shape;
    }
    public void increaseMove()
    {
        this.moves++;
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
    public  void makeMove() throws InterruptedException { //ova metoda moze u figuru,pa da se samo za svaki child overriduje koliko prelazi


        moves = 2*this.getNumberOfMoves();
        if (moves!=0 && this.firstMove==true) {
            moves++;
            this.firstMove=false;
        }
        for (ListIterator<Tuple<Integer, Integer>> it = this.getFutureMovements().listIterator(indexForFutureMovements); it.hasNext(); ) {
            Tuple<Integer, Integer> i = it.next();
            //Tuple<Integer, Integer> prev=it.previous();
            // DiamondShape.getButtons()[i.getItem1()][i.getItem2()].setActionCommand("empty");
            if (moves == 0) {
                // setTurn(false);
                if(this.firstMove==true)
                    break;
                if(!figureCoordinates.containsValue(i) )
                {
                    figureCoordinates.put(this,i);
                    break;
                }
                else
                {
                    for (Map.Entry<Figure, Tuple<Integer, Integer>> entry : figureCoordinates.entrySet())
                    {
                        if (entry.getValue().equals(i))
                        {
                            var key = entry.getKey();
                            if (this.getUniqueID().equals(key.getUniqueID()))
                                break;
                            else
                                moves++;
                        }
                    }
                }
            }
            if (moves==0)
                break;

            this.getD().drawMatrix(this, i.getItem1(), i.getItem2());

            passedMovements.add(i);
            indexForFutureMovements++;


            if (passedMovements.size() == futureMovements.size()) {
                this.setAlive(false);
                this.finished=true;
                DiamondShape.getButtons()[passedMovements.get(passedMovements.size()-1).getItem1()][passedMovements.get(passedMovements.size()-1).getItem2()].removeAll();
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