package chapter2.divideConquer;

/**
 * Created by ruiruilu on 3/4/17.
 */
public class MultiplyTwoNums {

    // O(n^(log3/log2)), better than O(n^2)
    public static String multiply(String num1, String num2) {
        if (num1.length() == 1) {
            return num1.equals("1") ? num2 : "0";
        }
        if (num1.length() > num2.length()) {
            return multiply(num2, num1);
        }
        if (num1.length() < num2.length()) {
            num1 = fillToLength(num1, num2.length());
        }
        int n = (num1.length()+1) / 2 * 2;
        String[] num1s = twoHalfs(num1);
        String[] num2s = twoHalfs(num2);
        String v1 = multiply(num1s[0], num2s[0]);
        String v2 = multiply(num1s[1], num2s[1]);
        String v3 = multiply(add(num1s[0], num1s[1]), add(num2s[0], num2s[1]));
        String calculated = subtract(subtract(v3, v2), v1);
        String result = add(add(pow(v1, n), pow(calculated, n/2)), v2);
        return trim(result);
    }

    // O(n)
    public static String add(String num1, String num2) {
        if (num1.length() > num2.length()) {
            return add(num2, num1);
        }
        String reverse1= new StringBuilder().append(num1).reverse().toString();
        String reverse2 = new StringBuilder().append(num2).reverse().toString();
        StringBuilder stringBuilder = new StringBuilder();
        int carry=0;
        for (int i = 0; i < reverse2.length();i++) {
            int val1 = i < reverse1.length() ? reverse1.charAt(i) - '0' : 0;
            int val2 = reverse2.charAt(i) - '0';
            int res = val1 + val2 + carry;
            stringBuilder.append((char) (res%2 +'0'));
            carry = res / 2;
        }
        if (carry == 1) {
            stringBuilder.append('1');
        }
        return stringBuilder.reverse().toString();
    }

    // requirement: num1 > num2, O(n)
    public static String subtract(String num1, String num2) {
        String reverse1 = new StringBuilder().append(num1).reverse().toString();
        String reverse2 = new StringBuilder().append(num2).reverse().toString();
        StringBuilder stringBuilder = new StringBuilder();
        int borrow = 0;
        for (int i = 0; i < reverse1.length(); i++) {
            int val1 = reverse1.charAt(i) - '0';
            int val2 = reverse2.length() > i ? reverse2.charAt(i)-'0' : 0;
            int res = val1 - val2 - borrow;
            borrow = res < 0 ? 1 : 0;
            res = res < 0 ? res + 2 : res;
            stringBuilder.append(res);
        }
        return trim(stringBuilder.reverse().toString());
    }

    private static String pow(String num, int index) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(num);
        for (int i = 0 ; i < index ; i ++) {
            stringBuilder.append("0");
        }
        return stringBuilder.toString();
    }

    private static String[] twoHalfs(String number) {
        int rightStartIndex = number.length() / 2;
        String rightHalf = number.substring(rightStartIndex);
        String leftHalf = number.substring(0, rightStartIndex);
        String[] result = new String[]{leftHalf, rightHalf};
        return result;
    }

    private static String fillToLength(String num, int length) {
        StringBuilder num1Builder = new StringBuilder();
        num1Builder.append(num);
        for (int i = 0; i<length-num.length();i++) {
            num1Builder.insert(0, "0");
        }
        return num1Builder.toString();
    }

    private static String trim(String result) {
        int index = 0;
        for (; index < result.length();index++) {
            if (result.charAt(index) == '1') {
                break;
            }
        }
        return index == result.length() ? "0" : result.substring(index);
    }
    public static void main(String[] args) {
        System.out.println("res:" + multiply("11111", "1011100"));
    }
}
