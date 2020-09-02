// Given an array of non-negative integers, you are initially positioned at the first index of the array.
//Each element in the array represents your maximum jump length at that position.
//Determine if you are able to reach the last index.

// DP Solution
// boolean [] M, M[i] represents if i can be reached, M[0] = true
// Use a variable to record the max position that index i and before can jump to
// Loop the input array, if M[i] is true and i + nums[i] > maxPos, set from i to M[i+j] to true and update maxPos
// Return M[last index]

public class Solution {
    public static void main(String[] args) {
        int [] nums = new int[]{3,2,1,0,4};
        System.out.print(new Solution().canJump(nums));
    }

    public boolean canJump(int [] nums) {
        if(nums == null || nums.length <= 1) {
            return true;
        }

        boolean [] M = new boolean[nums.length];
        M[0] = true;
        int maxPos = 0;

        for(int i = 0; i < nums.length; i++) {
            if(M[i] && i + nums[i] > maxPos) {
                int j = 1;
                while (j <= nums[i] && i + j < nums.length) {
                    M[i + j] = true;
                    j++;
                }
                maxPos = i + nums[i];
            }
        }
        return M[M.length-1];
    }
}
