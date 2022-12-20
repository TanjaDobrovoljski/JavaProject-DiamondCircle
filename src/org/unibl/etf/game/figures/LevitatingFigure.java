package org.unibl.etf.game.figures;

import org.unibl.etf.game.cards.Deck;
import org.unibl.etf.game.players.Player;
import org.unibl.etf.shape.DiamondShape;
import org.unibl.etf.tools.Tuple;

import java.io.IOException;
import java.util.List;

import static sample.Game.playingDeck;

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

    public  void run() { //pitanje? da li je potrebna sinhronizacija narednog bloka?
        synchronized (this) {
            this.setTurn(true);
            try {
                this.setNumberOfMoves(playingDeck.drawCard());
                System.out.println(this.getNumberOfMoves() + " OVOLIKO SE puta pomjera");
            } catch (IOException e) {
                e.printStackTrace();
            }
            int moves = this.getNumberOfMoves();

            for (Tuple<Integer, Integer> i : Figure.futureMovements) {
                this.getD().drawMatrix(this, i.getItem1(), i.getItem2());
                if (moves == 0) {
                    this.setTurn(false);
                    notifyAll();
                    break;
                }
                try {
                    System.out.println("kretanje " + this.getNumberOfMoves());
                    this.getD().drawMatrix(this, i.getItem1(), i.getItem2());
                    passedMovements.add(i);
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

        }
    }
}