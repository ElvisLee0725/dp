// You want to schedule a list of jobs in d days. Jobs are dependent (i.e To work on the i-th job, you have to finish all the jobs j where 0 <= j < i).
//
//You have to finish at least one task every day. The difficulty of a job schedule is the sum of difficulties of each day of the d days. The difficulty of a day is the maximum difficulty of a job done in that day.
//
//Given an array of integers jobDifficulty and an integer d. The difficulty of the i-th job is jobDifficulty[i].
//
//Return the minimum difficulty of a job schedule. If you cannot find a schedule for the jobs return -1.

// DP
// dp[n+1][d+1], dp[i][j] represents the min difficulty from job i to n with day j to finish
// Initialize: Set column 0 to MAX_VALUE except dp[n][0] as 0
// Fill the dp matrix from left to right, bottom up
// At each dp[i][j], set it to MAX_VALUE and get curMaxDiff from jobDifficulty[i]
// For each k starts from i to n - j, get the max of curMaxDiff and jobDifficulty[k]. Then, if dp[k+1][j-1] + curMaxDiff is less than dp[i][j], update the value
// Keep doing and return dp[0][d]
// Time: O(n^2*d), Space: O(nd)
class Solution {
    public static void main(String[] args) {
        int [] arr = new int[]{7,1,7,1,7,1};
        System.out.print(new Solution().minDifficulty(arr, 3));
    }
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        // Edge Case:
        if(n < d) {
            return -1;
        }
        int [][] dp = new int[n+1][d+1];
        for(int i = 0; i < n; i++) {
            dp[i][0] = Integer.MAX_VALUE;
        }

        for(int j = 1; j < dp[0].length; j++) {
            for(int i = n - j; i >= 0; i--) {
                int curMaxDiff = jobDifficulty[i];
                dp[i][j] = Integer.MAX_VALUE;
                for(int k = i; k <= n - j; k++) {
                    curMaxDiff = Math.max(curMaxDiff, jobDifficulty[k]);
                    if(dp[k+1][j-1] != Integer.MAX_VALUE) {
                        dp[i][j] = Math.min(dp[i][j], curMaxDiff + dp[k+1][j-1]);
                    }
                }
            }
        }
        return dp[0][d];
    }
}