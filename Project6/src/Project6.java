import java.util.Arrays;
import java.util.Scanner;

public class Project6 {
	static String[] inputList;
	
	public static void main(String[] args) {
		System.out.println("Input the number of words to be sorted: ");
		Scanner scanner = new Scanner(System.in);
		String wordCount = scanner.nextLine(); //System.out.println(numOfWords);
		int numOfWord = Integer.parseInt(wordCount);
		System.out.println("Input the words to be sorted: ");
		inputList = new String[numOfWord];
        for(int i = 0; i < numOfWord; i++) {
            	inputList[i] = scanner.nextLine();
            }
		
        long start, end, duration;
        
        //Selection Sort Algorithm
        String[] selectList = Arrays.copyOf(inputList, inputList.length);
        start = System.currentTimeMillis();
        int selectListNum = SelectionSort.selectionSort(selectList);
        end = System.currentTimeMillis();
        duration = end - start;
        System.out.println("ALGORITHM       | Comparisons | Time (Milliseconds) ");
        System.out.println("================+=============+======================");
        System.out.println("Selection Sort  | " + selectListNum + "        | " + (double) duration);
        System.out.println("----------------+-------------+----------------------");
        
        //Insertion Sort Algorithm
        String[] insertionList = Arrays.copyOf(inputList, inputList.length);
        start = System.currentTimeMillis();
        int insertionListNum = InsertionSort.insertionSort(insertionList);
        end = System.currentTimeMillis();
        duration = end - start;
        System.out.println("Insertion Sort  | " + insertionListNum + "        | " + (double) duration);
        System.out.println("----------------+-------------+----------------------");
        
        //Heap Sort Algorithm
        String[] heapList = Arrays.copyOf(inputList, inputList.length);
        start = System.currentTimeMillis();
        int heapListSum = HeapSort.sort(heapList);
        end = System.currentTimeMillis();
        duration = end - start;
        System.out.println("Heap Sort       | " + heapListSum + "        | " + (double) duration);
        System.out.println("----------------+-------------+----------------------");
        
        //Merge Sort Algorithm
        String[] mergeList = Arrays.copyOf(inputList, inputList.length);
        start = System.currentTimeMillis();
        int mergeListNum = MergeSort.sort(mergeList);
        end = System.currentTimeMillis();
        duration = end - start;
        System.out.println("Merge Sort      | " + mergeListNum + "         | " + (double) duration);
        System.out.println("----------------+-------------+----------------------");
        
        //Quick Sort Algorithm
        String[] quickList = Arrays.copyOf(inputList, inputList.length);
        start = System.currentTimeMillis();
        int quickListNum = QuickSort.sort(quickList);
        end = System.currentTimeMillis();
        duration = end - start;
        System.out.println("Quicksort       | " + quickListNum + "         | " +  (double) duration);
        System.out.println("----------------+-------------+----------------------");
        
        
		scanner.close();
	}
	
}
