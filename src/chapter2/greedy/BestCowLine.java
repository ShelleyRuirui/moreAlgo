package chapter2.greedy;

/**
 * http://poj.org/problem?id=3617
 */
public class BestCowLine {
    public static String getBestCowLine(String str) {
        StringBuilder builder = new StringBuilder();
        int start = 0;
        int end = str.length() -1;
        while (start <= end) {
            char frontChar = str.charAt(start);
            char endChar = str.charAt(end);
            if (frontChar < endChar) {
                builder.append(frontChar);
                start ++;
            } else if (frontChar < endChar) {
                builder.append(endChar);
                end --;
            } else {
                String current = str.substring(start, end + 1);
                String reverse = new StringBuilder(current).reverse().toString();
                if (current.compareTo(reverse) <= 0) {
                    builder.append(frontChar);
                    start ++;
                } else {
                    builder.append(endChar);
                    end --;
                }
            }
        }
        return builder.toString();
    }

    public static String getBestCowLine2(String str) {
        StringBuilder builder = new StringBuilder();
        int start = 0;
        int end = str.length() - 1;
        boolean takeFromStart = false;
        while (start <= end) {
            for (int step = 0; start + step < end - step ; step ++) {
                char frontChar = str.charAt(start + step);
                char endChar = str.charAt(end - step);
                if (frontChar < endChar) {
                    takeFromStart = true;
                } else if (frontChar > endChar) {
                    takeFromStart = false;
                } else {
                    break;
                }
            }

            if (takeFromStart) {
                builder.append(str.charAt(start));
                start ++;
            } else {
                builder.append(str.charAt(end));
                end --;
            }
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        System.out.println(getBestCowLine2("ACDBCB"));
    }
}
