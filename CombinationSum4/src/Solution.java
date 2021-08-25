// Given an array of distinct integers nums and a target integer target, return the number of
// possible combinations that add up to target.
// The answer is guaranteed to fit in a 32-bit integer.

/*
DP - Memoization
Create an array memo[i] represents the number of possible combination when cur number is i

Base Case:
 - If current value is 0, return 1
 - If memo[curVal] is not null, return memo[curVal]

Recursion Case:
 - Try all values from nums array
 - Get the sum from all of the returned value
 - memo[curVal] = sum and return it

Time: O(TN), while T is target and N is the length of nums array, Space: O(T)
*/

class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().combinationSum4(new int[]{1, 2, 3}, 4));
    }
    Integer [] memo;
    public int combinationSum4(int[] nums, int target) {
        memo = new Integer[target+1];
        return helper(nums, target);
    }

    public int helper(int [] nums, int curVal) {
        if(curVal == 0) {
            return 1;
        }
        else if(memo[curVal] != null) {
            return memo[curVal];
        }

        int sum = 0;
        for(Integer num : nums) {
            if(curVal - num >= 0) {
                sum += helper(nums, curVal - num);
            }
        }
        memo[curVal] = sum;
        return memo[curVal];
    }
}
