package org.unibl.etf.game.figures;

import org.unibl.etf.game.cards.Deck;
import org.unibl.etf.game.players.Player;
import org.unibl.etf.shape.DiamondShape;
import org.unibl.etf.tools.Tuple;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;

import static sample.Game.playField;
import static sample.Game.playingDeck;

public class OrdinaryFigure extends Figure{
    private CircleShape shape;
    private int indexForFutureMovements=0;

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
   // static int indexForFutureMovements=0; //problem za sve buduce ove objekte jer je staticki
    @Override
    public  void run() { //pitanje? da li je potrebna sinhronizacija narednog bloka?

        while(this.isAlive())
        {
                try {
                    this.setNumberOfMoves(playingDeck.drawCard());
                    System.out.println(this.getNumberOfMoves() + " OVOLIKO SE puta pomjera");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                int moves = this.getNumberOfMoves();

            for (ListIterator<Tuple<Integer, Integer>> it = Figure.futureMovements.listIterator(indexForFutureMovements); it.hasNext(); ) {
                Tuple<Integer, Integer> i = it.next();
                this.getD().drawMatrix(this, i.getItem1(), i.getItem2());
                if (moves == 0) {

                    // podesiti da nije njegov red i notifyAll
                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    break;
                }
                try {
                    System.out.println("kretanje " + this.getNumberOfMoves());
                    this.getD().drawMatrix(this, i.getItem1(), i.getItem2());
                    passedMovements.add(i);
                    indexForFutureMovements++;
                    System.out.println(passedMovements.size() + " " + futureMovements.size());
                    if (passedMovements.size() == futureMovements.size()) {
                        this.setAlive(false);
                        break;
                    }
                    moves--;
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            if(!this.isAlive() )
                break;
            }

    }


}