package org.unibl.etf.game.figures;


import org.unibl.etf.game.cards.Deck;
import org.unibl.etf.game.players.Player;
import org.unibl.etf.shape.DiamondShape;
import org.unibl.etf.tools.GenLogger;
import org.unibl.etf.tools.Tuple;
import org.unibl.etf.shape.*;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Figure  implements Serializable {
    private static Integer idCounter = 1;
    private int uniqueID;
    protected boolean alive,finished=false,firstMove;
    protected  List<Tuple<Integer, Integer>> futureMovements;
    protected  List<Tuple<Integer, Integer>> passedMovements;
    protected  int numberOfMoves=1;
    protected int numOfDiamonds=0;
    protected Player player;


    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean turn) {
        this.finished = turn;
    }

    private DiamondShape d;

    public static Integer getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(Integer idCounter) {
        Figure.idCounter = idCounter;
    }

    public Integer getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(Integer uniqueID) {
        this.uniqueID = uniqueID;
    }


    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    public DiamondShape getD() {
        return d;
    }

    public void setD(DiamondShape d) {
        this.d = d;
    }

    public int getNumberOfMoves() {
        return numberOfMoves;
    }

    public void setNumberOfMoves(int numberOfMoves) {
        this.numberOfMoves = numberOfMoves;
    }

    public int getNumOfDiamonds() {
        return numOfDiamonds;
    }

    public void setNumOfDiamonds(int numOfDiamonds) {
        this.numOfDiamonds = numOfDiamonds;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public String getFigureName() {
        return figureName;
    }

    public void setFigureName(String figureName) {
        this.figureName = figureName;
    }

    public boolean isFIgureAlive() {
        return alive;
    }


    protected String figureName;

    public Figure(DiamondShape d,Player p)
    {
        uniqueID=idCounter++;
        figureName="Figura "+idCounter;
        this.numOfDiamonds=0;
        this.alive=true;
        this.player=p;
        this.d=d;
        this.firstMove=true;
        futureMovements=d.movements();
        passedMovements=new ArrayList<>();

    }

    public  List<Tuple<Integer, Integer>> getFutureMovements() {
        return futureMovements;
    }

    public  void setFutureMovements(List<Tuple<Integer, Integer>> futureMovements) {
        this.futureMovements = futureMovements;
    }

    public  List<Tuple<Integer, Integer>> getPassedMovements() {
        return passedMovements;
    }

    public  void setPassedMovements(List<Tuple<Integer, Integer>> passedMovements) {
        this.passedMovements = passedMovements;
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

    public abstract void makeMove() throws InterruptedException;
}