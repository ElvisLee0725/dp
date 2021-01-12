// Given a string, a partitioning of the string is a palindrome partitioning if every substring of the
// partition is a palindrome. Determine the fewest cuts needed for a palindrome partitioning of a given string.

// “a | babbbab | bab | aba” is a palindrome partitioning of “ababbbabbababa”.
// The minimum number of cuts needed is 3.
public class Solution {
    public static void main(String[] args) {
        System.out.print(new Solution().minCuts("ababbbabbababa"));
    }

    // Create a int [] minCut, minCut[i] represents the minimum cuts needed for substring 0 to i
    // Create a boolean [][] isPal, isPal[i][j] represents if substring from i to j is a palindrome
    // Use 2 nested for loops to iterate the range combination from i to j, firstly, check if this is a palindrome
    // Secondly, if this range is a palindrome, try to update the minCut[j]
    public int minCuts(String input) {
        int [] minCut = new int[input.length()];
        boolean [][] isPal = new boolean[input.length()][input.length()];

        for(int j = 0; j < input.length(); j++) {
            minCut[j] = j;
            for(int i = j; i >= 0; i--) {
                if(i == j) {
                    isPal[i][j] = true;
                }
                else if(i == j - 1) {
                    isPal[i][j] = input.charAt(i) == input.charAt(j);
                }
                else {
                    isPal[i][j] = input.charAt(i) == input.charAt(j) && isPal[i+1][j-1];
                }

                if(isPal[i][j]) {
                    if(i == 0) {
                        // Substring from 0 to j is a palindrome, then we don't need to cut
                        minCut[j] = 0;
                    }
                    else {
                        // Check if substring before i's minCut plus 1 is smaller than current min cut
                        minCut[j] = Math.min(minCut[j], minCut[i-1] + 1);
                    }
                }
            }

        }
        return minCut[minCut.length-1];

    }
}
