// Given a grid where each entry is only 0 or 1, find the number of corner rectangles.
//
//A corner rectangle is 4 distinct 1s on the grid that form an axis-aligned rectangle. Note that only the corners need to have the value 1. Also, all four 1s used must be distinct.

import java.util.HashMap;

// Use a HashMap to store <Pair of 1s at previous row, Number of that pair>
// Rewrite hashCode() and equals() when using Pair as key in hashmap
// If there is a pair of 1s, say [0, 1] at a certain row, and it is in the map with value of 2, that means the current row can form 2 more rectangles, sum up and increase the value by 1 (the current row)
// Find all pair of 1s at each row and check the hashmap
// Return the final count
// Time: O(mn^2), Space: O(n^2) // All pair of 1s at a row of length n
class Solution {
    public static void main(String[] args) {
        int [][] grid = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        System.out.println(new Solution().countCornerRectangles(grid));
    }

    class Pair {
        int x;
        int y;
        public Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int hashCode() {
            final int prime = 31;
            return this.x * prime + this.y;
        }

        public boolean equals(Object obj) {
            if(this == obj) {
                return true;
            }
            if(obj == null || this.getClass() != obj.getClass()) {
                return false;
            }
            Pair other = (Pair) obj;
            if(this.x != other.x || this.y != other.y) {
                return false;
            }
            return true;
        }
    }

    public int countCornerRectangles(int[][] grid) {
        HashMap<Pair, Integer> map = new HashMap();
        int count = 0;
        for(int [] row : grid) {
            for(int i = 0; i < row.length-1; i++) {
                if(row[i] == 1) {
                    for(int j = i+1; j < row.length; j++) {
                        if(row[j] == 1) {
                            Pair p = new Pair(i, j);
                            map.putIfAbsent(p, 0);

                            int numOfPrevPairs = map.get(p);
                            count += numOfPrevPairs;
                            map.put(p, numOfPrevPairs + 1);
                        }
                    }
                }
            }
        }

        return count;
    }
}
