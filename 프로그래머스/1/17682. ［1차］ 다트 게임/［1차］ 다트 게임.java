import java.util.*;

class Solution {
    public int solution(String dartResult) {
        char[] arr = dartResult.toCharArray();
        int len = arr.length;
        
        int total = 0;
        int prevScore = 0;
        int curScore = 0;
        
        for (int i = 0; i < len; i++) {
            if (arr[i] >= '0' && arr[i] <= '9') { //숫자면
                //10인 경우 체크
                if (arr[i] == '1' && arr[i + 1] == '0') {
                    curScore = 10;
                    i++;
                } else {
                    curScore = arr[i] - '0';
                }
            } else { //문자면
                if (arr[i] == 'S') {
                    
                } else if (arr[i] == 'D') {
                    curScore = (int) Math.pow(curScore, 2);
                } else if (arr[i] == 'T') {
                    curScore = (int) Math.pow(curScore, 3);
                } else if (arr[i] == '*') {
                    total -= prevScore;
                    total += prevScore * 2;
                    curScore *= 2;
                } else if (arr[i] == '#') {
                    curScore = -curScore;
                }
                
                
                //뒤가 숫자거나 배열의 끝이면 
                if (i + 1 == len || i < len && (arr[i + 1] >= '0' && arr[i + 1] <= '9')) {
                    total += curScore;
                    prevScore = curScore;
                    curScore = 0;
                }
            }
            
           
        }
        
        return total;
    }
}