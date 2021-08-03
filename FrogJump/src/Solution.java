/*
DP - Memoization
Create Boolean matrix memo[stones.length][stones.length]. memo[i][j] represents at index i, can jump j steps reaches it?

Base Case:
    - cache hit: memo[curIndex][jump] is not null, return it

Recursion Case:
    - Get the index of stones[curIndex] + jump - 1. If it exist and can be reached, memo[curIndex][jump - 1] = true
    - Get the index of stones[curIndex] + jump. If it exist and can be reached, memo[curIndex][jump] = true
    - Get the index of stones[curIndex] + jump + 1. If it exist and can be reached, memo[curIndex][jump + 1] = true

    - Check if curIndex is the last index of stones array? If so, set memo[curIndex][jump] to true since we have found the answer; Otherwise, set it to false
    - Return memo[curIndex][jump] at last

    Time: O(N^2logN), Space: O(N^2)
*/

class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().canCross(new int[]{0,1,3,5,6,8,12,17}));
    }
    Boolean [][] memo;
    public boolean canCross(int[] stones) {
        memo = new Boolean[stones.length][stones.length];

        return helper(0, 0, stones);
    }

    public boolean helper(int curIndex, int jump, int [] stones) {
        if(memo[curIndex][jump] != null) {
            return memo[curIndex][jump];
        }

        // jump - 1
        int index1 = binarySearch(stones[curIndex] + jump - 1, curIndex + 1, stones.length - 1, stones);
        if(index1 != -1 && helper(index1, jump - 1, stones)) {
            memo[index1][jump-1] = true;
            return true;
        }

        // jump
        int index2 = binarySearch(stones[curIndex] + jump, curIndex + 1, stones.length - 1, stones);
        if(index2 != -1 && helper(index2, jump, stones)) {
            memo[index2][jump] = true;
            return true;
        }

        // jump + 1
        int index3 = binarySearch(stones[curIndex] + jump + 1, curIndex + 1, stones.length - 1, stones);
        if(index3 != -1 && helper(index3, jump + 1, stones)) {
            memo[index3][jump + 1] = true;
            return true;
        }

        memo[curIndex][jump] = curIndex == stones.length - 1 ? true : false;
        return memo[curIndex][jump];
    }

    public int binarySearch(int target, int left, int right, int [] stones) {
        while(left <= right) {
            int mid = left + (right - left) / 2;
            if(stones[mid] == target) {
                return mid;
            }
            else if(stones[mid] > target) {
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
