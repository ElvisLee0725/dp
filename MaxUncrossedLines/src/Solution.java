// We write the integers of A and B (in the order they are given) on two separate horizontal lines.
// Now, we may draw connecting lines: a straight line connecting two numbers A[i] and B[j] such that:
// A[i] == B[j];
// The line we draw does not intersect any other connecting (non-horizontal) line.
// Note that a connecting lines cannot intersect even at the endpoints: each number can only belong to one connecting line.
// Return the maximum number of connecting lines we can draw in this way.

// DP
// Create dp[A.length][B.length], dp[i][j] represents the max uncrossed lines using A[0 to i-1] and B[0 to j-1]
// In the for for loop, check if A[i] == B[j], if so, dp[i][j] = dp[i-1][j-1] + 1
// Else, dp[i][j] = Max of dp[i-1][j] or dp[i][j-1]
// Return the last position of dp

// Time: O(n^2), Space: O(n^2)
class Solution {
    public static void main(String[] args) {
        int [] A = new int[]{4,1,2,5,1,5,3,4,1,5};
        int [] B = new int[]{3,1,1,3,2,5,2,4,1,3,2,2,5,5,3,5,5,1,2,1};

        System.out.print(new Solution().maxUncrossedLines(A, B));
    }
    public int maxUncrossedLines(int[] A, int[] B) {
        int [][] dp = new int[A.length][B.length];
        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                if(i == 0 || j == 0) {
                    if(A[i] == B[j]) {
                        dp[i][j] = 1;
                    }
                    else {
                        if(i == 0 && j != 0) {
                            dp[i][j] = dp[i][j-1];
                        }
                        else if(j == 0 && i != 0) {
                            dp[i][j] = dp[i-1][j];
                        }
                    }
                }
                else {
                    if(A[i] == B[j]) {
                        dp[i][j] = dp[i-1][j-1] + 1;
                    }
                    else {
                        dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
                    }
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}
