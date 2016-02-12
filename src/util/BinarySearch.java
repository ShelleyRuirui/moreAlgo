package util;

public class BinarySearch {

	// Search target in array. Array should be in acsending order.
	public static int binarySearch(int[] array, int target) {
		int left = 0;
		int right = array.length - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			if (array[mid] == target) {
				return mid;
			} else if (array[mid] < target) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return -1;
	}
	
	public static void main(String[] args) {
		int[] array = new int[]{1,3,4,5,6};
		int target = 6;
		System.out.println(BinarySearch.binarySearch(array, target));
	}
}
