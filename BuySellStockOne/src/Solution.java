// Say you have an array for which the ith element is the price of a given stock on day i.
// If you were only permitted to complete at most one transaction (i.e., buy one and sell one share of the stock), design an algorithm to find the maximum profit.
// Note that you cannot sell a stock before you buy one.

// Max profit = highest price - lowest cost
// Use a variable lowest = prices[0] and profit = 0
// Loop from index 1. If cur price is lower, update lowest. Else, get update profit.
// Return the profit
// Time: O(n), Space: O(1)
public class Solution {
    public static void main(String[] args) {
        int [] nums = new int[]{7,1,5,3,6,4};
        System.out.print(new Solution().maxProfit(nums));
    }

    public int maxProfit(int[] prices) {
        if(prices == null || prices.length == 0) {
            return 0;
        }
        int lowest = prices[0];
        int profit = 0;
        for(int i = 1; i < prices.length; i++) {
            lowest = Math.min(lowest, prices[i]);
            profit = Math.max(profit, prices[i] - lowest);
        }
        return profit;
    }
}
