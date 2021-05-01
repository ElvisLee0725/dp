// Given a non-empty array nums containing only positive integers, find if the array can be partitioned into two subsets such that the sum of elements in both subsets is equal.

// DP: Memoization
// If this array can be partioned, its sum should be even, and there should exist a subset that sums up to half of sum
// Boolean dp[index][sum]. dp[i][j] represents if at index i there exist a sum of j
// Base Case:
// 1. If target is 0, return true => Find a subset that equals half of sum
// 2. If target is less than 0, return false
// 3. If dp[index][sum] is not null, return dp[index][sum] (cache hit)
// Get dp[index][sum] by calling recursion functions of get value at current index or not
// Return dp[index][sum]
// Time: O(mn), Space: O(mn) while m is sum / 2 and n is length of input array
class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().canPartition2(new int[]{1, 5, 11, 5}));
    }

    // DP: Tabulation
    // Create a boolean [][] dp, dp[i][j] means if we pick from index i, can we form a target number at j?
    // Initialize: dp[i][0] is true. Since target is 0, then there is no need to choose a value, it must be true. When index reaches the length of nums, it must be false except target is 0
    // Fill table from bottom to top, left to right
    // dp[i][j]
    // Case 1: Cur number nums[i] is greater than target, cannot choose current value, return dp[i+1][j]
    // Case 2: Choose value at cur index: dp[i+1][j - nums[i]] or not choose current value: dp[i+1][j]
    // Finally, return dp[0][sum / 2] => Start from index 0, we can form target of sum / 2
    // Time: O(mn), Space: O(mn), while m is sum / 2
    public boolean canPartition2(int[] nums) {
        int sum = 0;
        for(int n : nums) {
            sum += n;
        }
        if(sum % 2 == 1) {
            return false;
        }
        sum /= 2;
        boolean [][] dp = new boolean[nums.length + 1][sum + 1];

        // Initialize:
        for(int i = 0; i < dp.length; i++) {
            dp[i][0] = true;
        }

        for(int i = dp.length - 2; i >= 0; i--) {
            for(int j = 0; j < dp[0].length; j++) {
                if(nums[i] > j) {
                    dp[i][j] = dp[i+1][j];
                }
                else {
                    dp[i][j] = dp[i+1][j - nums[i]] || dp[i+1][j];
                }
            }
        }
        return dp[0][sum];
    }
    private Boolean [][] dp;
    public boolean canPartition(int[] nums) {
        int sum = 0;
        for(int n : nums) {
            sum += n;
        }
        if(sum % 2 == 1) {
            return false;
        }
        dp = new Boolean[nums.length][(sum / 2) + 1];
        return helper(0, sum / 2, nums);
    }

    private boolean helper(int index, int target, int [] nums) {
        if(target == 0) {
            return true;
        }
        else if(target < 0) {
            return false;
        }
        // dp[][] must be of "Boolean" type
        else if(dp[index][target] != null) {
            return dp[index][target];
        }

        boolean result = false;
        if(index + 1 < nums.length) {
            result = helper(index + 1, target - nums[index], nums) || helper(index + 1, target, nums);
        }

        dp[index][target] = result;
        return dp[index][target];
    }
}
