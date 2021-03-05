// Given an integer n, return the number of strings of length n that consist only of vowels (a, e, i, o, u) and are lexicographically sorted.
//
//A string s is lexicographically sorted if for all valid i, s[i] is the same as or comes before s[i+1] in the alphabet.

// Use a dp table, dp[i][j] denotes that when the length is i, using j vowels can create combinations
// dp[i][j] = dp[i-1][j] + dp[i][j-1]
// If i is 1, dp[i][j] equals the number of vowels, which is j
// If j is 1, dp[i][j] is 1 since you have only 1 vowel to use
// Time: O(n^2), Space: O(n^2)
class Solution {
    public static void main(String[] args) {
        System.out.print(new Solution().countVowelStrings(4));
    }
    public int countVowelStrings(int n) {
        int [][] dp = new int[n+1][6];
        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                if(i == 1 && j == 1) {
                    dp[i][j] = 1;
                }
                else if(i == 1 && j != 1) {
                    dp[i][j] = j;
                }
                else if(i != 1 && j == 1) {
                    dp[i][j] = 1;
                }
                else {
                    dp[i][j] = dp[i-1][j] + dp[i][j-1];
                }
            }
        }
        return dp[n][5];
    }
}
