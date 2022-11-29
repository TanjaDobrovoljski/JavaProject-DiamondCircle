package org.unibl.etf.game.players;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;
import java.util.List;


import org.unibl.etf.game.cards.Deck;
import org.unibl.etf.game.figures.*;
import org.unibl.etf.shape.Diamond;
import org.unibl.etf.shape.DiamondShape;
import org.unibl.etf.shape.Hole;
import org.unibl.etf.tools.*;
import static sample.Game.playField;

public class Player extends Thread implements Serializable {
    public static final Set<String> names=new HashSet<String>();
    private String name;
    private static Integer idCounter = 1;
    private List<Figure> figures;
    private int numOfFigures=0,uniqueID;
    private Color color;
    private boolean isPlayerInTheGame=true;
    private  DiamondShape d;


  /*  static
    {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("IGRA_"+ LocalDate.now()+".txt"));
            bufferedWriter.write("");
            bufferedWriter.close();
        }
        catch (IOException ioException){
            GenLogger.log(Player.class, ioException);
        }
    }*/

    public Player(String name, DiamondShape d) throws Exception
    {
        this.d=d;
        if (!names.add(name))
            throw new UnavaliableNameException();
        this.name = name;
        uniqueID=idCounter;

        if(uniqueID==1)
        { color=Color.RED;
            idCounter++;
        }
        else if(uniqueID==2) {
            color = Color.GREEN;
            idCounter++;
        }
        else if(uniqueID==3) {
            color = Color.BLUE;
            idCounter++;
        }
        else if(uniqueID==4) {
            color = Color.YELLOW;
            idCounter = 1;
        }

        figures=new ArrayList<>(4);
        Random random=new Random();

        extracted(random);

        //dodaj figure i uvecaj im broj igracu
    }

    private void extracted(Random random) {
        int rand;
        for(int i = 0; i<4; i++)
        {
            rand = random.nextInt(4);
            if(rand ==1)
                figures.add(new OrdinaryFigure(d,this));
            else if(rand ==2)
                figures.add(new LevitatingFigure(d,this));
            else if(rand ==3)
                figures.add(new SuperFastFigure(d,this));
            else if(rand==0)
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
        return "Igrac "+uniqueID+" - "+name;
    }

    @Override
    public void run()
    {
                try {
                    getFigures().get(0).start();

                    getFigures().get(0).join();
                    getFigures().get(1).start();


                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
    }
}