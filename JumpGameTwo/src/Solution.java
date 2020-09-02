// Given an array of non-negative integers, you are initially positioned at the first index of the array.
// Each element in the array represents your maximum jump length at that position.
// Your goal is to reach the last index in the minimum number of jumps.
// You can assume that you can always reach the last index.

// DP Solution
// int [] M with every entry Integer.MAX_VALUE, M[i] represents minimum jumps to i. M[0] = 0
// In a for loop, at cur index, check in the range that nums[i] can reach, find min of M[i+j] and 1 + M[i]
// Return M[last index]

import java.util.Arrays;

public class Solution {
    public static void main(String[] args) {
        int [] nums = new int[]{2,3,1,1,4};
        System.out.println(new Solution().jump(nums));
    }

    public int jump(int[] nums) {
        if(nums == null || nums.length <= 1) {
            return 1;
        }

        int [] M = new int[nums.length];
        Arrays.fill(M, Integer.MAX_VALUE);
        M[0] = 0;
        for(int i = 0; i < nums.length; i++) {
            int j = 1;
            while(j <= nums[i] && i + j < nums.length) {
                M[i+j] = Math.min(M[i+j], 1 + M[i]);
                j++;
            }
        }
        return M[M.length-1];
    }
}
