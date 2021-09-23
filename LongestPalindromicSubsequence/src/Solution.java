/*
Given a string s, find the longest palindromic subsequence's length in s.
A subsequence is a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.

DP:
- Create a matrix dp, dp[i][j] represents the longest palindromic subsequence from index i to index j.
- Fill in matrix from bottom to top, left to right
Ex. "bbbab"

   0 1 2 3 4
0  1 2
1    1
2      1
3        1
4          1

    - If i == j, the letter itself is 1
    - Else,
        -If char at i is the same as char at j, if they are next to each other, dp[i][j] = 2; Else, dp[i][j] = dp[i+1][j-1] + 2
        -Else, dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1])

- Return dp[0][s.length()-1] at the end
Time: O(N^2), Space: O(N^2)
*/

class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().longestPalindromeSubseq("bbbab"));
    }
    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int [][] dp = new int[n][n];
        boolean [][] isPal = new boolean[n][n];

        for(int j = 0; j < n; j++) {
            for(int i = j; i >= 0; i--) {
                if(i == j) {
                    isPal[i][j] = true;
                    dp[i][j] = 1;
                }
                else {
                    if(s.charAt(i) == s.charAt(j)) {
                        if(i == j - 1) {
                            dp[i][j] = 2;
                        }
                        else {
                            dp[i][j] = dp[i+1][j-1] + 2;
                        }
                    }
                    else {
                        dp[i][j] = Math.max(dp[i+1][j], dp[i][j-1]);
                    }
                }
            }
        }
        return dp[0][n-1];
    }
}
