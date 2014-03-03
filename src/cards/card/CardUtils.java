package cards.card;

public class CardUtils {

    private CardUtils() {
        ;
    }

    public static int findLcm(int x1, int x2) {

        int max = x1, min = x2;
        if (x1 < x2) {
            max = x2;
            min = x1;
        }
        for (int i = 1; i <= min; i++) {
            if ((max * i) % min == 0) {
                return i * max;
            }
        }
        throw new Error("Cant find lcm");
    }

    public static int parseInput(String[] argv) throws Exception {

        if (argv.length != 1) {
            throw new IllegalArgumentException(
                    "Please provide only one input argument.");
        }

        try {
            int numOfCards = Integer.valueOf(argv[0]);
            if (numOfCards <= 0) {
                throw new IllegalArgumentException(
                        "Valid argument is a positive integer");
            }
            return numOfCards;
        } catch (NumberFormatException e) {
            throw new NumberFormatException("Invalid input numOfCards:"
                    + argv[0] + " (only positive integer values allowed)");
        }

    }

    public static boolean assertTrue(Object obj1, Object obj2) {

        if (obj1.equals(obj2)) {
            return true;
        }

        throw new AssertionError("Assert is false");

    }

}
