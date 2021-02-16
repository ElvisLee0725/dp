// Given a m * n matrix of ones and zeros, return how many square submatrices have all ones

// Use a dp matrix to store the most number of squares at dp[i][j]
// At each position, if matrix[i][j] is 1, get the min of dp[i-1][j], dp[i][j-1] and dp[i-1][j-1] to + 1 as the value of dp[i][j]. Also update the counter value with dp[i][j]
// Return the count
// Time: O(n^2), Space: O(n^2)
class Solution {
    public static void main(String[] args) {
        int [][] m = new int[][]{{0,1,1,1}, {1,1,1,1}, {0,1,1,1}};
        System.out.print(new Solution().countSquares(m));
    }
    public int countSquares(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        int [][] dp = new int[row][col];
        int count = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(i == 0 || j == 0) {
                    dp[i][j] = matrix[i][j];
                }
                else {
                    if(matrix[i][j] == 1) {
                        dp[i][j] = Math.min(Math.min(dp[i-1][j], dp[i][j-1]), dp[i-1][j-1]) + 1;
                    }
                }
                count += dp[i][j];
            }
        }
        return count;
    }
}