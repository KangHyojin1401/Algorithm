import java.util.*;

class Solution {
    public int solution(int k, int m, int[] score) {
        int len = score.length;
        
        //오름차순 정렬 후 뒤에서부터 m개씩 자른다.
        Arrays.sort(score);
        
        int answer = 0;
        
        for (int i = len - m; i >= 0; i -= m) {
            answer += score[i] * m;
        }
        
        return answer;
    }
}