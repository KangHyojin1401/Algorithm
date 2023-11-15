/*
dfs로 했다가 시초
*/

import java.util.*;

class Solution {
//     public int max = -1;
//     public int depth;
    
//     public void dfs(int[][] triangle, int i, int j, int sum) {
//         if (i == depth) {
//             //최대값 계산
//             if (max < sum) {
//                 max = sum;
//             }
//             return;
//         }

//         dfs(triangle, i + 1, j, sum + triangle[i][j]);
//         dfs(triangle, i + 1, j + 1, sum + triangle[i][j]);
//     }
    
//     public int solution(int[][] triangle) {
//         depth = triangle.length;
        
//         dfs(triangle, 0, 0, 0);
        
//         return max;
//     }
    
    public int solution(int[][] triangle) {
        int len = triangle.length;
        
        //초기화
        int[][] dp = new int[len][];
        
        for (int i = 0; i < len; i++) {
            dp[i] = new int[triangle[i].length];
        }
        
        for (int i = 0; i < len; i++) {
            dp[len - 1][i] = triangle[len - 1][i];
        }
        
        
        //알고리즘
        for (int i = len - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                dp[i][j] = Math.max(dp[i + 1][j], dp[i + 1][j + 1]) + triangle[i][j];
            }
        }
            
        return dp[0][0];
    }
    

}