package chapter3.trees;

/**
 * Created by luruirui on 16-5-8.
 */
public class SegmentTree {

    int[] completeTree;
    int nonLeafSize;
    int leafSize;

    public void buildTree(int[] array) {
        nonLeafSize = findPowOf2(array.length) - 1;
        leafSize = array.length;
        int completeSize = nonLeafSize + leafSize;
        completeTree = new int[completeSize];
        // Init
        for (int i = 0 ; i < completeSize ; i ++) {
            completeTree[i] = Integer.MAX_VALUE;
        }
        // Set real value
        for (int i = completeSize - 1 ; i > completeSize - 1 - leafSize ; i --) {
            update(i - nonLeafSize, array[i - nonLeafSize]);
        }
    }

    // O(lgn)
    public void update(int index, int newVal) {
        int completeTreeIndex = nonLeafSize + index;
        completeTree[completeTreeIndex] = newVal;
        int current = completeTreeIndex;
        while (current > 0) {
            current = (current - 1) / 2;
            completeTree[current] = (current * 2 + 2) < completeTree.length
                    ? Math.min(completeTree[current * 2 + 1], completeTree[current * 2 + 2])
                        : completeTree[current * 2 + 1];
        }
    }

    // Range minimum query, O(logn)
    public int queryMin(int leftIndex, int rightIndex) {
        return queryMin(leftIndex, rightIndex, 0, 0, findPowOf2(leafSize) - 1);
    }

    private int queryMin(int leftIndex, int rightIndex, int nodeIndex, int leftRange, int rightRange) {
        if (leftIndex > rightRange || rightIndex < leftRange)
            return Integer.MAX_VALUE;
        if (leftIndex <= leftRange && rightIndex >= rightRange)
            return completeTree[nodeIndex];
        return Math.min(
                queryMin(leftIndex, rightIndex, nodeIndex * 2 + 1, leftRange, (leftRange + rightRange) / 2),
                queryMin(leftIndex, rightIndex, nodeIndex * 2 + 2, (leftRange + rightRange) / 2 + 1, rightRange));
    }

    private int findPowOf2(int size) {
        int res = 1;
        while (res < size) {
            res *= 2;
        }
        return res;
    }

    public static void main(String[] args) {
        SegmentTree tree = new SegmentTree();
        int[] input = new int[]{3, 7, 2, 5, 9, 1};
        tree.buildTree(input);
        System.out.println(tree.queryMin(0, 5));
    }
}