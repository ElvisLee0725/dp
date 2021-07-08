// DP
// Button up approach:
// Base Case:
// When i reaches the end, return 0
// Recursive Case:
// If current word can be formed, try a. use current word b. Don't use current word
// Else, just not use current word
// Return the max of result 
// Time: O(N), Space: O(N)
class Solution {
    public static void main(String[] args) {
        String [] words = {"dog","cat","dad","good"};
        char [] letters = {'a','a','c','d','d','d','g','o','o'};
        int [] score = {1,0,9,5,0,0,3,0,0,0,0,0,0,0,2,0,0,0,0,0,0,0,0,0,0,0};
        System.out.println(new Solution().maxScoreWords(words, letters, score));
    }
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        // Use an array of length 26 to store the freqency of each letter
        int [] freq = new int[26];
        for(int i = 0; i < letters.length; i++) {
            freq[letters[i] - 'a']++;
        }

        return helper(0, words, freq, score);
    }


    private int helper(int i, String [] words, int [] freq, int [] score) {
        if(i == words.length) {
            return 0;
        }

        int max = 0;
        String s = words[i];

        if(canForm(s, freq)) {
            // a. Select current word
            select(s, freq);
            int curScore = 0;
            for(int j = 0; j < s.length(); j++) {
                curScore += score[s.charAt(j) - 'a'];
            }
            max = Math.max(max, helper(i+1, words, freq, score) + curScore);
            // b. Unselect current word
            unSelect(s, freq);
            max = Math.max(max, helper(i+1, words, freq, score));
        }
        else {
            max = Math.max(max, helper(i+1, words, freq, score));
        }
        return max;
    }

    private void select(String s, int [] freq) {
        for(int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']--;
        }
    }

    private void unSelect(String s, int [] freq) {
        for(int i = 0; i < s.length(); i++) {
            freq[s.charAt(i) - 'a']++;
        }
    }

    private boolean canForm(String word, int [] freq) {
        int [] wordFreq = new int[26];
        for(int i = 0; i < word.length(); i++) {
            wordFreq[word.charAt(i) - 'a']++;
        }

        for(int i = 0; i < 26; i++) {
            if(wordFreq[i] > freq[i]) {
                return false;
            }
        }
        return true;
    }
}