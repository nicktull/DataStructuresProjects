
public class InsertionSort {

	public static int insertionSort(String arr[]) {    //Might need to pass a String array as a parameter.
		int comparison = 0;
		int n = arr.length;
		for(int i = 1; i < n; i++) {
			String key = arr[i];
			int j = i - 1;
			while(j >= 0 && arr[j].compareTo(key) > 0) {
				comparison++;
				arr[j+1] = arr[j];
				j = j - 1;
			}
			arr[j + 1] = key;
		}
		return comparison;
	}
}
//Updated