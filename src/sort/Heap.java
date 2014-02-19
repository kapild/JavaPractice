package sort;

import java.util.ArrayList;
import java.util.List;

public class Heap {

    List<Integer> elements;
    int heapSize = 0;

    public Heap(int heapSize) {
        elements = new ArrayList<Integer>(heapSize);
        this.heapSize = heapSize;
    }
}
