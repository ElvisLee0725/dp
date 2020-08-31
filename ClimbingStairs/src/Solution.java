// You are climbing a stair case. It takes n steps to reach to the top.
//Each time you can either climb 1 or 2 steps. In how many distinct ways can you climb to the top?

// DP
// In order to reach stair case i, we can either take a step of 1 from i-1 or step of 2 from i-2. Therefore, to reach i we sum up i-1 and i-2
// Base Case: 1 if stair case is 1. 2 if stair case is 2
// Use 2 variables: prev2 = 1, prev2 = 2. For i = 3, i <= n, res = prev2 + prev1
// Assign prev1 to prev2, prev1 = res; Then return res

// Time: O(n), Space: O(1)
public class Solution {
    public static void main(String[] args) {
        System.out.print(new Solution().climbStairs(5));
    }

    public int climbStairs(int n) {
        if(n == 1) {
            return 1;
        }
        else if(n == 2) {
            return 2;
        }
        int prev2 = 1;
        int prev1 = 2;
        int res = 0;
        for(int i = 3; i <= n; i++) {
            res = prev2 + prev1;
            prev2 = prev1;
            prev1 = res;
        }
        return res;
    }
}
