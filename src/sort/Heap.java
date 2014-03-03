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

    public Heap(int[] elemets) {
        this(elemets.length);
        for (int index = 0; index < elemets.length; index++) {
            elements.add(elemets[index]);
        }

        for (int index = elements.size() / 2; index >= 0; index--) {
            heapify(index);
        }
    }

    public int getLeftChild(int index) {
        return index * 2 + 1;
    }

    public int getRightChild(int index) {
        return getLeftChild(index) + 1;

    }

    public int getParent(int index) {
        return index / 2;

    }

    public void heapify(int pos) {

        int leftIndex = getLeftChild(pos);
        int rightIndex = getRightChild(pos);
        int maxIndex = pos;

        if (leftIndex < heapSize
                && elements.get(leftIndex) > elements.get(maxIndex)) {
            maxIndex = leftIndex;
        }

        if (rightIndex < heapSize
                && elements.get(rightIndex) > elements.get(maxIndex)) {
            maxIndex = rightIndex;
        }

        if (maxIndex != pos) {
            int temp = elements.get(pos);
            elements.set(pos, elements.get(maxIndex));
            elements.set(maxIndex, temp);
            heapify(maxIndex);
        }
    }

    private void bubbleUp(int pos) {

        if (pos > 0) {
            int parentIndex = getParent(pos);
            if (elements.get(pos) > elements.get(parentIndex)) {
                int temp = elements.get(parentIndex);
                elements.set(parentIndex, elements.get(pos));
                elements.set(pos, temp);
                bubbleUp(parentIndex);
            }
        }
    }

    public void addElement(int ele) {

        if (heapSize == elements.size()) {
            elements.add(ele);
        } else {
            elements.set(heapSize, ele);
        }
        heapSize++;
        bubbleUp(heapSize-1);
    }

    public int extractMax() {

        if (heapSize > 0) {
            int maxVal = elements.get(0);
            elements.set(0, elements.get(heapSize - 1));
            heapSize--;
            heapify(0);
            return maxVal;
        }

        throw new ArrayIndexOutOfBoundsException();
    }

    public static List<Integer> sortHeap(Heap heap) {
        List<Integer> list = new ArrayList<Integer>();
        int initialSize = heap.heapSize;
        for (int i = 0; i < initialSize; i++) {
            int maxVal = heap.elements.get(0);
            list.add(0, maxVal);
            heap.elements.set(0, heap.elements.get(heap.heapSize - 1));
            heap.elements.set(heap.heapSize - 1, maxVal);
            heap.heapSize--;
            heap.heapify(0);
        }

        return list;
    }

    public void printHeap() {
        for (int i = 0; i < heapSize; i++) {
            System.out.println(elements.get(i));
        }
    }

}
