// The chess knight has a unique movement, it may move two squares vertically and one square horizontally, or two squares horizontally and one square vertically (with both forming the shape of an L). The possible movements of chess knight are shown in this diagaram:
// A chess knight can move as indicated in the chess diagram below:
// We have a chess knight and a phone pad as shown below, the knight can only stand on a numeric cell (i.e. blue cell).
// Given an integer n, return how many distinct phone numbers of length n we can dial.
//
//You are allowed to place the knight on any numeric cell initially and then you should perform n - 1 jumps to dial a number of length n. All jumps should be valid knight jumps.
//
//As the answer may be very large, return the answer modulo 109 + 7.

// DP: Memoization
// Use a dp[10][n+1] matrix, dp[i][j] represents how many distinct phone numbers at number i with length j
// Base Case:
// If cur length is 0, return 1
// Else if dp[i][j] is not null (cache hit), return dp[i][j]
// Fill the value of dp[i][j]:
// Call recursion function and get all possible next step from current step, sum up the result to dp[i][j] and return it
// After filling the dp matrix, return the sum from dp[0][n] to
// Time: O(n), Space: O(n)
class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().knightDialer2(1));
    }

    // DP - Tabulation
    // Use a dp[10][n] matrix, dp[i][j] represents the number of distnct dials at number i with j jumps
    // Initialize: dp[0][1] is 1
    // Fill the table from left to right, top to bottom: dp[i][j] = dp[prev number 1][j-1] + dp[prev number 2][j-1]
    // Return the sum from dp[0][n-1] to dp[9][n-1]
    public int knightDialer2(int n) {
        int [][] dp = new int[10][n+1];
        int [][] prev = {{4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9}, {}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}};

        for(int j = 1; j < dp[0].length; j++) {
            for(int i = 0; i < dp.length; i++) {
                if(j == 1) {
                    dp[i][j] = 1;
                }
                else {
                    int sum = 0;
                    for(int pre : prev[i]) {
                        sum = (sum + dp[pre][j-1]) % 1000000007;
                    }
                    dp[i][j] = sum;
                }
            }
        }

        int res = 0;
        for(int i = 0; i < dp.length; i++) {
            res = (res + dp[i][n]) % 1000000007;
        }
        return res;
    }

    Integer [][] dp;
    int [][] next;
    int mod = 1000000007;
    public int knightDialer(int n) {
        dp = new Integer[10][n];
        next = new int[][]{{4, 6}, {6, 8}, {7, 9}, {4, 8}, {0, 3, 9}, {}, {0, 1, 7}, {2, 6}, {1, 3}, {2, 4}};
        int res = 0;
        for(int i = 0; i < 10; i++) {
            res = (res + helper(i, n - 1)) % mod;
        }
        return res;
    }

    private int helper(int cur, int jumps) {
        if(jumps == 0) {
            return 1;
        }
        else if(dp[cur][jumps] != null) {
            return dp[cur][jumps];
        }

        dp[cur][jumps] = 0;
        for(int num : next[cur]) {
            dp[cur][jumps] = (dp[cur][jumps] + helper(num, jumps - 1)) % mod;
        }
        return dp[cur][jumps];
    }
}
