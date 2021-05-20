// Given a string s consisting of items as "*" and closed compartments as an open and close "|",
// an array of starting indices startIndices, and an array of ending indices endIndices,
// determine the number of items in closed compartments within the substring between the two indices, inclusive.
//
// An item is represented as an asterisk *
// A compartment is represented as a pair of pipes | that may or may not have items between them.
// Example:
// s = '|**|*|*'
// startIndices = [1,1]
// endIndices = [5,6]

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Use a array to store the number of items within compartments. Ex [0, 0, 0, 2], arr[3] is 2 since
// there are 2 items within |**|
// Iterate the input start and end indices:
// Check if startIndex - 1 is a '|'? If not, keep increase startIndex until meet one
// Check if endIndex is a '|'? If not, keep decrease endIndex until meet one
// Add to result with arr[endIndex] - arr[startIndex]
// Time: O(n), Space: O(m) while n is the length of start, end index array and m is length of string
public class Solution {
    public static void main(String[] args) {
        List<Integer> s = Arrays.asList(1, 1);
        List<Integer> e = Arrays.asList(5, 6);
        System.out.println(new Solution().numberOfItems("|**|*|*", s, e));
    }
    public List<Integer> numberOfItems(String s, List<Integer> startIndices, List<Integer> endIndices) {
        // Write your code here
        int [] arr = new int[s.length()];
        int count = 0;
        for(int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '|') {
                arr[i] = count;
            }
            else {
                count++;
            }
        }

        List<Integer> res = new ArrayList();
        for(int i = 0; i < startIndices.size(); i++) {
            int startIndex = startIndices.get(i);
            int endIndex = endIndices.get(i);
            while(startIndex < s.length() && s.charAt(startIndex) != '|') {
                startIndex++;
            }
            while(endIndex >= 0 && s.charAt(endIndex) != '|') {
                endIndex--;
            }

            if(startIndex >= endIndex) {
                res.add(0);
            }
            else {
                res.add(arr[endIndex] - arr[startIndex]);
            }
        }
        return res;
    }
}
