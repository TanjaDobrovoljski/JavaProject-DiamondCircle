package org.unibl.etf.game.cards;



import java.util.*;

public class Deck {
    private LinkedList<Card> deck;

    public Deck()
    {
        deck=new LinkedList<>();
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

    public LinkedList<Card> getDeck() {
        return deck;
    }

    public void setDeck(LinkedList<Card> deck) {
        this.deck = deck;
    }

 /*private Node head;

    public Node getHeadNode() {
        return this.head;
    }
    public class Node {
        private int data;
        private Node next;
        //constructor
        public Node(int data) { this.data = data; this.next = null; }
    }*/

}