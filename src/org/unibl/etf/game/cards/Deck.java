package org.unibl.etf.game.cards;

import org.unibl.etf.tools.CircularArrayList;

import java.nio.Buffer;
import java.util.*;

public class Deck {
    private static CircularArrayList<Card> deck;
    private Buffer buf;
    private static int numofDraw=0;

    public Deck()
    {
        deck=new CircularArrayList<Card>(52);
        for (int i=0;i<10;i++) {
            deck.add(new OrdinaryCard(1));
            deck.add(new OrdinaryCard(2));
            deck.add(new OrdinaryCard(3));
            deck.add(new OrdinaryCard(4));
            deck.add(new SpecialCard());
        }
        deck.add(new SpecialCard());
        deck.add(new SpecialCard());

        Collections.shuffle(deck);
    }

    public CircularArrayList<Card> getDeck() {
        return deck;
    }

    public void setDeck(CircularArrayList<Card> deck) {
        this.deck = deck;
    }

    public synchronized static Card drawCard()
    {
        Card tmp=deck.get(numofDraw);
        numofDraw++;
        System.out.println(deck.capacity()+" velicina");
        return tmp;
    }
}