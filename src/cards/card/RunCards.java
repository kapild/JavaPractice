package cards.card;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cards.data.DeckOfCards;
import cards.data.DeckOfNumbers;

public class RunCards {

    public static void main(String arg[]) {

        int numOfCards;

        try {

            // parse the input argument
            numOfCards = CardUtils.parseInput(arg);

            // faster version using lcm(sizeOfDisjointSet()))
            int numOfShuffle = findNumberOfIteratinFast(numOfCards);
            System.out
                    .println("Total number of iteration using Order of permuation approach: "
                            + numOfShuffle);

            /**
             * slow version of runShuffle assuming cards to be cards of poker
             * game and comparing permuted order with original order
             **/
            int numOfShuffle2 = findNumberOfIterationSlow(numOfCards);

            System.out
                    .println("Total number of iteration by comparing original order with shuffled order: "
                            + numOfShuffle2);

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println("usage: java " + RunCards.class.getName()
                    + " [positive integer]");
            System.exit(0);
        }

    }

    /**
     * Finds the total number of iteration to bring back the shuffle to original
     * order. This is the faster version. This is inspired from the fact that
     * there exists a number i, such that when the cycle (pi) when multiplied by
     * itself i times, returns an identify matrix inspired from
     * http://en.wikipedia.org/wiki/Order_(group_theory)
     * http://www.math.tamu.edu/~yvorobet/MATH433-2010B/Lect2-02web.pdf
     * http://mathworld.wolfram.com/GroupOrder.html
     * http://www.math.niu.edu/~beachy/abstract_algebra/study_guide/23.html
     * http://en.wikipedia.org/wiki/Cyclic_permutation
     * http://mathworld.wolfram.com/PermutationCycle.html
     * http://groupprops.subwiki
     * .org/wiki/Cycle_decomposition_theorem_for_permutations
     * http://groupprops.subwiki
     * .org/wiki/Order_of_permutation_is_lcm_of_cycle_sizes
     * http://math.stackexchange
     * .com/questions/231143/finding-the-order-of-permutations-in-s-8
     * http://www.proofwiki.org/wiki/Order_of_Product_of_Disjoint_Permutations
     * 
     * @param c
     * @return the number of elements in this set (its cardinality)
     * @throws Exception
     */
    public static int findNumberOfIteratinFast(int numOfCards) throws Exception {

        System.out.println("\nRunning the fast version:");

        // initialize the deck of with numOfCards cards
        DeckOfNumbers deck = new DeckOfNumbers(numOfCards);

        // perform one shuffle
        DeckOfNumbers afterShuffle = DeckOfNumbers.runOneShufflePass(deck);

        // store the reverse mapping of the cards after one shuffle.
        // values defines the cycle notation after one shuffle
        // http://en.wikipedia.org/wiki/Cycle_notation
        int[] values = new int[afterShuffle.size()];
        Set<Integer> visited = new HashSet<Integer>();

        int i = 0;
        while (afterShuffle.size() > 0) {
            // mapping of cards in cycle notation
            values[i++] = afterShuffle.removeTopCard();

        }

        // list storing the disjoint cycles length
        List<Integer> cycleSizeList = new ArrayList<Integer>();

        int k = 0;

        // iterate over all cards to generate the disjoint cycles
        for (; k < values.length; k++) {
            // if already processed
            if (!visited.contains(k)) {
                // disjoint cycle
                Set<Integer> permutationCycleSet = new HashSet<Integer>();

                generateDisjointCycle(k, visited, values, permutationCycleSet);
                // store the size
                cycleSizeList.add(permutationCycleSet.size());
            }
        }

        Integer previous = 1;
        Integer lcmNow = 0;

        // number of iteration to bring back to same permutation is the lcm
        // of length of the disjoint cycle
        // http://groupprops.subwiki
        // .org/wiki/Order_of_permutation_is_lcm_of_cycle_sizes
        for (Integer cycleSize : cycleSizeList) {
            // find lcm of size of disjoint cycles
            lcmNow = CardUtils.findLcm(previous, cycleSize);
            previous = lcmNow;
        }

        return lcmNow;
    }

    /*
     * Function finds the number of iterations to bring back the cards into
     * original order. Initially, it maintains the copy of the original order of
     * cards in originalDeck. Next, it performs the shuffle until the order of
     * the new shuffle matches the order of original shuffle. This is a slow
     * version which was initially coded.
     * 
     * @param c
     * 
     * @return the number of elements in this set (its cardinality)
     */

    public static int findNumberOfIterationSlow(int noOfcards) throws Exception {

        System.out.println("\nRunning the slow version:");

        DeckOfCards initialOrderDeck = new DeckOfCards(noOfcards);
        // clone the original deck, to maintain the initial order of cards.

        // TODO: maintain a mapping of card number and it's initial index for
        // space optimization
        DeckOfCards originalDeck = initialOrderDeck.clone();

        // aserrt they are true
        CardUtils.assertTrue((Object) initialOrderDeck,
                (Object) initialOrderDeck);

        // aserrt a new deck has same order everytime.
        DeckOfCards deck2 = new DeckOfCards(noOfcards);
        CardUtils.assertTrue(initialOrderDeck, deck2);

        int noOfIteration = 0;

        boolean isOriginalOrder = false;

        DeckOfCards afterShuffleDeck = null;

        // keep on looping until the deck after shuffle is same as the original
        // one
        while (!isOriginalOrder) {

            // run the iteration once.
            afterShuffleDeck = DeckOfCards
                    .runOneShuffleOriginal(initialOrderDeck);
            noOfIteration++;

            // just for debug
            if (noOfIteration % 100000 == 0) {
                System.out.println("debug:" + noOfIteration);
            }

            // compare the shuffle deck with orignal deck.
            // Deck overrides a equals() method.
            if (originalDeck.equals(afterShuffleDeck)) {
                isOriginalOrder = true;
            }

            // update the reference
            initialOrderDeck = afterShuffleDeck;

        }
        return noOfIteration;

    }

    // recursive function to find disjoint cycles
    public static void generateDisjointCycle(int index, Set<Integer> visited,
            int[] values, Set<Integer> permutationCycleSet) {

        // index position of the card
        int cardValue = values[index];
        // if already visited return
        if (permutationCycleSet.contains(cardValue)) {
            return;
        }

        // add the card number to the set
        permutationCycleSet.add(cardValue);
        visited.add(index);

        // call recursively at the index cardValue
        generateDisjointCycle(cardValue, visited, values, permutationCycleSet);

    }

}
