package chapter2.numbers;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Implement RSA algorithm
 */
public class RSA {
    private final static int PRIME_RANGE = 10000;

    public static void implementRSA(String msg) {
        Long[] publicKeyPrimes = generatePublicKeyPrimes();
        long p = publicKeyPrimes[0];
        long q = publicKeyPrimes[1];
        long publicKey = p * q;
        int e = findEToPrimes(p, q);

        System.out.println("Original message: " + msg);
        System.out.println("publicKey: " + publicKey);
        System.out.println("encode power index: " + e);

        List<Long> encodedMsg = encode(msg, publicKey, e);
        System.out.println("encoded msg: " + encodedMsg);
        String decodedMsg = decode(encodedMsg, p, q, e);
        System.out.println("decoded msg: " + decodedMsg);
    }

    // returns the N, first and second prime factor
    private static Long[] generatePublicKeyPrimes() {
        List<Integer> primes = PrimeUtil.getPrimes(PRIME_RANGE);
        Random rand = new Random();
        int index1 = rand.nextInt(primes.size());
        int index2 = rand.nextInt(primes.size());
        Long[] result = new Long[2];
        result[0] = (long)primes.get(index1);
        result[1] = (long)primes.get(index2);
        return result;
    }

    // returns e that cannot divide (p-1)(q-1)
    private static int findEToPrimes(long p, long q) {
        long multiple = (p-1) * (q-1);
        for (int i = 2; i <= PRIME_RANGE; i ++) {
            if (multiple % i !=0) {
                return i;
            }
        }
        return -1;
    }

    private static List<Long> encode(String msg, long publicKey, int e) {
        List<Long> result = new ArrayList<>();
        for (char c : msg.toCharArray()) {
            result.add(PrimeUtil.pow(c, e) % publicKey);
        }
        return result;
    }

    private static String decode(List<Long> msg, long p, long q, int e) {
        StringBuilder stringBuilder = new StringBuilder();
        long privateKey = GCD.findModReverse(e, (p-1)*(q-1));
        for (Long val : msg) {
            char decodedChar = (char)(PrimeUtil.powerMod(val, privateKey, p*q));
            stringBuilder.append(decodedChar);
        }
        return stringBuilder.toString();
    }

    public static void main(String[] args) {
        implementRSA("Suzy is so beautiful!!");
    }
}
