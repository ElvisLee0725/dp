// Alice and Bob continue their games with piles of stones.  There are a number of piles arranged in a row, and each pile has a positive integer number of stones piles[i].  The objective of the game is to end with the most stones.
//
// Alice and Bob take turns, with Alice starting first.  Initially, M = 1.
//
// On each player's turn, that player can take all the stones in the first X remaining piles, where 1 <= X <= 2M.  Then, we set M = max(M, X).
//
// The game continues until all the stones have been taken.
//
// Assuming Alice and Bob play optimally, return the maximum number of stones Alice can get.
// DP:
// dp[i][m] is the max stones Alice can get from i to the end with m consecutive piles
// Base Case:
// curIndex >= piles.length, return 0;
// dp[curIndex][M] is not -1, return dp[curIndex][m]
// Try all possible move from 1 to 2M and record max
// Set dp[curIndex][M] = max and return it

class Solution {
    public static void main(String[] args) {
        System.out.print(new Solution().stoneGameII(new int[]{1,2,3,4,5,100}));
    }
    public int stoneGameII(int[] piles) {
        int [][] dp = new int[piles.length][piles.length+1];
        int [] sum = new int[piles.length];
        for(int i = 0; i < sum.length; i++) {
            sum[i] = i == 0 ? piles[0] : sum[i-1] + piles[i];
        }
        return helper(0, 1, piles, sum, dp);
    }

    private int helper(int curIndex, int M, int [] piles, int [] sum, int [][] dp) {
        if(curIndex >= piles.length) {
            return 0;
        }
        else if(dp[curIndex][M] != 0) {
            return dp[curIndex][M];
        }

        int max = Integer.MIN_VALUE;
        for(int i = curIndex; i < curIndex + 2*M && i < piles.length; i++) {
            int curSum = sum[i] - (curIndex == 0 ? 0 : sum[curIndex-1]);
            int nextM = Math.max(i - curIndex + 1, M);
            curSum += sum[sum.length-1] - sum[i] - helper(i + 1, nextM, piles, sum, dp);
            max = Math.max(max, curSum);
        }
        dp[curIndex][M] = max;
        return dp[curIndex][M];
    }
}
