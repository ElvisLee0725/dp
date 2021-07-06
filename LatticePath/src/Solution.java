public class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().latticePaths(2, 3));
    }

    static int [][] memo;
    public int latticePaths(int m, int n) {
        //YOUR WORK HERE
        memo = new int[m+1][n+1];
        for(int i = 0; i < memo.length; i++) {
            for(int j = 0; j < memo[0].length; j++) {
                if(i == 0 || j == 0) {
                    memo[i][j] = 1;
                }
                else {
                    memo[i][j] = -1;
                }
            }
        }
        helper(m, n);
        return memo[m][n];
    }

    public int helper(int i, int j) {
        if(memo[i][j] != -1) {
            return memo[i][j];
        }
        memo[i][j] = helper(i - 1, j) + helper(i, j - 1);
        return memo[i][j];
    }
}
