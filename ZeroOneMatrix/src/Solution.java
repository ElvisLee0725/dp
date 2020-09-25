// Given a matrix consists of 0 and 1, find the distance of the nearest 0 for each cell.
// The distance between two adjacent cells is 1

// DP
// Use a dp matrix to record the min distance from 0 to dp[i][j]. Initialize matrix with Integer.MAX_VALUE
// First Scan: From top down, left to right. If current position is 0, set dp[i][j] = 0
// Else, if cur position is not on left or top edge, check if its left or top + 1 is shorter. If so, update
// Second Scan: From down top, right to left. If cur position is not on bottom or right, check if its
// right or bottom + 1 is shorter? If so, update it.
// Return the dp matrix

// Time: O(m*n), Space: O(m*n)
public class Solution {
    public static void main(String[] args) {
        int [][] matrix = {{0,0,0}, {0,1,0}, {1,1,1}};
        int [][] res = new Solution().updateMatrix(matrix);

        for(int i = 0; i < res.length; i++) {
            for(int j = 0; j < res[0].length; j++) {
                System.out.print(res[i][j]);
                if(j != res[0].length - 1) {
                    System.out.print(", ");
                }
                else {
                    System.out.println();
                }
            }
        }

    }

    public int[][] updateMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int [][] dp = new int[row][col];

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(matrix[i][j] == 0) {
                    dp[i][j] = 0;
                }
                else {
                    if(i > 0 && dp[i-1][j] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + 1);
                    }
                    if(j > 0 && dp[i][j-1] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], dp[i][j-1] + 1);
                    }
                }
            }
        }

        for(int i = row - 1; i >= 0; i--) {
            for(int j = col - 1; j >= 0; j--) {
                if(j < col - 1 && dp[i][j+1] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][j+1] + 1);
                }
                if(i < row - 1 && dp[i+1][j] != Integer.MAX_VALUE) {
                    dp[i][j] = Math.min(dp[i][j], dp[i+1][j] + 1);
                }
            }
        }
        return dp;
    }
}
