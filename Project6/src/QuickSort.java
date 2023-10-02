
	public class QuickSort {

	    private static int comparisons = 0;
	    
	    public static int sort(String[] arr) {
	        comparisons = 0;
	        quicksort(arr, 0, arr.length - 1);
	        return comparisons;
	    }
	    
	    private static void quicksort(String[] arr, int left, int right) {
	        if (left >= right) {
	            return;
	        }
	        
	        int pivotIndex = partition(arr, left, right);
	        quicksort(arr, left, pivotIndex - 1);
	        quicksort(arr, pivotIndex + 1, right);
	    }
	    
	    private static int partition(String[] arr, int left, int right) {
	        String pivot = arr[right];
	        int i = left - 1;
	        for (int j = left; j < right; j++) {
	            comparisons++;
	            if (arr[j].compareTo(pivot) < 0) {
	                i++;
	                swap(arr, i, j);
	            }
	        }
	        swap(arr, i + 1, right);
	        return i + 1;
	    }
	    
	    private static void swap(String[] arr, int i, int j) {
	        String temp = arr[i];
	        arr[i] = arr[j];
	        arr[j] = temp;
	    }
	    
	}

