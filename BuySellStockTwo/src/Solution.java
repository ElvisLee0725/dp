// Say you have an array prices for which the ith element is the price of a given stock on day i.
// Design an algorithm to find the maximum profit. You may complete as many transactions as you like (i.e., buy one and sell one share of the stock multiple times).
// Note: You may not engage in multiple transactions at the same time (i.e., you must sell the stock before you buy again).

// If draw all prices into a graph, we can see the max profit is the sum of every high minus its previous low.
// Use a variable to store current min price as prices[0]. Loop from index 1, if cur price is greater than previous, sell it and sum up profits.
// Update previous to cur price. Return the final sum after for loop.

// Time: O(n), Space: O(1)
public class Solution {
    public static void main(String[] args) {
        int [] nums = new int[]{1, 2, 3, 4, 5};
        System.out.print(new Solution().maxProfit(nums));
    }

    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1) {
            return 0;
        }
        int prevPrice = prices[0];
        int sum = 0;
        for(int i = 1; i < prices.length; i++) {
            sum += prices[i] > prevPrice ? prices[i] - prevPrice : 0;
            prevPrice = prices[i];
        }
        return sum;
    }
}
