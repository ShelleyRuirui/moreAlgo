package chapter2.numbers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Prime number related methods.
 */
public class PrimeUtil {
    // method 1: straight forward judge, time: O(n^1/2)
    public static boolean isPrime(long n) {
        for (int i = 2; i * i <= n; i ++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    // method 2: Fermat's little theorem, time: O(logn), but involve big integer multiply
    public static boolean isPrime2(int n) {
        int times = (int)Math.min(n-1, 100L);
        Random rand = new Random();
        for (int i = 0; i < times; i ++) {
            BigInteger number = BigInteger.valueOf((long)(rand.nextDouble()*(n-1)) + 1);
            BigInteger result = number.pow(n-1).mod(BigInteger.valueOf(n));
            if (!result.equals(BigInteger.ONE)) {
                return false;
            }
        }
        return true;
    }

    // sieve of Eratosthenes
    public static List<Integer> getPrimes(int n) {
        List<Integer> primes = new ArrayList<>();
        boolean[] primeIndicators = new boolean[n+1];
        for (int i = 0; i < primeIndicators.length; i++) {
            primeIndicators[i] = true;
        }
        for (int i = 2; i <= n; i ++) {
            if (primeIndicators[i]) {
                primes.add(i);
                for (int j = i * 2; j <= n; j+=i ) {
                    primeIndicators[j] = false;
                }
            }
        }
        return primes;
    }

    // fast power, time: O(logm)
    public static long pow(long n, long m) {
        long result = 1;
        long multiply = n;
        while (m > 0) {
            if (m % 2 == 1) {
                result *= multiply;
            }
            multiply *= multiply;
            m = m >> 1;
        }
        return result;
    }

    public static long powerMod(long n, long m, long N) {
        long result = 1;
        long multiply = n;
        while (m > 0) {
            if (m % 2 == 1) {
                result = multiply * result % N;
            }
            multiply = multiply * multiply % N;
            m = m >> 1;
        }
        return result;
    }

    public static void main(String[] args){
//        System.out.println(isPrime(112567781));
//        int n = 100000;
//        System.out.println(getPrimes(n).size());
//        System.out.println(getPrimes(n));
        System.out.println(powerMod(3, 27, 55));
    }
}
