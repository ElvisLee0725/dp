// Given an integer array arr, you should partition the array into (contiguous) subarrays of length at most k. After partitioning, each subarray has their values changed to become the maximum value of that subarray.
// Return the largest sum of the given array after partitioning.

// Use a dp[arr.length][arr.length], dp[i][j] represents the max consecutive sum from index i to j
// Fill the dp table from left to right, bottom to top
// If i == j, dp[i][j] = arr[i]
// Else, a. when i is within range k, fill the table with max * range
//       b. when i is out of range k, check all possible range and get max from dp table
// Return dp[0][n-1];
// Time: O(n^2k), Space: O(n^2)

import java.util.Arrays;

class Solution {
    public static void main(String[] args) {
        System.out.print(new Solution().maxSumAfterPartitioning1(new int[]{1,15,7,9,2,5,10}, 3));
    }

    // DP: Memoization
    // Use a 1-d array, dp[i] represents the max sum from i to last index. Initialize with value -1
    // Base Case:
    // a. If curent index has reached the end of array, return 0;
    // b. If dp[i] has an answer, return dp[i]
    // At each index, get the max value in range from cur index to cur index + k - 1. Then, get the max of the curMax * length + next index's greatest value (Get from dp array)
    // Then, assign curRes to dp[curIndex] and return it
    // Return dp[0] at the end
    // Time: O(n*k), Space: O(n)
    public int maxSumAfterPartitioning1(int[] arr, int k) {
        int n = arr.length;
        int [] dp = new int[n];
        Arrays.fill(dp, -1);
        helper(0, arr, k, dp);
        return dp[0];
    }

    private int helper(int curIndex, int [] arr, int k, int [] dp) {
        if(curIndex == arr.length) {
            return 0;
        }
        else if(dp[curIndex] != -1) {
            return dp[curIndex];
        }

        int curMax = Integer.MIN_VALUE;
        int ans = Integer.MIN_VALUE;

        for(int i = curIndex; i < curIndex + k && i < arr.length; i++) {
            curMax = Math.max(curMax, arr[i]);
            ans = Math.max(ans, curMax * (i - curIndex + 1) + helper(i+1, arr, k, dp));
        }
        dp[curIndex] = ans;
        return dp[curIndex];
    }

    public int maxSumAfterPartitioning(int[] arr, int k) {
        int n = arr.length;
        int [][] dp = new int[n][n];

        for(int j = 0; j < n; j++) {
            for(int i = j; i >= 0; i--) {
                if(i == j) {
                    dp[i][j] = arr[i];
                }
                else {
                    if(i >= j-k+1) {
                        int curMax = arr[j];
                        for(int l = j-1; l >= i; l--) {
                            curMax = Math.max(curMax, arr[l]);
                            dp[i][j] = curMax * (j - l + 1);
                        }
                    }
                    else {
                        int max = Integer.MIN_VALUE;
                        for(int l = i; l < j; l++) {
                            max = Math.max(max, dp[i][l] + dp[l+1][j]);
                        }
                        dp[i][j] = max;
                    }
                }
            }
        }
        return dp[0][n-1];
    }
}
