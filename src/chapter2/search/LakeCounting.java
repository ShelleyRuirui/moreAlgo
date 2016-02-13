package chapter2.search;

/*
 * Lake counting POJ 2386
 */
public class LakeCounting {

	public int countLakes(int[][] garden) {
		if (garden.length == 0) return 0;
		int result = 0;
		for (int i = 0; i < garden.length; i ++) {
			for (int j = 0; j < garden[0].length; j ++) {
				if (garden[i][j] == 1) {
					result ++;
					dfs(garden, i, j);
				}
			}
		}
		return result;
	}
	
	private void dfs(int[][] garden, int i, int j) {
		if (garden[i][j] == 1) garden[i][j] = 0;
		for (int deltai = -1; deltai <= 1; deltai ++) {
			for (int deltaj = -1; deltaj <= 1; deltaj ++) {
				int newi = i + deltai;
				int newj = j + deltaj;
				if (isValid(garden, newi, newj))
					dfs(garden, newi, newj);
			}
		}
	}
	
	private boolean isValid(int[][] garden, int i, int j) {
		int lengthi = garden.length;
		int lengthj = garden[0].length;
		return i >= 0 && i < lengthi && j >= 0 && j < lengthj && garden[i][j] == 1;
	}
	
	public static void main(String[] args) {
		LakeCounting l = new LakeCounting();
		int[][] garden = new int[][]{
				{1,0,0,0,0,0,0,0,0,1,1,0},
				{0,1,1,1,0,0,0,0,0,1,1,1},
				{0,0,0,0,1,1,0,0,0,1,1,0},
				{0,0,0,0,0,0,0,0,0,1,1,0},
				{0,0,0,0,0,0,0,0,0,1,0,0},
				{0,0,1,0,0,0,0,0,0,1,0,0},
				{0,1,0,1,0,0,0,0,0,1,1,0},
				{1,0,1,0,1,0,0,0,0,0,1,0},
				{0,1,0,1,0,0,0,0,0,0,1,0},
				{0,0,1,0,0,0,0,0,0,0,1,0}};
		System.out.println(l.countLakes(garden));
	}
}
