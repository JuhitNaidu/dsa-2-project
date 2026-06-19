import java.util.*;

public class StockBuySellKTransactions {

    static int maxProfit(int k, int[] prices) {

        int n = prices.length;

        if (n == 0 || k == 0)
            return 0;

        // Optimization for large k
        if (k >= n / 2) {
            int profit = 0;

            for (int i = 1; i < n; i++) {
                if (prices[i] > prices[i - 1])
                    profit += prices[i] - prices[i - 1];
            }
            return profit;
        }

        int[][] dp = new int[k + 1][n];

        for (int t = 1; t <= k; t++) {

            int maxDiff = -prices[0];

            for (int d = 1; d < n; d++) {

                dp[t][d] = Math.max(
                        dp[t][d - 1],
                        prices[d] + maxDiff);

                maxDiff = Math.max(
                        maxDiff,
                        dp[t - 1][d] - prices[d]);
            }
        }

        return dp[k][n - 1];
    }

    public static void main(String[] args) {

        int[] prices = {
            100, 80, 95, 85, 110,
            90, 130, 105, 145, 115
        };

        int k = 2;

        System.out.println(
            "Maximum Profit = " +
            maxProfit(k, prices));
    }
}
