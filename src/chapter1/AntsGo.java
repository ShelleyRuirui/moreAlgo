package chapter1;

/*
 * http://poj.org/problem?id=1852
 */
public class AntsGo {
	public int[] calcMinMaxTime(int[] positions, int length) {
		int[] result = new int[2];
		int min = 0;
		int max = 0;
		for (int pos : positions) {
			min = Math.max(min, Math.min(pos, length - pos));
			max = Math.max(max, Math.max(pos, length - pos));
		}
		result[0] = max;
		result[1] = min;
		return result;
	}
	
	public static void main(String[] args) {
		AntsGo a = new AntsGo();
		int[] pos = new int[]{2, 6, 7};
		int length = 10;
		int[] res = a.calcMinMaxTime(pos, length);
		System.out.println("Max: " + res[0]);
		System.out.println("Min: " + res[1]);
	}
}
