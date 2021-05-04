// You are given an integer array nums. You can choose exactly one index (0-indexed) and remove the element. Notice that the index of the elements may change after the removal.
//
//For example, if nums = [6,1,7,4,1]:
//
//Choosing to remove index 1 results in nums = [6,7,4,1].
//Choosing to remove index 2 results in nums = [6,1,4,1].
//Choosing to remove index 4 results in nums = [6,1,7,4].
//An array is fair if the sum of the odd-indexed values equals the sum of the even-indexed values.

//Return the number of indices that you could choose such that after the removal, nums is fair.

// Create arrays even and odd for prefix sum of even and odd index
// Ex. nums = [2,1,6,4]
//     even = [2,2,8,8]
//     odd  = [0,1,1,5]
// Iterate each element of the input array. The sum after deleted index will switch. Ex. even sum will become odd sum
// Get the even sum and odd sum for each index:
// evenSum: even[i-1] + odd[last index] - odd[i] 
// oddSum: odd[i-1] + even[last index] - even[i]
// If evenSum equals oddSum, count++
// Return count at the end
// Time: O(n), Space: O(n)
class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().waysToMakeFair(new int[]{1, 1, 1}));
    }
    public int waysToMakeFair(int[] nums) {
        int n = nums.length;
        int [] even = new int[n];
        int [] odd = new int[n];
        for(int i = 0; i < n; i++) {
            if(i == 0) {
                even[i] = nums[0];
                odd[i] = 0;
            }
            else {
                even[i] = i % 2 == 0 ? even[i-1] + nums[i] : even[i-1];
                odd[i] = i % 2 == 1 ? odd[i-1] + nums[i] : odd[i-1];
            }
        }

        int count = 0;
        for(int i = 0; i < n; i++) {
            int evenSum = (i > 0 ? even[i-1] : 0) + odd[n-1] - odd[i];
            int oddSum = (i > 0 ? odd[i-1] : 0) + even[n-1] - even[i];

            if(evenSum == oddSum) {
                count++;
            }
        }
        return count;
    }
}