package chapter2.divideConquer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Multiple tasks that may have overlapping time. Calculate the max number of tasks that can be finished.
 */
public class WorkSchedule {
    public static int maxNumOfWorksCanBeFinished(int[] start, int[] end) {
        WorkUnit[] workUnits = new WorkUnit[start.length];
        for (int i = 0 ; i < start.length; i ++) {
            WorkUnit unit = new WorkUnit(start[i], end[i]);
            workUnits[i] = unit;
        }
        Arrays.sort(workUnits);

        int result = 0;
        int lastEnd = 0;
        for (WorkUnit unit : workUnits) {
            if (lastEnd < unit.start) {
                result ++;
                lastEnd = unit.end;
            }
        }
        return result;
    }

    static class WorkUnit implements Comparable<WorkUnit> {
        int start;
        int end;

        WorkUnit(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(WorkUnit that) {
            return end - that.end;
        }
    }

    public static void main(String[] args) {
        int[] starts = new int[]{1, 2, 4, 6, 8};
        int[] ends = new int[]{3, 5, 7, 9, 10};
        System.out.println(maxNumOfWorksCanBeFinished(starts, ends));
    }
}
