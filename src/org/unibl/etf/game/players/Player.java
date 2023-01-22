package org.unibl.etf.game.players;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.List;
import org.unibl.etf.game.cards.Deck;
import org.unibl.etf.game.cards.SpecialCard;
import org.unibl.etf.game.figures.*;
import org.unibl.etf.shape.Diamond;
import org.unibl.etf.shape.DiamondShape;
import org.unibl.etf.shape.Hole;
import org.unibl.etf.tools.*;
import sample.Game;


import static sample.Game.figureCoordinates;
import static sample.Game.playingDeck;

public final class Player implements Serializable {
    public static final Set<String> names = new HashSet<String>();

    private String name;
    private static Integer idCounter = 1;
    private List<Figure> figures;
    private int numOfFigures = 0, uniqueID, figureNumber = 0,figuresFinishedGame=0;

    private Color color;
    private boolean isPlayerInTheGame = true, turn = false;
    private DiamondShape d;
    private String textPlayer;

    public boolean isTurn() {
        return turn;
    }

    public void setTurn(boolean turn) {
        this.turn = turn;
    }

    public String getColorName()
    {
        if(color.equals(Color.RED))
            return "CRVENA";
        else if(color.equals(Color.GREEN))
            return "ZELENA";
        else if(color.equals(Color.BLUE))
            return "PLAVA";
        else if(color.equals(Color.YELLOW))
            return "ŽUTA";
        return null;
    }


    public Player(String name, DiamondShape d) throws UnavaliableNameException {
        this.d = d;

       if ( !"".equals(name) && !" ".equals(name) )
           if(!names.add(name))
               throw new UnavaliableNameException();

        this.name = name;
        uniqueID = idCounter;

        if (uniqueID == 1) {
            color = Color.RED;
            idCounter++;
        } else if (uniqueID == 2) {
            color = Color.GREEN;
            idCounter++;
        } else if (uniqueID == 3) {
            color = Color.BLUE;
            idCounter++;
        } else if (uniqueID == 4) {
            color = Color.YELLOW;
            idCounter = 1;
        }

        figures = new ArrayList<>(4);
        Random random = new Random();

        extracted(random);



        //dodaj figure i uvecaj im broj igracu
    }

    private void extracted(Random random) {
        int rand;
        for (int i = 0; i < 4; i++) {
          rand = random.nextInt(4);
          // rand = 2;
            if (rand == 1)
                figures.add(new OrdinaryFigure(d, this));
            else if (rand == 2)
                figures.add(new LevitatingFigure(d, this));
            else if (rand == 3)
                figures.add(new SuperFastFigure(d, this));
            else if (rand == 0)
                i--;
        }
    }

    public String getPlayersName() {
        return name;
    }

    public void setPlayersName(String name) {
        this.name = name;
    }

    public static Integer getIdCounter() {
        return idCounter;
    }

    public static void setIdCounter(Integer idCounter) {
        Player.idCounter = idCounter;
    }

    public List<Figure> getFigures() {
        return figures;
    }

    public void setFigures(List<Figure> figures) {
        this.figures = figures;
    }

    public int getNumOfFigures() {
        return numOfFigures;
    }

    public void setNumOfFigures(int numOfFigures) {
        this.numOfFigures = numOfFigures;
    }

    public int getUniqueID() {
        return uniqueID;
    }

    public void setUniqueID(int uniqueID) {
        this.uniqueID = uniqueID;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public boolean isPlayerInTheGame() {
        return isPlayerInTheGame;
    }

    public void setPlayerInTheGame(boolean playerInTheGame) {
        isPlayerInTheGame = playerInTheGame;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Player) && obj != null)
            return false;
        if (((Player) obj).uniqueID == this.uniqueID)
            return true;
        return false;
    }

    @Override
    public String toString() {
        return "Igrac " + uniqueID + " - " + name;
    }

    public int getFiguresFinishedGame() {
        return figuresFinishedGame;
    }

    public void setFiguresFinishedGame(int figuresFinishedGame) {
        this.figuresFinishedGame = figuresFinishedGame;
    }

    public void playing() {

            if (figureNumber < 4 && figures.get(figureNumber).isFIgureAlive())
            {
                if(!Game.model.contains(figures.get(figureNumber)))
                    Game.model.addElement(figures.get(figureNumber));
                textPlayer=Game.text.getText();
                figures.get(figureNumber).setNumberOfMoves(playingDeck.drawCard());
                textPlayer=figures.get(figureNumber).toString()+"\n prelazi ";
                Game.text.append(textPlayer);
            if (figures.get(figureNumber) instanceof OrdinaryFigure)
                    ((OrdinaryFigure) figures.get(figureNumber)).makeMove();
                else if (figures.get(figureNumber) instanceof LevitatingFigure)
                    ((LevitatingFigure) figures.get(figureNumber)).makeMove();
                else if (figures.get(figureNumber) instanceof SuperFastFigure)
                    ((SuperFastFigure) figures.get(figureNumber)).makeMove();
                if (!figures.get(figureNumber).isFIgureAlive())
                {
                    if(figures.get(figureNumber).isFinished())
                        figuresFinishedGame++;
                    Tuple<Integer,Integer> last_move= figures.get(figureNumber).getPassedMovements().get(figures.get(figureNumber).getPassedMovements().size()-1);
                    figureCoordinates.remove(figures.get(figureNumber));
                    figureNumber++;
                }
            } else
                this.setPlayerInTheGame(false);
            textPlayer="";

    }



    public void figureOnHole()
    {
        if (figureNumber==4)
            figureNumber=3;
        if(figures.get(figureNumber).getPassedMovements().size()!=0 && !((figures.get(figureNumber)) instanceof levitationInterface))
        {
        Set<Hole> list=SpecialCard.getList();
          Tuple<Integer,Integer> last_move= figures.get(figureNumber).getPassedMovements().get(figures.get(figureNumber).getPassedMovements().size()-1);
        for (Hole h:list)
        {
            if (h.getPositionX().equals(last_move.getItem1()) && h.getPositionY().equals(last_move.getItem2()))
            {
                figures.get(figureNumber).setAlive(false);
               DiamondShape.getButtons()[last_move.getItem1()][last_move.getItem2()].removeAll();
               figureCoordinates.remove(figures.get(figureNumber));
                figureNumber++;
                DiamondShape.getButtons()[last_move.getItem1()][last_move.getItem2()].add(h);
                break;
            }
        }
    }

    }
}
