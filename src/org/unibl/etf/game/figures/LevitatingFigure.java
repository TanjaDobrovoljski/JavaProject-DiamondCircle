package org.unibl.etf.game.figures;

import org.unibl.etf.game.cards.Deck;
import org.unibl.etf.game.players.Player;
import org.unibl.etf.shape.DiamondShape;
import org.unibl.etf.tools.Tuple;

import java.io.IOException;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Timer;

import static sample.Game.figureCoordinates;
import static sample.Game.playingDeck;

public class LevitatingFigure extends Figure implements levitationInterface{
    private StarShape shape;
    private int indexForFutureMovements=0,moves=0;

    public LevitatingFigure(DiamondShape d, Player p)
    {
        super(d,p);
        shape=new StarShape();
        shape.setForeground(p.getColor());

    }

    public StarShape getShape() {
        return shape;
    }
    public void increaseMove()
    {
        this.moves++;
    }

    public void setShape(StarShape shape) {
        this.shape = shape;
    }

    @Override
    public  void makeMove() throws InterruptedException { //ova metoda moze u figuru,pa da se samo za svaki child overriduje koliko prelazi


        moves = this.getNumberOfMoves();
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