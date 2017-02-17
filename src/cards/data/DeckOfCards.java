package cards.data;

import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;

/*
 * DeckOfCards for a card game like Poker. It contains a list of Cards.
 * #number of card is provided as an input
 */
public class DeckOfCards {

    // an doubly LinkList for adding and deleting at both ends.
    private Deque<Card> deck = new LinkedList<Card>();;

    public DeckOfCards() {
        deck = new LinkedList<Card>();
    }

    // add a Card to the deck, by default it is added at the bottom of the deck,
    // like a Queue and not a stack, viewing the deck from the top
    public void addCard(Card card) {
        deck.add(card);
    }

    // this function is biased that it will return the same order of cards for
    // same number of cards. Some randomness can be added.
    public DeckOfCards(int numOfCards) {

        int deckLenght = Suit.values().length * CardValue.values().length;
        deck = new LinkedList<Card>();
        int i = 0;
        while (true) {
            for (Suit suit : Suit.values()) {
                for (CardValue value : CardValue.values()) {

                    // deck value just for info purpose, not used in equals();
                    int deckVale = i / deckLenght + 1;

                    if (i++ < numOfCards) {
                        Card card = new Card(suit, value, deckVale);
                        deck.add(card);
                    } else {
                        return;
                    }
                }
            }
        }
    }

    /*
     * returns a clone of the deck is the same order as the present order of the
     * cards, this is required to compare the original order with the new order
     * when suffling is done. Another way to implement this is to create a
     * mapping of Card() object and its index in the map. (non-Javadoc)
     * 
     * @see java.lang.Object#clone()
     */
    @Override
    public DeckOfCards clone() {

        DeckOfCards newDeck = new DeckOfCards();
        for (Card card : deck) {
            Card newCard = new Card(card.getSuit(), card.getValue());
            newDeck.addCard(newCard);
        }

        return newDeck;
    }

    public int size() {
        return deck.size();
    }

    /*
     * Remove the top Card from the Deck
     */
    public Card removeTopCard() throws Exception {
        if (size() > 0) {
            Card card = deck.pop();
            return card;

        }

        throw new Exception("Deck is Empty");
    }

    /*
     * Adds a new card on the top of the deck. The cards are placed upside down
     */
    public void addCardOnTop(Card card) {
        deck.addFirst(card);
    }

    /*
     * Adds a new card at the back of the deck.
     */
    public void addCardOnBack(Card card) {
        deck.addLast(card);

    }

    /*
     * Just prints the deck with its value
     */
    public void printDeckValue() {

        System.out.println("Printing deck***");

        int i = 0;
        for (Card card : deck) {
            System.out.println(i++ + ":" + card.getNumericaValue());
        }
        System.out.println("****");

    }

    public void printDeck() {

        System.out.println("Printing deck***");
        int i = 0;
        for (Card card : deck) {
            System.out.println(i++ + "\t" + card);
        }
        System.out.println("****");

    }

    @Override
    public int hashCode() {
        return deck.hashCode();
    }

    /*
     * Compare if two decks are equal. First compare the size, then compare each
     * element in the deck. (non-Javadoc)
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (getClass() == obj.getClass()) {
            if (obj == this) {
                return true;
            }
            DeckOfCards other = (DeckOfCards) obj;
            DeckOfCards current = (DeckOfCards) this;
            if (other.size() != current.size()) {
                return false;
            }
            Iterator<Card> itFirst = current.deck.iterator();
            Iterator<Card> itSecond = other.deck.iterator();

            while (itFirst.hasNext() && itSecond.hasNext()) {
                Card cardCurrent = itFirst.next();
                Card cardOther = itSecond.next();
                if (!cardCurrent.equals(cardOther)) {
                    return false;
                }
            }
            // at this point it matches
            return true;
        }
        return false;
    }

    public Card getCard(int index) {

        if (index < size()) {
            Iterator<Card> it = deck.iterator();
            int i = 0;
            while (it.hasNext()) {
                Card card = it.next();
                if (i == index) {
                    return card;
                }
                i++;
            }
        }
        return null;
    }

    public static DeckOfCards runOneShuffleOriginal(DeckOfCards deck)
            throws Exception {

        DeckOfCards tableDeck = new DeckOfCards();

        while (deck.size() > 0) {
            Card tableCard = deck.removeTopCard();
            tableDeck.addCardOnTop(tableCard);
            if (deck.size() > 0) {
                Card backCard = deck.removeTopCard();
                deck.addCardOnBack(backCard);
            }
        }

        deck = null;
        return tableDeck;
    }

}
