// In a country popular for train travel, you have planned some train travelling one year in advance.  The days of the year that you will travel is given as an array days.  Each day is an integer from 1 to 365.

// Train tickets are sold in 3 different ways:

// a 1-day pass is sold for costs[0] dollars;
// a 7-day pass is sold for costs[1] dollars;
// a 30-day pass is sold for costs[2] dollars.
// The passes allow that many days of consecutive travel.  For example, if we get a 7-day pass on day 2, then we can travel for 7 days: day 2, 3, 4, 5, 6, 7, and 8.

// Return the minimum number of dollars you need to travel every day in the given list of days

import java.util.HashSet;
import java.util.Set;

// DP: Memoization
// Create a 1-d array, dp[i] represents the min cost from days i (the index at days array) to the end of plan
// What kind of pass should I buy?
// dp[i] = The min of a. 1 day pass + dp[i+1], b. 7 day pass + dp[i+7], c. 30 day pass + dp[i+30]
// Base Case:
// If i is greater or equal to 366, return 0
// Else if dp[i] is not null, return dp[i]
// At each i, if need to travel, get the min from costs[0] + dp[i+1], costs[1] + dp[i+7] and costs[2] + dp[i+30]
// If the current day doesn't need to travel, dp[i] = dp[i+1]
// return dp[i]
// At the end, return dp[days[0]]
// Time: O(W), W is 365. Space: O(W)
class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().mincostTickets(new int[]{1,4,6,7,8,20}, new int[]{2, 7, 15} ));
    }
    Integer [] dp;
    public int mincostTickets(int[] days, int[] costs) {
        dp = new Integer[366];
        Set<Integer> travelDays = new HashSet();
        for(int n : days) {
            travelDays.add(n);
        }

        return helper(days[0], travelDays, costs);
    }

    private int helper(int i, Set<Integer> travelDays, int [] costs) {
        if(i >= 366) {
            return 0;
        }
        else if(dp[i] != null) {
            return dp[i];
        }

        int curMin = Integer.MAX_VALUE;
        if(travelDays.contains(i)) {
            curMin = Math.min(costs[0] + helper(i+1, travelDays, costs), Math.min(costs[1] + helper(i+7, travelDays, costs), costs[2] + helper(i+30, travelDays, costs)));
        }
        else {
            curMin = helper(i+1, travelDays, costs);
        }
        dp[i] = curMin;
        return dp[i];
    }
}
