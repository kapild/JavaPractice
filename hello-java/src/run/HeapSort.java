package run;

import java.util.List;

import sort.Heap;

public class HeapSort {

    public static void main(String arg[]) {

        int[] elements = new int[] { 1, 2, 3, 4, 5, 6 };

        Heap heap = new Heap(elements);

        // Heap.sortHeap(heap);

        // System.out.println(heap.extractMax());
        // System.out.println();
        heap.printHeap();
        System.out.println();
        // System.out.println(heap.extractMax());
        System.out.println();
        // heap.printHeap();

        heap.addElement(100);
        heap.printHeap();

        System.out.println(heap.extractMax());
        heap.addElement(100);

        List<Integer> sortedHeap = Heap.sortHeap(heap);
        System.out.println();

        for (int ele : sortedHeap) {
            System.out.println(ele);
        }

    }
}
