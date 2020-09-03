// Say you have an array for which the ith element is the price of a given stock on day i.
// Design an algorithm to find the maximum profit. You may complete as many transactions as you like
// (ie, buy one and sell one share of the stock multiple times) with the following restrictions:
// You may not engage in multiple transactions at the same time (ie, you must sell the stock before you buy again).
// After you sell your stock, you cannot buy stock on next day. (ie, cooldown 1 day)

// Use state machine: sold: MIN, held: MIN, sold: 0
// At each day, we can do either sold (earn profit), held (buy in), or rest
// Each state represent the max profit on previous day
// Loop the array, check, update:
// Sold: previous held + cur price
// Held: Math.max(do nothing, buy in from rest)
// Rest: Math.max(do nothing, previous sold)
// Finally, compare sold and rest since only these 2 condition could have max profit

// Time: O(n), Space: O(1)
public class Solution {
    public static void main(String[] args) {
        int [] nums = new int[]{1,2,3,0,2};
        System.out.print(new Solution().maxProfit(nums));
    }

    public int maxProfit(int[] prices) {
        if(prices == null || prices.length <= 1) {
            return 0;
        }
        int sold = Integer.MIN_VALUE;
        int held = Integer.MIN_VALUE;
        int rest = 0;
        for(int i = 0; i < prices.length; i++) {
            int prevSold = sold;
            sold = held + prices[i];
            held = Math.max(held, rest - prices[i]);
            rest = Math.max(rest, prevSold);
        }

        return Math.max(sold, rest);
    }
}
