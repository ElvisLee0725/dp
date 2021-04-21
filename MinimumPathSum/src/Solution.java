// dp[i][j] represents the min path sum from grid[0][0] to grid[i][j]
// dp[i][j] = grid[i][j] + Min of dp[i-1][j] (from top-down), dp[i][j-1] (from left-right)
// Return the bottom-right of the dp matrix
// Time: O(mn), Space: O(mn)
class Solution {
    public static void main(String[] args) {
        int [][] grid = {{1,3,1},{1,5,1},{4,2,1}};
        System.out.print(new Solution().minPathSum(grid));
    }
    public int minPathSum(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        int [][] dp = new int[r][c];
        for(int i = 0; i < r; i++) {
            for(int j = 0; j < c; j++) {
                if(i == 0 && j == 0) {
                    dp[i][j] = grid[i][j];
                }
                else if(i == 0) {
                    dp[i][j] = dp[i][j-1] + grid[i][j];
                }
                else if(j == 0) {
                    dp[i][j] = dp[i-1][j] + grid[i][j];
                }
                else {
                    dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
                }
            }
        }
        return dp[r-1][c-1];
    }
}
