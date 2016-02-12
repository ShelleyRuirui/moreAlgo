package chapter1;

import java.util.Arrays;

import util.BinarySearch;

/*
 * Judge if four numbers in an array can sum up to K. The numbers can be retrieved multiple times.
 */
public class Lot {

	// O(n^2lgn)
	public boolean judge(int[] array, int K) {
		int length = array.length;
		int[] twoSums = new int[length * length];
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				twoSums[i * length + j] = array[i] + array[j];
			}
		}
		Arrays.sort(twoSums);
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				int sum = array[i] + array[j];
				if (BinarySearch.binarySearch(twoSums, K - sum) >= 0)
					return true;
			}
		}
		return false;
	}
	
	public static void main(String[] args) {
		Lot lot = new Lot();
		int[] array = new int[]{1, 3, 5};
		int K = 11;
		System.out.println(lot.judge(array, K));
	}
}
