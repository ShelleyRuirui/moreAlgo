package chapter1;

import java.util.Arrays;

/*
 * n sticks, length a0, a1, ..., ai, pick three for triangle of largest length.
 */
public class Triangle {

	// O(N^3)
	public int  pickMaxTriangle(int[] sticks) {
		int size = sticks.length;
		int result = 0;
		for (int i = 0; i < size; i ++)
			for (int j = i + 1; j < size; j ++)
				for (int k = j + 1; k < size; k ++) {
					int a = sticks[i];
					int b = sticks[j];
					int c = sticks[k];
					result = canConstructTriangle(a, b, c) ? Math.max(result, a + b + c) : result;
				}
		return result;
	}
	
	// It's easy to tell that the result sticks are neighbors in sorted sequence if any.
	// O(nlgn)
	public int pickMaxTriangle2(int[] sticks) {
		Arrays.sort(sticks);
		int result = 0;
		for (int i = sticks.length - 1; i >= 2 ; i --) {
			int a = sticks[i];
			int b = sticks[i-1];
			int c = sticks[i-2];
			result = (b + c > a) ? Math.max(result, a + b + c) : result;
		}
		return result;
	}
	
	// Negative and zero values are not taken into account
	private boolean canConstructTriangle(int a, int b, int c) {
		int total = a + b + c;
		int max = Math.max(Math.max(a, b), c);
		return total - max > max;
	}
	
	public static void main(String[] args) {
		Triangle t = new Triangle();
		int[] sticks = new int[]{2, 3, 4, 5, 10};
		System.out.println(t.pickMaxTriangle2(sticks));
		// Ensure new method is correct by comparing the result for the two methods.
		int[] randomSticks = new int[15];
		for (int i = 0; i < 50; i ++) {
			for (int j = 0; j < 15 ; j ++) {
				randomSticks[j] = (int) (Math.random()*100);
			}
			int res1 = t.pickMaxTriangle(randomSticks);
			int res2 = t.pickMaxTriangle2(randomSticks);
			System.out.println(res1 + " " + res2);
			if (res1 != res2) {
				System.err.println("Different result");
			}
		}
	}
}
