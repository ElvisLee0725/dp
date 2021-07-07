// DP: Memoization
// memo[n][eggs], Ex. memo[3][1] is the min number of moves when there are n floors with 1 egg remaining
// Base Case:
// a. n is 1 or 0, return 1 or 0
// b. If egg is 1, return n (Try from 1 to n when only 1 egg left)
// c. Cache hit, return memo[n][egg]
// Recursive Case:
// Find the min from all possible i where 0 < i < n
// 1. Egg broke at level i, go n = i - 1, egg - 1
// 2. Egg didn't break at level i, go n = n - i
// Time: O(n), Space: O(n)
class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().twoEggDrop(2));
    }
    int [][] memo;
    public int twoEggDrop(int n) {
        memo = new int[n+1][3];
        for(int i = 0; i < memo.length; i++) {
            for(int j = 0; j < memo[0].length; j++) {
                if(j == 0) {
                    memo[i][j] = 0;
                }
                else {
                    memo[i][j] = -1;
                }
            }
        }
        return helper(n, 2);
    }

    private int helper(int n, int egg) {
        if(n <= 1) {
            return n;
        }
        else if(egg == 1) {
            return n;
        }
        else if(memo[n][egg] != -1) {
            return memo[n][egg];
        }

        int tmp = Integer.MAX_VALUE;
        for(int i = 1; i < n; i++) {
            tmp = Math.min(tmp, 1 + Math.max(helper(i-1, egg-1), helper(n-i, egg)));
        }
        memo[n][egg] = tmp;
        return memo[n][egg];
    }
}
