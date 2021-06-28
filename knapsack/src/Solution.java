import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {
    public static void main(String[] args) {
        int [] weights = new int[]{10, 20, 30};
        int [] values = new int[]{60, 100, 120};
        System.out.println(new Solution().knapsack(weights, values, 50));

        int [][] items = {{1,2}, {4,3}, {5,6}, {6,7}};
        List<List<Integer>> res = new Solution().knapsackProblem(items, 10);
        System.out.println(res);
    }

    // Create dp matrix, dp[i][j] represents at capacity i with item j, the most value can fit is dp[i][j]
    // Fill the table from top down, left to right
    // If i >= weights[j], we compare whether to add current item j or not:
    // a. Add the item at index j + dp[i-weights[j]][j]
    // b. Don't add item at index j => dp[i][j-1]
    // Else, the current item's weight is too high, just assign 0 or dp[i][j-1]
    private int knapsack(int [] weights, int [] values, int capacity) {
        int [][] dp = new int[(capacity / 10)+1][weights.length+1];

        for(int i = 1; i < dp.length; i++) {
            for(int j = 1; j < dp[0].length; j++) {
                if(i * 10 >= weights[j-1]) {
                    int index = (i*10 - weights[j-1]) / 10;
                    int a = values[j-1] + dp[index][j-1];
                    int b = dp[i][j-1];
                    dp[i][j] = Math.max(a, b);
                }
                else {
                    dp[i][j] = dp[i][j-1];
                }
            }
        }
        return dp[dp.length-1][dp[0].length-1];
    }

    // Knapsack from AlgoExpert (Parameters are different)
    public List<List<Integer>> knapsackProblem(int[][] items, int capacity) {
        // Write your code here.
        // Replace the code below.
        memo = new int[items.length+1][capacity+1];
        // Initialize memo:
        for(int i = 0; i < memo.length; i++) {
            for(int j = 0; j < memo[0].length; j++) {
                if(i == 0 || j == 0) {
                    memo[i][j] = 0;
                }
                else {
                    memo[i][j] = -1;
                }
            }
        }

        helper(items, items.length, capacity);

        List<Integer> totalValue = Arrays.asList(memo[items.length][capacity]);
        List<Integer> finalItems = new ArrayList<>();

        int i = items.length;
        int c = capacity;
        while(i > 0) {
            if(memo[i][c] == memo[i-1][c]) {
                i--;
            }
            // When not match, that means we add this item
            else {
                finalItems.add(0, i - 1);
                c -= items[i-1][1];
                i--;
            }

            if(c == 0) break;
        }

        var result = new ArrayList<List<Integer>>();
        result.add(totalValue);
        result.add(finalItems);
        return result;
    }
    // Memoization:
    // memo[items.length+1][capacity+1]
    // Base Case:
    // capacity < 0, return 0
    // Cache hit, return memo[item][capacity]
    // Else, memo[item][capacity] = the max of adding current item and not adding it
    static int [][] memo;
    public static int helper(int [][] items, int i, int cap) {
        if(memo[i][cap] != -1) {
            return memo[i][cap];
        }

        // Check if current items weight is less than or equal to capacity
        if(items[i-1][1] <= cap) {
            memo[i][cap] = Math.max(items[i-1][0] + helper(items, i-1, cap - items[i-1][1]), helper(items, i-1, cap));
        }
        // Current item to heavy, just skip it
        else {
            memo[i][cap] = helper(items, i-1, cap);
        }
        return memo[i][cap];
    }
}
