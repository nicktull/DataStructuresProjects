
public class MergeSort {
    private static int comparisons = 0;

    public static int sort(String[] arr) {
        mergeSort(arr, 0, arr.length - 1);
        return comparisons;
    }

    private static void mergeSort(String[] arr, int start, int end) {
        if (start < end) {
            int mid = (start + end) / 2;
            mergeSort(arr, start, mid);
            mergeSort(arr, mid + 1, end);
            merge(arr, start, mid, end);
        }
    }

    private static void merge(String[] arr, int start, int mid, int end) {
        String[] temp = new String[arr.length];
        for (int i = start; i <= end; i++) {
            temp[i] = arr[i];
        }
        int i = start, j = mid + 1, k = start;
        while (i <= mid && j <= end) {
            if (temp[i].compareTo(temp[j]) <= 0) {
                arr[k] = temp[i];
                i++;
            } else {
                arr[k] = temp[j];
                j++;
            }
            k++;
            comparisons++;
        }
        while (i <= mid) {
            arr[k] = temp[i];
            k++;
            i++;
        }
        while (j <= end) {
            arr[k] = temp[j];
            k++;
            j++;
        }
    }
} 
