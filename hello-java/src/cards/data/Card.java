package cards.data;

/*
 * A card is composed of Suit and CardValue
 */
public class Card {

    // make the variables final
    private final Suit suit;
    private final CardValue value;

    private int deckValue = 1;
    private Integer numericaValue;

    public Card(Suit suit, CardValue value) {
        this(suit, value, 1);
    }

    public Card(Suit suit, CardValue value, int deckValue) {
        this.suit = suit;
        this.value = value;
        this.deckValue = deckValue;
    }

    public CardValue getValue() {
        return value;
    }

    public Suit getSuit() {
        return suit;
    }

    private static int getNumericValue(Suit suit, CardValue value) {
        int cardvalue = suit.ordinal() * CardValue.values().length
                + value.ordinal();
        return cardvalue;
    }

    @Override
    public String toString() {
        return "Card [suit=" + suit + ", value=" + value + ", deckValue="
                + deckValue + "]";
    }

    @Override
    public int hashCode() {
        return suit.hashCode() + 17 * value.hashCode();
    }

    /*
     * Override equals() method, returns true is same class and suit and card
     * value is the same.
     * 
     * @see java.lang.Object#equals(java.lang.Object)
     */
    @Override
    public boolean equals(Object obj) {
        if (getClass() == obj.getClass()) {
            if (obj == this) {
                return true;
            }
            Card other = (Card) obj;
            Card current = (Card) this;
            if (other.getSuit().equals(current.getSuit())
                    && other.getValue().equals(current.getValue())) {
                return true;
            }
        }
        return false;
    }

    /*
     * Numeric value of card, from [0-51]
     */
    public int getNumericaValue() {
        if (numericaValue == null) {
            numericaValue = Card.getNumericValue(suit, value);
        }
        return numericaValue;
    }

}
