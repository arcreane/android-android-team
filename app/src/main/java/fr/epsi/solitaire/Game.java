package fr.epsi.solitaire;

import java.io.Serializable;
import java.util.Vector;


public class Game implements Serializable {

    public static class Stack extends java.util.Stack<Card> {}
    public static class Deck extends java.util.Stack<Card> {}


    public static final int STACK_COUNT = 4;
    public static final int DECK_COUNT = 7;

    public Stack [] stacks = new Stack[STACK_COUNT];
    public Deck [] decks = new Deck[DECK_COUNT];
    public Vector<Card> pioche = new Vector<>();
    public Vector<Card> returnedPioche = new Vector();

}