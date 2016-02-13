package chapter2.search;

/*
 * Given a1, a2, ..., an, judge whether can pick subset of them with sum k
 */
public class SubsetSum {

	public boolean canFindSubset(int[] array, int k) {
		return search(array, 0, k);
	}
	
	private boolean search(int[] array, int pos, int k) {
		if (pos == array.length) return k == 0;
		return search(array, pos + 1, k) || search(array, pos + 1, k - array[pos]);
	}

	// Use bit to represent whether an element is contained in the set.
	public boolean canFindSubset2(int[] array, int k) {
		for (int i = 0; i < Math.pow(2, array.length); i ++) {
			int sum = 0;
			int current = i;
			for (int j = 0; j < 32; j ++) {
				if ((current & 1) == 1) {
					sum += array[j];
				}
				current = current >> 1;
			}
			if (sum == k) return true;
		}
		return false;
	}

	public static void main(String[] args) {
		SubsetSum s = new SubsetSum();
		int[] array = new int[]{1, 2, 4, 7};
		int k = 13;
		System.out.println(s.canFindSubset2(array, k));
	}
}
