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
        System.out.println(new Solution().canPartition(new int[]{1, 5, 11, 5}));
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
