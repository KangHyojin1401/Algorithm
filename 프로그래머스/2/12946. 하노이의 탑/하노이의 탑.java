import java.util.*;

class Solution {
    ArrayList<int[]> route;
    
    public int[][] solution(int n) {
        route = new ArrayList<>();
        hanoi(n, 1, 2, 3);
        
        int size = route.size();
        int[][] answer = new int[size][];
        for (int i = 0; i < size; i++) {
            answer[i] = route.get(i);
        }
        
        return answer;
    }
    
    public void hanoi(int n, int from, int via, int to) {
        if (n == 0) {
            return;
        }
        
        hanoi(n - 1, from, to, via);
        route.add(new int[]{from, to});
        hanoi(n - 1, via, from, to);
    }
}