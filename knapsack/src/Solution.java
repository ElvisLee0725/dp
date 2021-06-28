public class Solution {
    public static void main(String[] args) {
        int [] weights = new int[]{10, 20, 30};
        int [] values = new int[]{60, 100, 120};
        System.out.println(new Solution().knapsack(weights, values, 50));

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

        for(int i = 0; i < dp.length; i++) {
            for(int j = 0; j < dp[0].length; j++) {
                System.out.print(dp[i][j] + ", ");
            }
            System.out.println();
        }
        return dp[dp.length-1][dp[0].length-1];
    }
}
