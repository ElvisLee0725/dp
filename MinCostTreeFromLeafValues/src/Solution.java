// Given an array arr of positive integers, consider all binary trees such that:
// Each node has either 0 or 2 children;
// The values of arr correspond to the values of each leaf in an in-order traversal of the tree.  (Recall that a node is a leaf if and only if it has 0 children.)
// The value of each non-leaf node is equal to the product of the largest leaf value in its left and right subtree respectively.
// Among all possible binary trees considered, return the smallest possible sum of the values of each non-leaf node.  It is guaranteed this sum fits into a 32-bit integer.

// DP solution:
// Use dp[i][j] to store the min sum of non-leaf nodes from index i to j
// Use max[i][j] to represent the max value from index i to j
// For each combination of i and j:
// Case 1: i equals to j, max[i][j] = arr[i], dp[i][j] is 0 (do nothing)
// Case 2: i is next to j, dp[i][j] = arr[i] * arr[j], max[i][j] = the max of arr[i] and arr[j]
// Case 3: For each possible k, find dp[i][j] = dp[i][k] + dp[k+1][j] + max[i][k] * max[k][j]. max[i][j] is the max of max[i][k] and max[k+1][j]
// Return dp[0][dp[0].length-1] which is the min from index 0 to n-1
// Time: O(n^2), Space: O(n^2)

public class Solution {
    public static void main(String[] args) {
        int [] arr = new int[]{6,2,4,3};
        System.out.print(new Solution().mctFromLeafValues(arr));
    }
    public int mctFromLeafValues(int[] arr) {
        int n = arr.length;
        int [][] dp = new int[n][n];
        int [][] max = new int[n][n];

        for(int j = 0; j < n; j++) {
            for(int i = j; i >= 0; i--) {
                if(i == j) {
                    max[i][j] = arr[i];
                }
                else if(i == j - 1) {
                    dp[i][j] = arr[i] * arr[j];
                    max[i][j] = Math.max(arr[i], arr[j]);
                }
                else {
                    dp[i][j] = Integer.MAX_VALUE;
                    for(int k = i; k < j; k++) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k+1][j] + max[i][k] * max[k+1][j]);
                        max[i][j] = Math.max(max[i][k], max[k+1][j]);
                    }
                }
            }
        }
        return dp[0][n-1];
    }
}
