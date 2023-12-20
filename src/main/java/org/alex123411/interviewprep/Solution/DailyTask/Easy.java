package org.alex123411.interviewprep.Solution.DailyTask;

import java.math.BigDecimal;

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
        if(n < 0){
            n = -n;
            x = 1 / x;
        }

        double pow = 1;

        while(n != 0){
            if((n & 1) != 0){
                pow *= x;
            }

            x *= x;
            n >>>= 1;

        }

        return pow;
    }
}
