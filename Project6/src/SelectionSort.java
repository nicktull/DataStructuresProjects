
public class SelectionSort {
	
	public static int selectionSort(String selectList[]) { //Might need to pass a String array as a parameter.
		int comparison = 0;
		int n = selectList.length;
		for(int i = 0; i < n-1; i++) {
			int minIndex = i;
			for(int j = i + 1; j < n; j++) {
				comparison++;
				if(selectList[j].compareTo(selectList[minIndex]) > 0) {
					minIndex = j;
				}
				
			}
			String temp = selectList[i];
            selectList[i] = selectList[minIndex];
            selectList[minIndex] = temp;
		}
		return comparison;
	}
}
