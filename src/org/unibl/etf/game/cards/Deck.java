package org.unibl.etf.game.cards;

import org.unibl.etf.shape.DiamondShape;
import org.unibl.etf.tools.CircularArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.util.*;

public class Deck{
    private static CircularArrayList<Card> deck,tmp;
    private Buffer buf;
    private int numofDraw = 0,holeFlag=0;

    public Deck() {

        deck = new CircularArrayList<Card>(52);
        for (int i = 0; i < 10; i++) {
            deck.add(new OrdinaryCard(1));
            deck.add(new OrdinaryCard(2));
            deck.add(new OrdinaryCard(3));
            deck.add(new OrdinaryCard(4));
            deck.add(new SpecialCard());
        }
        deck.add(new SpecialCard());
        deck.add(new SpecialCard());

        Collections.shuffle(deck);
        tmp=deck;
    }

    public CircularArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(CircularArrayList<Card> deck) {
        this.deck = deck;
    }

    public  int drawCard() throws InterruptedException {

        if (numofDraw == deck.capacity()-1)
        { numofDraw = 0;
            deck=tmp;}
        System.out.println(numofDraw +" "+deck.capacity());
        Card tmp = deck.get(numofDraw);
        numofDraw++;

        if (tmp instanceof  OrdinaryCard)
        {
            if(holeFlag==1)

            {
                Thread.sleep(1000);
                SpecialCard.removeHoles();
                holeFlag=0;

            }

            return ((OrdinaryCard) tmp).getNumOfFields();
    }
        else
        {
            holeFlag=1;
            ((SpecialCard) tmp).setHoles();
            return 0;
        }
    }
    public Card image()
    {
        return deck.get(numofDraw);
    }
}