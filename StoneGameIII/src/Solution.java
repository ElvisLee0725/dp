// Alice and Bob continue their games with piles of stones. There are several stones arranged in a row, and each stone has an associated value which is an integer given in the array stoneValue.

// Alice and Bob take turns, with Alice starting first. On each player's turn, that player can take 1, 2 or 3 stones from the first remaining stones in the row.

// The score of each player is the sum of values of the stones taken. The score of each player is 0 initially.

// The objective of the game is to end with the highest score, and the winner is the player with the highest score and there could be a tie. The game continues until all the stones have been taken.

// Assume Alice and Bob play optimally.

// Return "Alice" if Alice will win, "Bob" if Bob will win or "Tie" if they end the game with the same score.

import java.util.Arrays;

// Each rounch check if cur sum + future Bob's score is greater than cur Alice's score (When Alice doesn't take)? If so, Alice takes it, while Bob get what Alice is suppose to get after her current move
class Solution {
    public static void main(String[] args) {
        System.out.print(new Solution().stoneGameIII(new int[]{1,2,3,-1,-2,-3,7}));
    }
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int [][] dp = new int[n+1][2];
        for(int i = 0; i < dp.length-1; i++) {
            Arrays.fill(dp[i], Integer.MIN_VALUE);
        }

        for(int i = n-1; i >= 0; i--) {
            int curSum = 0;
            for(int j = i; j <= Math.min(n-1, i+2); j++) {
                curSum += stoneValue[j];
                if(curSum + dp[j+1][1] > dp[i][0]) {
                    // Update Alice's score
                    dp[i][0] = curSum + dp[j+1][1];
                    // Update Bob's score
                    dp[i][1] = dp[j+1][0];
                }
            }
        }
        int valA = dp[0][0];
        int valB = dp[0][1];
        if(valA == valB) {
            return "Tie";
        }

        return valA > valB ? "Alice" : "Bob";
    }
}
