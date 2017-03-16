package chapter2.greedy;

/**
 * Created by luruirui on 17-3-16.
 */
public class CoinSum {
    private static int[] coinValus = new int[] {500, 100, 50, 10, 5, 1};

    // Get the minimum number of coins whose values sum up to totalSum
    public static int getMinNumberOfCoins(int totalSum) {
        int result = 0;
        int leftValue = totalSum;
        for (int value : coinValus) {
            result += leftValue / value;
            leftValue = leftValue % value;
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(getMinNumberOfCoins(551));
    }
}
