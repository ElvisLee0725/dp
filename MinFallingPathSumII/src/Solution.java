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
        System.out.println(new Solution().minFallingPathSum2(m));
    }

    // DP Solution:
    // Fill the matrix from top to bottom, left to right
    // At each position [i, j], get the min and 2nd min from previous row. int [] min = [Index of min at previous row, Value of min at previous row]
    // Then, check if current index equals to the index of the min value's index? If so, sum up current value with min. Else, sum up with 2nd min
    // Keep doing until the table is filled
    // Get the min value from last row of matrix, return it.
    // Time: O(n^2), Space: O(1)
    public int minFallingPathSum2(int[][] arr) {
        int n = arr.length;
        for(int i = 1; i < n; i++) {
            int [] min = new int[]{0, Integer.MAX_VALUE};
            for(int k = 0; k < n; k++) {
                if(arr[i-1][k] < min[1]) {
                    min[1] = arr[i-1][k];
                    min[0] = k;
                }
            }

            int [] min2 = new int[]{0, Integer.MAX_VALUE};
            for(int k = 0; k < n; k++) {
                if(k != min[0] && arr[i-1][k] < min2[1]) {
                    min2[1] = arr[i-1][k];
                    min2[0] = k;
                }
            }

            for(int j = 0; j < n; j++) {
                if(j != min[0]) {
                    arr[i][j] += min[1];
                }
                else {
                    arr[i][j] += min2[1];
                }
            }
        }

        int res = Integer.MAX_VALUE;
        for(int num : arr[n-1]) {
            res = Math.min(res, num);
        }
        return res;
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
