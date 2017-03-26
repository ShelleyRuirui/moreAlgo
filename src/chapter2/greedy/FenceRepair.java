package chapter2.greedy;

/**
 * http://poj.org/problem?id=3253
 */
public class FenceRepair {
    public static int minimumCost(int[] lengths) {
        int cost = 0;
        int N = lengths.length - 1;
        while (N >= 1) {
            int[] mins = findMins(lengths, N);
            int newLength = lengths[mins[0]] + lengths[mins[1]];
            cost += newLength;
            lengths[mins[0]] = newLength;
            if (mins[1] != N) {
                lengths[mins[1]] = lengths[N];
            }
            N --;
        }
        return cost;
    }

    // Find min indexes
    private static int[] findMins(int[] lengths, int N) {
        int min1 = Math.min(lengths[0], lengths[1]);
        int min2 = Math.max(lengths[0], lengths[1]);
        int min1Index = lengths[0] < lengths[1] ? 0 : 1;
        int min2Index = lengths[0] < lengths[1] ? 1 : 0;
        for (int i = 2; i <= N; i ++) {
            if (lengths[i] < min1) {
                min1 = lengths[i];
                min2 = min1;
                min2Index = min1Index;
                min1Index = i;
            } else if (lengths[i] < min2) {
                min2 = lengths[i];
                min2Index = i;
            }
        }
        return new int[]{Math.min(min1Index, min2Index), Math.max(min1Index, min2Index)};
    }

    public static void main(String[] args) {
        int[] lengths = new int[] {8, 5, 8};
        int[] lengths2 = new int[] {1, 2, 3, 4, 5};
        System.out.println(minimumCost(lengths));
        System.out.println(minimumCost(lengths2));
    }
}
