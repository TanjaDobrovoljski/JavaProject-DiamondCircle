package org.unibl.etf.game.figures;


import org.unibl.etf.game.cards.Deck;
import org.unibl.etf.shape.DiamondShape;
import org.unibl.etf.tools.GenLogger;
import org.unibl.etf.tools.Tuple;
import org.unibl.etf.shape.*;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Timer;



public class Figure extends Thread  implements Serializable {
    private static Integer idCounter = 1,uniqueID;
    private static int Id=1;

    protected boolean hasDiamond,alive;
    protected static List<Tuple<Integer, Integer>> futureMovements,passedMovements;
    protected  int numberOfMoves=1;
    protected int numOfDiamonds;
    private Color color ;
    protected DiamondShape dS;
    private Deck gameDeck;


    public Figure()
    {
        uniqueID=idCounter++;


        this.numOfDiamonds=0;
        this.hasDiamond=false;
        this.alive=true;

    }

    public Figure(DiamondShape ds, Deck deck)
    {

        this.numOfDiamonds=0;
        this.hasDiamond=false;
        this.dS=ds;
        this.gameDeck=deck;
        this.alive=false;
        futureMovements=(DiamondShape.random) % 2 == 0 ? ds.getMovementsEven() : dS.getMovementsOdd();
        passedMovements=new ArrayList<>();

    }

    static int k=0;
    /*@Override
    public  void  run()
    {
        for (int i = 0; i < Figure.getFutureMovements().size(); i++)
        {
            try {
                Platform.runLater(() -> {
                    DiamondShape.drawMatrix( futureMovements.get(k).getItem1(),futureMovements.get(k).getItem2() );
                    passedMovements.add(futureMovements.get(k));
                    k++;

                });

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            catch(Exception e){
                e.printStackTrace();
            }
        }

    }

    public void moveFigure()
    {
        synchronized (this) {
            for (int i = 0; i < Figure.getFutureMovements().size(); i++)
            {
                try {
                    Platform.runLater(() -> {
                        DiamondShape.drawMatrix( futureMovements.get(k).getItem1(),futureMovements.get(k).getItem2() );
                        passedMovements.add(futureMovements.get(k));
                        k++;

                    });

                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                catch(Exception e){
                    e.printStackTrace();
                }
            }
            alive=false;

        }
    }
*/

    public   Color getColor() {
        return color;
    }

    public  void setColor(Color color) {
        this.color = color;
    }

    public boolean isHasDiamond() {
        return hasDiamond;
    }

    public void setHasDiamond(boolean hasDiamond) {
        this.hasDiamond = hasDiamond;
    }

    public static List<Tuple<Integer, Integer>> getFutureMovements() {
        return futureMovements;
    }

    public static void setFutureMovements(List<Tuple<Integer, Integer>> futureMovements) {
        Figure.futureMovements = futureMovements;
    }

    public static List<Tuple<Integer, Integer>> getPassedMovements() {
        return passedMovements;
    }

    public static void setPassedMovements(List<Tuple<Integer, Integer>> passedMovements) {
        Figure.passedMovements = passedMovements;
    }

    public int getNumOfDiamons() {
        return numOfDiamonds;
    }

    public void setNumOfDiamons(int numOdDiamons) {
        this.numOfDiamonds = numOdDiamons;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Figure) && obj != null)
            return false;
        if (((Figure) obj).uniqueID == this.uniqueID)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "Figura "+uniqueID;
    }
}