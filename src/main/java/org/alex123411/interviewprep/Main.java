package org.alex123411.interviewprep;

import org.alex123411.interviewprep.Solution.DailyTask.Easy;
import org.alex123411.interviewprep.Solution.DailyTask.Medium;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Medium s = new Medium();

        long timeStart = System.currentTimeMillis();
        System.out.println(s.numSquares(12));
        long timeEnd = System.currentTimeMillis();
        System.out.println("Time: " + (timeEnd - timeStart) + " milliseconds");
    }

    public int strStr(String haystack, String needle) {
        char[] h = haystack.toCharArray();
        char[] n = needle.toCharArray();
        for (int i = 0; i < h.length; i++) {
            if (h[i] == n[0]) {
                for (int j = 0; j < n.length; j++) {
                    if (i + j >= h.length || h[i + j] != n[j]) break;
                    if (j == n.length - 1) return i;
                }
            }
        }
        return -1;
    }
}