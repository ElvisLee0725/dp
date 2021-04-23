// Given an integer array arr, you should partition the array into (contiguous) subarrays of length at most k. After partitioning, each subarray has their values changed to become the maximum value of that subarray.
// Return the largest sum of the given array after partitioning.

// Use a dp[arr.length][arr.length], dp[i][j] represents the max consecutive sum from index i to j
// Fill the dp table from left to right, bottom to top
// If i == j, dp[i][j] = arr[i]
// Else, a. when i is within range k, fill the table with max * range
//       b. when i is out of range k, check all possible range and get max from dp table
// Return dp[0][n-1];
// Time: O(n^2k), Space: O(n^2)

class Solution {
    public static void main(String[] args) {
        System.out.print(new Solution().maxSumAfterPartitioning(new int[]{1,15,7,9,2,5,10}, 3));
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
