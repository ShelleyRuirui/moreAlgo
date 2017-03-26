package chapter2.greedy;

/**
 * http://poj.org/problem?id=3069
 */
public class SarumanArmy {
    static int getMinMarkedPoints(int[] locations, int coverDist) {
        int result = 0;
        int leftMost = locations[0];
        int index = 1;
        while (true) {
            for(;index < locations.length && leftMost + coverDist >= locations[index] ; index ++);
            if (index < locations.length) {
                int markIndex = index - 1;
                result ++;
                for (; index < locations.length && locations[markIndex] + coverDist >= locations[index]; index ++);
                if (index < locations.length) {
                    leftMost = locations[index];
                    index ++;
                }
            } else {
                return result + 1;
            }
        }
    }

    public static void main(String[] args) {
        int[] locations1 = new int[]{1, 7, 15, 20, 30, 50};
        int[] locations2 = new int[]{1, 7, 10};
        int[] locations3 = new int[]{1, 7, 20};
        int[] locations4 = new int[]{1};
        System.out.println(getMinMarkedPoints(locations1, 10));
        System.out.println(getMinMarkedPoints(locations2, 10));
        System.out.println(getMinMarkedPoints(locations3, 10));
        System.out.println(getMinMarkedPoints(locations4, 10));
    }
}
