// Given a square grid of integers arr, a falling path with non-zero shifts is a choice of exactly one element from each row of arr, such that no two elements chosen in adjacent rows are in the same column.
// Return the minimum sum of a falling path with non-zero shifts.

// DP
// Utilize the original matrix as dp, fill the matrix from the 2nd last row to the top
// At each position, get the min from next row's column 0 to n-1 except the same column as j, then plus current value
// Return the min value from the first row
// Time: O(n^3), Space: O(n^2)
class Solution {
    public static void main(String[] args) {
        int [][] m = {{1,2,3},{4,5,6},{7,8,9}};
        System.out.println(new Solution().minFallingPathSum(m));
    }
    public int minFallingPathSum(int[][] arr) {
        int n = arr.length;
        for(int i = n-2; i >= 0; i--) {
            for(int j = 0; j < n; j++) {
                int min = Integer.MAX_VALUE;
                for(int k = 0; k < n; k++) {
                    if(j != k) {
                        min = Math.min(min, arr[i+1][k]);
                    }
                }
                arr[i][j] += min;
            }
        }
        int res = Integer.MAX_VALUE;
        for(int path : arr[0]) {
            res = Math.min(res, path);
        }
        return res;
    }
}
