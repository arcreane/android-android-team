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

    // ... Début de l'état initiale du jeu constructeur...

    public Game() {

        //   On instanci toute les cartes du jeu
        for( int i=1; i<=13; i++ ) {
            pioche.add( new Card( Card.CardType.CARREAU, i ) );
            pioche.add( new Card( Card.CardType.COEUR, i ) );
            pioche.add( new Card( Card.CardType.PIQUE, i ) );
            pioche.add( new Card( Card.CardType.TREFLE, i ) );
        }

        //  On fait le mélange des cartes
        for( int round=0; round<200; round++ ) {
            int position = (int) ( Math.random() * pioche.size() );
            Card removedCard = pioche.elementAt( position );
            pioche.removeElementAt( position );
            pioche.add( removedCard );
        }

        // Ici il y a sept decks avec des cartes tirées de manière aléatoirement dans la pioche du jeu
        for( int deckIndex=0; deckIndex<DECK_COUNT; deckIndex++ ) {
            decks[deckIndex] = new Deck();
            for( int cardIndex = 0; cardIndex < deckIndex+1; cardIndex++ ) {
                int position = (int) ( Math.random() * pioche.size() );
                Card removedCard = pioche.elementAt( position );
                pioche.removeElementAt( position );
                decks[deckIndex].push( removedCard );
                if ( cardIndex == deckIndex ) removedCard.setReturned( true );
            }
        }

        // Puis ici on initialise les quatre stacks du jeu.
        for( int stackIndex=0; stackIndex<STACK_COUNT; stackIndex++ ) {
            stacks[stackIndex] = new Stack();
        }

    }




}