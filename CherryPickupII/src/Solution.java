// Given a rows x cols matrix grid representing a field of cherries. Each cell in grid represents the number of cherries that you can collect.
//
// You have two robots that can collect cherries for you, Robot #1 is located at the top-left corner (0,0) , and Robot #2 is located at the top-right corner (0, cols-1) of the grid.
//
// Return the maximum number of cherries collection using both robots  by following the rules below:
//
// From a cell (i,j), robots can move to cell (i+1, j-1) , (i+1, j) or (i+1, j+1).
// When any robot is passing through a cell, It picks it up all cherries, and the cell becomes an empty cell (0).
// When both robots stay on the same cell, only one of them takes the cherries.
// Both robots cannot move outside of the grid at any moment.
// Both robots should reach the bottom row in the grid.

// DP: Top Down
// Memoization: dp[m][n][n] represents the max berry can get at row m, col n(robot1) and col n (robot2)
// Base Case:
// a. When column out of bound, return 0
// b. When cache hit, return it
// c. When reach the last row, return current sum
// At each level, try all possilbe combinations for next col1 and col2, and get max from their child
// Return the max plus berries at current node, update dp and return it
// Time: O(mn^2), Space: O(m^2)
class Solution {
    public static void main(String[] args) {
        int [][] grid = {{3,1,1},{2,5,1},{1,5,5},{2,1,1}};
        System.out.print(new Solution().cherryPickup(grid));
    }
    public int cherryPickup(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int [][][] dp = new int[m][n][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < n; k++) {
                    dp[i][j][k] = -1;   // Initialize as -1
                }
            }
        }

        return helper(0, 0, n-1, grid, dp);
    }

    private int helper(int row, int col1, int col2, int [][] grid, int [][][] dp) {
        if(col1 < 0 || col1 >= grid[0].length || col2 < 0 || col2 >= grid[0].length) {
            return 0;
        }
        else if(dp[row][col1][col2] != -1) {
            return dp[row][col1][col2];
        }
        else if(row == grid.length - 1) {
            dp[row][col1][col2] = col1 == col2 ? grid[row][col1] : grid[row][col1] + grid[row][col2];
            return dp[row][col1][col2];
        }

        int curSum = col1 == col2 ? grid[row][col1] : grid[row][col1] + grid[row][col2];
        int max = 0;
        for(int i = col1 - 1; i <= col1 + 1; i++) {
            for(int j = col2 - 1; j <= col2 + 1; j++) {
                max = Math.max(max, helper(row + 1, i, j, grid, dp));
            }
        }

        dp[row][col1][col2] = max + curSum;
        return max + curSum;
    }
}
