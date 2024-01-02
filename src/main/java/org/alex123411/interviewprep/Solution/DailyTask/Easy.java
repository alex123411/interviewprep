package org.alex123411.interviewprep.Solution.DailyTask;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Easy {
    // 2706. Buy Two Chocolates
    // https://leetcode.com/problems/buy-two-chocolates/?envType=daily-question&envId=2023-12-20
    public int buyChoco(int[] prices, int money) {
        int[] chocs = new int[2];

        chocs[0] = prices[0];
        chocs[1] = prices[1];

        for (int i = 2; i < prices.length; i++) {
            if (chocs[0] > chocs[1]) {
                chocs[0] = Math.min(chocs[0], prices[i]);
            } else {
                chocs[1] = Math.min(chocs[1], prices[i]);
            }
        }
        return money - chocs[0] - chocs[1] < 0 ? money : money - chocs[0] - chocs[1];
    }

    // 50. Pow(x, n)
    // https://leetcode.com/problems/powx-n/?envType=study-plan-v2&envId=top-interview-150
    public double myPow(double x, int n) {
        if (n < 0) {
            n = -n;
            x = 1 / x;
        }

        double pow = 1;

        while (n != 0) {
            if ((n & 1) != 0) {
                pow *= x;
            }

            x *= x;
            n >>>= 1;

        }

        return pow;
    }

    // 1422. Maximum Score After Splitting a String
    // https://leetcode.com/problems/maximum-score-after-splitting-a-string/
    public int maxScore(String s) {
        int res = 0;
        int sumOne = 0;
        char[] arr = s.toCharArray();
        for (char c : arr) if (c == '1') sumOne++;

        int sumZero = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] == '0') sumZero++;
            else sumOne--;
            res = Math.max(res, sumOne + sumZero);
        }

        return res;
    }

    // 1496. Path Crossing
    // https://leetcode.com/problems/path-crossing/?envType=daily-question&envId=2023-12-23
    public static class Point {
        private int x;
        private int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj) return true;
            if (obj == null || getClass() != obj.getClass()) return false;
            Point point = (Point) obj;
            return Double.compare(point.x, x) == 0 && Double.compare(point.y, y) == 0;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }

    public boolean isPathCrossing(String path) {
        Set<Point> set = new HashSet<>();

        int x = 0;
        int y = 0;
        set.add(new Point(x, y));
        for (char c : path.toCharArray()) {
            if (c == 'N') y++;
            else if (c == 'S') y--;
            else if (c == 'W') x++;
            else if (c == 'E') x--;

            Point p = new Point(x, y);

            if (set.contains(p)) return true;
            set.add(p);
        }

        return false;
    }

    public int minOperations(String s) {
        int x = 0, y = 0;

        char[] arr = s.toCharArray();

        // 010101
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0 && arr[i] != '0') x++;
            else if (i % 2 == 1 && arr[i] != '1') x++;
        }

        // 1010101
        for (int i = 0; i < arr.length; i++) {
            if (i % 2 == 0 && arr[i] != '1') y++;
            else if (i % 2 == 1 && arr[i] != '0') y++;
        }

        return Math.min(x, y);
    }
}
