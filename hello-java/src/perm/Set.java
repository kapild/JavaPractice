package perm;

import java.util.ArrayList;
import java.util.List;

public class Set {

    public static void main(String[] arg) {

        int[] array = new int[] { 1, 2, 3 };

        List<Integer> list = new ArrayList<Integer>();
        generateSet(list, 0, array);

    }

    public static void generateSet(List<Integer> list, int index, int[] input) {

        if (index == input.length) {

            System.out.println();
            for (Integer item : list) {
                System.out.print(":" + item + ",");
            }
            return;
        }
        List<Integer> list2 = new ArrayList<Integer>(list);

        generateSet(list2, index + 1, input);
        list2.add(input[index]);
        generateSet(list2, index + 1, input);

    }
}
