/*
DP
- Base Case:
    - mouse == cat, return CAT
    - mouse == 0, return MOUSE
    - turn >= 2 * len, return DRAW
    - Cache hit, return memo[mouse][cat][turn]

- Recursion Case:
    - Mouse turn
        - Try all possible next node, if the return is MOUSE, return it!
        - If a DRAW is there, set tie to true and move on to the next
        - The worst is return CAT
    - Cat turn
        - Try all possible next node, if the return is CAT, return it!
        - If a DRAW is there, set tie to true and move on to the next
        - The worst is return MOUSE
*/

class Solution {
    public static void main(String[] args) {
        System.out.println(new Solution().catMouseGame(new int[][]{{2,5},{3},{0,4,5},{1,4,5},{2,3},{0,2,3}}));
    }
    public static final int MOUSE = 1;
    public static final int CAT = 2;
    public static final int DRAW = 0;
    Integer [][][] memo;

    public int catMouseGame(int[][] graph) {
        int len = graph.length;
        memo = new Integer[len][len][2*len];

        return helper(1, 2, 0, graph);
    }

    public int helper(int mouse, int cat, int turn, int [][] graph) {
        if(mouse == cat) {
            return CAT;
        }
        else if(mouse == 0) {
            return MOUSE;
        }
        else if(turn >= 2 * graph.length) {
            return DRAW;
        }
        else if(memo[mouse][cat][turn] != null) {
            return memo[mouse][cat][turn];
        }

        boolean possibleTie = false;
        // Mouse turn:
        if(turn % 2 == 0) {
            for(int nei : graph[mouse]) {
                // Mouse don't go to cat
                if(nei == cat) {
                    continue;
                }
                int tmp = helper(nei, cat, turn + 1, graph);
                if(tmp == MOUSE) {
                    memo[mouse][cat][turn] = MOUSE;
                    return MOUSE;
                }
                else if(tmp == DRAW) {
                    possibleTie = true;
                }
            }

            if(possibleTie) {
                memo[mouse][cat][turn] = DRAW;
                return DRAW;
            }
            else {
                memo[mouse][cat][turn] = CAT;
                return CAT;
            }
        }
        // Cat turn:
        else {
            for(int nei : graph[cat]) {
                // Cat can't go to hole
                if(nei == 0) {
                    continue;
                }
                int tmp = helper(mouse, nei, turn + 1, graph);
                if(tmp == CAT) {
                    memo[mouse][cat][turn] = CAT;
                    return CAT;
                }
                else if(tmp == DRAW) {
                    possibleTie = true;
                }
            }

            if(possibleTie) {
                memo[mouse][cat][turn] = DRAW;
                return DRAW;
            }
            else {
                memo[mouse][cat][turn] = MOUSE;
                return MOUSE;
            }
        }
    }
}
