// Given a string of lowercase characters (a-z), return the length of the longest palindromic subsequence.
//
// Subsequence: a sequence that can be derived from another sequence by deleting some or no elements without changing the order of the remaining elements.
//
// For example, the sequence ⟨A, B, D⟩ is a subsequence of ⟨A, B, C, D, E, F⟩ obtained after removal of elements C, E, and F.
//
// https://en.m.wikipedia.org/wiki/Subsequence
//
// Input: {String}
// Output: {Integer}

// Input:  "vtvvv"
//Output: 4
//
//Longest palindromic subsequence here is "vvvv"
// DP
// Use index i and j representing the start and end index of a substring
// Case 1: When i equals j, the longest palindrome length is 1
// Case 2: When i and j next to each other: If they are the same letter, return 2; Else return 1
// Case 3: The longest Palindrome within i and j:
//          a. i and j are pointing the same: pass 2 + i+1 to j-1
//          b. Else, max of i+1 to j / i to j-1
//               vtvv
//           /           \
//        tvv            vtv
//       /    \        /     \
//     vv     tv     tv      vt
//    /  \    / \   /  \    /   \
//   v   v   t   v  t  v   v    t

//    v   t   v   v   v
// v  1   1   2   4   4
// t      1   1   2   3
// v          1   2   3
// v              1   2
// v                  1
public class Solution {

    public static void main(String[] args) {
        System.out.println(new Solution().longestPalSubsequence("ttbtctcbt"));

    }

    static int [][] memo;
    public int longestPalSubsequence(String str) {
        int n = str.length();
        memo = new int[n][n];
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                memo[i][j] = i == j ? 1 : -1;
            }
        }
        return helper(0, str.length() - 1, str);
    }

    private int helper(int i, int j, String s) {
        if(memo[i][j] != -1) {
            return memo[i][j];
        }
        else if(i == j - 1) {
            if(s.charAt(i) == s.charAt(j)) {
                memo[i][j] = 2;
            }
            else {
                memo[i][j] = 1;
            }
            return memo[i][j];
        }

        if(s.charAt(i) == s.charAt(j)) {
            memo[i][j] = 2 + helper(i + 1, j - 1, s);
        }
        else {
            memo[i][j] = Math.max(helper(i + 1, j, s), helper(i, j - 1, s));
        }
        return memo[i][j];
    }
}
