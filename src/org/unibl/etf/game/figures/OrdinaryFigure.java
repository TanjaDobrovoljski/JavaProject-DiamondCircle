package org.unibl.etf.game.figures;

import org.unibl.etf.game.cards.Deck;
import org.unibl.etf.game.players.Player;
import org.unibl.etf.shape.DiamondShape;
import org.unibl.etf.tools.Tuple;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.ListIterator;
import java.util.TimerTask;
import java.util.Timer;

import static sample.Game.playField;
import static sample.Game.playingDeck;

public class OrdinaryFigure extends Figure {
    private CircleShape shape;
    private int indexForFutureMovements=0,moves;
    private Timer timer;

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
        timer=new Timer();
    }

    @Override
    public  void makeMove() { //ova metoda moze u figuru,pa da se samo za svaki child overriduje koliko prelazi

        moves = this.getNumberOfMoves();
        if (moves!=0 && this.firstMove==true) {
            moves++;
            this.firstMove=false;
        }
                   for (ListIterator<Tuple<Integer, Integer>> it = this.getFutureMovements().listIterator(indexForFutureMovements); it.hasNext(); ) {
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

