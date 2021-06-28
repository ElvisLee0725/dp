public class Solution {
    //          5
    //         /
    //       4
    //      /   \
    //     3    2
    //   /  \  /  \
    //  2   1 1    1
    //  /\
    // 1  1
    // O(3^N)
    // Optimize: DP Memoization
    // Create an array memo[] with length num + 1, memo[i] is the shortest path from i to 1. memo[1] = 0
    // Ex. [0, 0, -1, -1, -1] , num is 5
    // Base Case:
    // If number is less than 1, return 0
    // If cache hit, return memo[number]
    // Recursive Case:
    // Get the min from number - 1, number / 3 and number / 2 as memo[number], and plus it by 1 step
    // return memo[number]
    // Time: O(N), Space: O(N)
    // To prevent stack overflow error, use tabulation!
    public static void main(String[] args) {
        System.out.println(new Solution().minimumStepsToOne(10));
    }
    static int [] dp;
    public int minimumStepsToOne(int num) {
        dp = new int[num+1];
        for(int i = 2; i < dp.length; i++) {
            dp[i] = 1 + Math.min(dp[i-1], Math.min(i % 2 == 0 ? dp[i/2] : Integer.MAX_VALUE, i % 3 == 0 ? dp[i/3] : Integer.MAX_VALUE));
        }

        return dp[num];
    }
}


