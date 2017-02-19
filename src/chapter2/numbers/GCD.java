package chapter2.numbers;

/**
 * Greater Common Divisor related problems
 */
public class GCD {
    // Calculate GCD of a and b
    public static long calcGCD(long a, long b) {
        if (a < b) return calcGCD(b, a);
        if (b == 0) return a;
        return calcGCD(b, a % b);
    }

    // Calc x, y such that ax+by=GCD(a,b)
    public static long[] extendedGCD(long a, long b) {
        if (a < b) {
            long[] swapResult = extendedGCD(b, a);
            return new long[]{swapResult[1], swapResult[0], swapResult[2]};
        }
        if (b == 0) return new long[]{1, 0, a};
        long[] prevResult = extendedGCD(b, a%b);
        long prevX = prevResult[0];
        long prevY = prevResult[1];
        long gcd = prevResult[2];
        return new long[]{prevY, prevX - a / b * prevY, gcd};
    }

    public static long findModReverse(long a, long N) {
        long originalResult = extendedGCD(a, N)[0];
        if (originalResult < 0 || originalResult >= N) {
            long numOfNToAdd = Math.abs(originalResult) / N + 1;
            return originalResult + numOfNToAdd * N;
        }
        return originalResult;
    }

    public static void main(String[] args) {
        long result = findModReverse(3, 132);
        System.out.println(result);
    }
}
