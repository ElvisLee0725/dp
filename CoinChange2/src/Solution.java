// You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
//
//Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.
//
//You may assume that you have an infinite number of each kind of coin.
//
//The answer is guaranteed to fit into a signed 32-bit integer.

// Create an array of length coins + 1. Initialize with dp[0] = 1 since amount is 0 we take 0 coins
// dp[i] represents the ways to get to amount i
// For each coin, update the dp[i] by memo[amount - coin] each of the amount from coin to last amount
// Time: O(n * amount), Space: O(amount)
class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().change(5, new int[]{1, 2,5}));
    }
    public int change(int amount, int[] coins) {
        int [] dp = new int[amount+1];
        dp[0] = 1;
        for(int coin : coins) {
            for(int i = coin; i < amount + 1; i++) {
                dp[i] += dp[i - coin];
            }
        }
        return dp[dp.length-1];
    }
}
