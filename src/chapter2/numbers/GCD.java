package chapter2.numbers;

/**
 * Greater Common Divisor related problems
 */
public class GCD {
    // Calculate GCD of a and b
    public static int calcGCD(int a, int b) {
        if (a < b) return calcGCD(b, a);
        if (b == 0) return a;
        return calcGCD(b, a % b);
    }

    // Calc x, y such that ax+by=GCD(a,b)
    public static int[] extendedGCD(int a, int b) {
        if (a < b) {
            int[] swapResult = extendedGCD(b, a);
            return new int[]{swapResult[1], swapResult[0], swapResult[2]};
        }
        if (b == 0) return new int[]{1, 0, a};
        int[] prevResult = extendedGCD(b, a%b);
        int prevX = prevResult[0];
        int prevY = prevResult[1];
        int gcd = prevResult[2];
        return new int[]{prevY, prevX - a / b * prevY, gcd};
    }

    public static void main(String[] args) {
        int[] result = extendedGCD(12, 43);
        System.out.println("x:" + result[0] + " y:" + result[1]);
    }
}
