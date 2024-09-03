class Solution {
    public int[] solution(int n, long left, long right) {
        
//         int[][] arr = new int[n][n];
//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j <= i; j++) {
//                 arr[i][j] = i + 1;
//             }
            
//             for (int j = 0; j <= i; j++) {
//                 arr[j][i] = i + 1;
//             }
//         }
        
        int len = (int) (right - left) + 1;
        int[] answer = new int[len];
        
        for (long i = left; i <= right; i++) {
            int x = (int)(i / n);
            int y = (int)(i % n);
            
            int num;
            if (y <= x) {
                num = x + 1;
            } else {
                num = y + 1;
            }
            
            answer[(int)(i - left)] = num;
        }
        
        return answer;
    }
}