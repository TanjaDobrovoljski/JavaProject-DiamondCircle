package org.unibl.etf.game.figures;

import org.unibl.etf.game.cards.Deck;
import org.unibl.etf.game.cards.SpecialCard;
import org.unibl.etf.game.players.Player;
import org.unibl.etf.shape.DiamondShape;
import org.unibl.etf.tools.GenLogger;
import org.unibl.etf.tools.Tuple;
import sample.Game;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.*;
import java.util.Timer;

import static sample.Game.figureCoordinates;

public class OrdinaryFigure extends Figure {
    private CircleShape shape;
    private int indexForFutureMovements=0,moves=0,tmpMoves=0,flagEnd = 0,mov,flagBeg,flagFirstMovement=0;
    private Tuple<Integer, Integer> k=null;
    private ListIterator<Tuple<Integer, Integer>> tmp=null;
    private JTextField textField;
    private String textFigure="",tmpText="",tmpTextEnd="";

    public CircleShape getShape() {
        return shape;
    }

    public void setShape(CircleShape shape) {
        this.shape = shape;
    }

    public int getMoves() {
        return moves;
    }

    public void increaseMove()
    {
        flagEnd=0;
        flagBeg=0;
        this.moves++;
        this.mov++;
        tmpMoves=moves-1;
    }

    public void setMoves(int moves) {
        this.moves = moves;
    }

    public OrdinaryFigure(DiamondShape d, Player p)
    {
        super(d,p);
        shape=new CircleShape();
        shape.setForeground(p.getColor());

    }

    @Override
    public  void makeMove()  { //ova metoda moze u figuru,pa da se samo za svaki child overriduje koliko prelazi
        flagBeg = 0; flagEnd=0;
        flagFirstMovement=0;
        Tuple<Integer, Integer> last_move = null;
        int tempMove = indexForFutureMovements;
        tmpText=Game.text.getText();
        moves = this.getNumberOfMoves();
        mov=moves;
        if (moves != 0 && this.firstMove == true) {
            moves++;
            this.firstMove = false;
        }


        for (ListIterator<Tuple<Integer, Integer>> it = this.getFutureMovements().listIterator(indexForFutureMovements); it.hasNext(); ) {
            Tuple<Integer, Integer> i = it.next();
            //Tuple<Integer, Integer> prev=it.previous();
            // DiamondShape.getButtons()[i.getItem1()][i.getItem2()].setActionCommand("empty");
            if (moves == 0) {
                // setTurn(false);
                if (this.firstMove == true)
                {
                    Game.text.removeAll();
                    Game.text.setText(tmpText);
                    Game.text.append(" 0 pozicija");
                    break;
                }
                if (!figureCoordinates.containsValue(i)) {
                    figureCoordinates.put(this, i);
                    break;
                } else {
                    for (Map.Entry<Figure, Tuple<Integer, Integer>> entry : figureCoordinates.entrySet()) {
                        if (entry.getValue().equals(i)) {
                            var key = entry.getKey();
                            if (this.getUniqueID().equals(key.getUniqueID()))
                                break;
                            else {
                                moves++;
                                this.mov++;
                                flagEnd = 0;
                                flagBeg=0;
                            }
                        }
                    }
                }
            }
            if (moves == 0)
                break;

            this.getD().drawMatrix(this, i.getItem1(), i.getItem2());

            passedMovements.add(i);
            indexForFutureMovements++;


            if (passedMovements.size() == futureMovements.size()) {
                this.setAlive(false);
                this.finished = true;
                DiamondShape.getButtons()[passedMovements.get(passedMovements.size() - 1).getItem1()][passedMovements.get(passedMovements.size() - 1).getItem2()].removeAll();
                break;
            }
            if (flagBeg == 0 ) {

                if(flagFirstMovement==0) {
                    if (this.getPassedMovements().size() == 1)
                        last_move = this.getPassedMovements().get(this.getPassedMovements().size() - 1);
                    else
                        last_move = this.getPassedMovements().get(this.getPassedMovements().size() - 2);
                    flagFirstMovement=1;
                }
                Game.text.removeAll();
                Game.text.setText(tmpText);

                Game.text.append(mov+" polja, pomjera se sa\npozicije " + DiamondShape.getButtons()[last_move.getItem1()][last_move.getItem2()].getText());
                tmpTextEnd=Game.text.getText();
                flagBeg = 1;
            }

            if (flagEnd == 0) {

                for (tmp = this.getFutureMovements().listIterator(tempMove); tmpMoves != moves && tmp.hasNext(); k = tmp.next())
                { tmpMoves++;
                    tempMove++; }

                 Game.text.removeAll();
                 Game.text.setText(tmpTextEnd);
                Game.text.append(" na " + DiamondShape.getButtons()[k.getItem1()][k.getItem2()].getText());
                flagEnd = 1;
                tmpMoves=0;


            }

            moves--;
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                GenLogger.log(OrdinaryFigure.class,e);
            }


        }


    }
    @Override
    public String toString() {
        return super.toString()+" (obična figura,";
    }

}

