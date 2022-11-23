package org.unibl.etf.game.players;

import java.awt.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;
import java.util.List;


import org.unibl.etf.game.figures.*;
import org.unibl.etf.tools.*;

public class Player {
    public static final Set<String> names=new HashSet<String>();
    private String name;
    private static Integer idCounter = 1;
    private List<Figure> figures=new ArrayList<Figure>();
    private int numOfFigures=0,uniqueID;
    private Color color;
    private boolean isPlayerInTheGame;

    static
    {
        try {
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("IGRA_"+ LocalDate.now()+".txt"));
            bufferedWriter.write("");
            bufferedWriter.close();
        }
        catch (IOException ioException){
            GenLogger.log(Player.class, ioException);
        }
    }

    public Player(String name) throws Exception
    {
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

        //dodaj figure i uvecaj im broj igracu
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
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
}