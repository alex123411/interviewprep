package org.alex123411.interviewprep;

import org.alex123411.interviewprep.Solution.DailyTask.Easy;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {
        Set<Easy.Point> set = new HashSet<>();

        int x = 0;
        int y = 0;
        set.add(new Easy.Point(x, y));
        set.add(new Easy.Point(0, 0));
        System.out.println(set.contains(new Easy.Point(0, 0)));

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