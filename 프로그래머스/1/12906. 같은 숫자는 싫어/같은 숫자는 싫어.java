import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int len = arr.length;
        
        int[] answer = new int[len];
        int answerIdx = 0;
        
        answer[answerIdx++] = arr[0]; //초기화
    
        for (int i = 1; i < len; i++) {
            if (arr[i - 1] != arr[i]) {
                answer[answerIdx++] = arr[i];
            }
        }
        
        return Arrays.copyOf(answer, answerIdx);
    }
}