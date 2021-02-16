// Given a 2D grid of 0s and 1s, return the number of elements in the largest square subgrid that has all 1s on its border, or 0 if such a subgrid doesn't exist in the grid.

// Use 2 matrix to record the consecutive 1s from left to right and top to bottom
// Iterate the input grid, if the current grid[i][j] is 1, update the value in both matrices
// Then, find the max length of edge. Set the max length starts from Math.min(lr[i][j], tb[i][j]), 
// If lr[i-maxLen+1][j] is greater or equal to max length and tb[i][j-maxLen+1] >= maxLen, we found the max length of edge, update the result with the maxLen^2 and break
// Return the result
// Time: O(n^3), Space: O(n^2)
class Solution {
    public static void main(String[] args) {
        int [][] m = new int[][]{{1,1,1},{1,0,1},{1,1,1}};
        System.out.print(new Solution().largest1BorderedSquare(m));
    }
    public int largest1BorderedSquare(int[][] grid) {
        int row = grid.length;
        int col = grid[0].length;
        int [][] lr = new int[row+1][col+1];
        int [][] tb = new int[row+1][col+1];

        int res = 0;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                if(grid[i][j] == 1) {
                    lr[i+1][j+1] = lr[i+1][j+1-1] + 1;
                    tb[i+1][j+1] = tb[i+1-1][j+1] + 1;
                    for(int maxLen = Math.min(lr[i+1][j+1], tb[i+1][j+1]); maxLen >= 1; maxLen--) {
                        if(lr[i+1-maxLen+1][j+1] >= maxLen && tb[i+1][j+1-maxLen+1] >= maxLen) {
                            res = Math.max(res, maxLen * maxLen);
                            break;
                        }
                    }
                }
            }
        }
        return res;
    }
}