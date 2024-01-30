package org.alex123411.interviewprep.Solution.DailyTask;

public class Hard {

    // 629. K Inverse Pairs Array
    // https://leetcode.com/problems/k-inverse-pairs-array
    public int kInversePairs(int n, int k) {
        /*
        n = 5

        5 4 3 2 1 -> k_max = (n-1)!
        1 2 3 4 5 -> k_min = 0

        0 <= k <= (n-1)!
        */
        // max inverse pairs we can get is (n-1)!
        if (k == 0) return 1;

        int res = 0;


        return res;
    }
}
