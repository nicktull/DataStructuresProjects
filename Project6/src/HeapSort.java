
public class HeapSort {
    private static int heapSize;

    public static int sort(String[] array) {
        int comparisons = 0;

        // Build the heap
        buildHeap(array);

        // Sort the array using the heap
        for (int i = array.length - 1; i >= 1; i--) {
            swap(array, 0, i);
            heapSize--;
            comparisons += downheap(array, 0);
        }
        return comparisons;
    }

    private static void buildHeap(String[] array) {
        heapSize = array.length;
        for (int i = (array.length / 2) - 1; i >= 0; i--) {
            downheap(array, i);
        }
    }

    private static int downheap(String[] array, int i) {
        int comparisons = 0;
        int left = 2 * i + 1;
        int right = 2 * i + 2;
        int largest = i;

        if (left < heapSize && array[left].compareTo(array[largest]) > 0) {
            largest = left;
        }
        comparisons++;

        if (right < heapSize && array[right].compareTo(array[largest]) > 0) {
            largest = right;
        }
        comparisons++;

        if (largest != i) {
            swap(array, i, largest);
            comparisons += downheap(array, largest);
        }

        return comparisons;
    }

    private static void swap(String[] array, int i, int j) {
        String temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }
}


