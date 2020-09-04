// Your are given an array of integers prices, for which the i-th element is the price of a given stock on day i;
// and a non-negative integer fee representing a transaction fee.
//You may complete as many transactions as you like, but you need to pay the transaction fee for each transaction.
// You may not buy more than 1 share of a stock at a time (ie. you must sell the stock share before you buy again.)
//Return the maximum profit you can make.

// Use a variable to store profit starts from 0, and another for cost starts from -prices[0] (Buy in stock at 1st day)
// Loop the array from index 1, update the max of profit between profit (do nothing) and cost + prices[i] - fee (sell)
// and the max of cost between cost (do nothing) and profit - prices[i] (buy in share)
// Return the profit after loop

// Time: O(n), Space: O(1)
public class Solution {
    public static void main(String[] args) {
        int [] prices = new int[]{1, 3, 2, 8, 4, 9};
        System.out.print(new Solution().maxProfit(prices, 2));
    }

    public int maxProfit(int[] prices, int fee) {
        if(prices == null || prices.length <= 1) {
            return 0;
        }
        int profit = 0;
        int cost = -prices[0];
        for(int i = 1; i < prices.length; i++) {
            profit = Math.max(profit, cost + prices[i] - fee);
            cost = Math.max(cost, profit - prices[i]);
        }
        return profit;
    }
}
