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


    /**
     - On vérifie la carte si elle peut être déposé sur l'une des quatre stacks.
     * On vérifie si l'indice de la stack sur laquelle la carte peut être déposée.
     */
    public int canMoveCardToStack( Card card ) {
        // Au cas où une stack est vide et que la carte est un as on fait
        if ( card.getValue() == 1 ) {
            int stackIndex = 0;
            while( ! this.stacks[stackIndex].isEmpty() ) {
                stackIndex++;
            }
            return stackIndex;
        }

        // Au cas où ce n'est pas un as, on eput faire empiler la carte sur une carte d'une autre valeur inférieure dans l'une des piles.
        for( int stackIndex=0; stackIndex<STACK_COUNT; stackIndex++ ) {
            Stack stack = this.stacks[stackIndex];
            if ( ! stack.isEmpty() ) {
                if ( stack.lastElement().getType() != card.getType() ) continue;
                if ( stack.lastElement().getValue() == card.getValue()-1 ) return stackIndex;
            }
        }

        return -1;
    }


    /**
     - Vérifie si une carte peut être déposée sur un des sept decks.
      - Si lacard La carte à déposer.
     - Si l'indice du deck sur lequel la carte peut être déposée,
     - Et si verification -1 si ce n'est pas possible.
     */
    public int canMoveCardToDeck( Card card ) {
        // Au cas où la carte est un roi et qu'un deck est vide, alors on peut poser la carte
        if ( card.getValue() == 13 ) {
            for( int deckIndex=0; deckIndex<DECK_COUNT; deckIndex++ ) {
                if ( this.decks[deckIndex].isEmpty() ) return deckIndex;
            }
        }

        // Est-ce que la carte peut être placée sur un deck ?
        for( int deckIndex=0; deckIndex<DECK_COUNT; deckIndex++ ) {
            Deck deck = this.decks[deckIndex];
            if ( deck.size() > 0 ) {
                if ( deck.lastElement().getColor() == card.getColor() ) continue;
                if ( deck.lastElement().getValue() == card.getValue()+1 ) return deckIndex;
            }
        }

        return -1;
    }

    /**
     - On vérifie si le jeu est terminé, chaque stack doit avoir bien 13 cartes.
     - C'est vrai si le jeu est terminé, false dans le cas contraire.
     */
    public boolean isFinish() {
        return stacks[0].isEmpty() == false && stacks[0].lastElement().getValue() == 13 &&
                stacks[1].isEmpty() == false && stacks[1].lastElement().getValue() == 13 &&
                stacks[2].isEmpty() == false && stacks[2].lastElement().getValue() == 13 &&
                stacks[3].isEmpty() == false && stacks[3].lastElement().getValue() == 13;
    }

    /**
     - Ici on peut voir et savoir si toutes les cartes du jeu sont retournées et si qu'on peut lancer une terminaison automatique du jeu.
     - C'est vrai si toutes les cartes sont retournées, false dans le cas contraire.
     */
    public boolean allIsReturned() {
        for( int i=0; i<DECK_COUNT; i++ ) {
            Deck deck = decks[i];
            if ( deck.size() > 0 && deck.firstElement().isReturned() == false ) return false;
        }
        return true;
    }


}






