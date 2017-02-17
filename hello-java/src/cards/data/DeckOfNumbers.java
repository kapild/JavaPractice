package cards.data;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

public class DeckOfNumbers {

    // doubly LinkList
    private final Deque<Integer> deck;

    // /initialize the list
    public DeckOfNumbers() {
        deck = new LinkedList<Integer>();
    }

    // initialize a deck with numOfCards

    public DeckOfNumbers(int numOfCards) {

        // deck is nothing but cards from 0,1,2,3 to numOfCards - 1
        deck = new LinkedList<Integer>();
        for (int i = 0; i < numOfCards; i++) {
            deck.add(i);
        }
    }

    public int size() {
        return deck.size();
    }

    // remove from teh top of Deck
    public Integer removeTopCard() throws Exception {

        if (size() > 0) {
            Integer card = deck.pop();
            return card;

        }

        // can extends own Exception
        throw new Exception("Deck is Empty");
    }

    // add on top of deck with cards facing down.
    public void addCardOnTop(Integer card) {
        deck.addFirst(card);
    }

    public void addCardOnBack(Integer card) {
        deck.addLast(card);
    }

    // do one iteration of the deck as in the problem statement
    public static DeckOfNumbers runOneShufflePass(DeckOfNumbers handDeck)
            throws Exception {

        // deck on the table
        DeckOfNumbers tableDeck = new DeckOfNumbers();

        // while the deck in hand is empty
        while (handDeck.size() > 0) {
            // remove the top
            Integer tableCard = handDeck.removeTopCard();

            // add the card on the table on the deck
            tableDeck.addCardOnTop(tableCard);

            // check if the handdeck is not empty
            if (handDeck.size() > 0) {
                // remove the top card
                Integer backCard = handDeck.removeTopCard();
                // add the card to the back of hand deck
                handDeck.addCardOnBack(backCard);
            }
        }

        handDeck = null;
        return tableDeck;
    }

    public void printDeckValue() {

        System.out.println("Printing deck***");

        for (Integer card : deck) {
            System.out.println(card);
        }
        System.out.println("****");

    }

    public void printDeck() {

        System.out.println("Printing deck***");
        int i = 0;
        for (Integer card : deck) {
            System.out.println(++i + "\t" + card);
        }
        System.out.println("****");

    }

    @Override
    public int hashCode() {
        return deck.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (getClass() == obj.getClass()) {
            if (obj == this) {
                return true;
            }
            DeckOfNumbers other = (DeckOfNumbers) obj;
            DeckOfNumbers current = (DeckOfNumbers) this;
            if (other.size() != current.size()) {
                return false;
            }
            Iterator<Integer> itFirst = current.deck.iterator();
            Iterator<Integer> itSecond = other.deck.iterator();

            while (itFirst.hasNext() && itSecond.hasNext()) {
                Integer cardCurrent = itFirst.next();
                Integer cardOther = itSecond.next();
                if (!cardCurrent.equals(cardOther)) {
                    return false;
                }
            }
            // at this point it matches
            return true;
        }
        return false;
    }
}
