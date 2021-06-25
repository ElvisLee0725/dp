// You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
//
//Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.
//
//You may assume that you have an infinite number of each kind of coin.
//
//The answer is guaranteed to fit into a signed 32-bit integer.

import java.util.ArrayList;
import java.util.List;

// Create an array of length coins + 1. Initialize with dp[0] = 1 since amount is 0 we take 0 coins
// dp[i] represents the ways to get to amount i
// For each coin, update the dp[i] by memo[amount - coin] each of the amount from coin to last amount
// Time: O(n * amount), Space: O(amount)
class Solution {
    static int [][] memo;

    public static void main(String[] args) {
        System.out.println(new Solution().change(5, new int[]{1, 2,5}));
        List<Integer> coins = new ArrayList<>();
        coins.add(1);
        coins.add(2);
        coins.add(3);
        int total = 4;
        memo = new int[coins.size()+1][total+1];
        for(int i = 0; i < memo.length; i++) {
            for(int j = 0; j < memo[0].length; j++) {
                if(i == 0) {
                    memo[i][j] = 0;
                }
                else {
                    memo[i][j] = -1;
                }
            }
        }
        // There is no coins to get and amount is 0 => 1 way
        memo[0][0] = 1;
        System.out.println(findWays(coins, coins.size(), total));
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

    private static int findWays(List<Integer> coins, int i, int total){
        if(total < 0) {
            return 0;
        }
        else if(memo[i][total] != -1) {
            return memo[i][total];
        }

        memo[i][total] = findWays(coins, i - 1, total) + findWays(coins, i, total - coins.get(i-1));

        return memo[i][total];
    }
}
