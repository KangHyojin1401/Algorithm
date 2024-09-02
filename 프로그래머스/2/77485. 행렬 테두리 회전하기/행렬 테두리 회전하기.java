class Solution {
    int[][] arr;
    int[] answer;
    int answerIdx = 0;
    
    public int[] solution(int rows, int columns, int[][] queries) {
        int rotateNum = queries.length;
        answer = new int[rotateNum];
        
        arr = new int[rows][columns];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= columns; j++) {
                arr[i - 1][j - 1] = (i - 1) * columns + j;
            }
        }
        
        for (int i = 0; i < rotateNum; i++) {
            int[] query = queries[i];
            rotateAndFindMin(query[0] - 1, query[1] - 1, query[2] - 1, query[3] - 1);
        }
        
        return answer;
    }
    
    public void rotateAndFindMin(int x1, int y1, int x2, int y2) {
        int prev = arr[x1][y1];
        int min = prev;
        
        for (int j = y1 + 1; j <= y2; j++) {
            int cur = arr[x1][j];
            arr[x1][j] = prev;
            prev = cur;
            
            if (min > cur) {
                min = cur;
            }
        }
        
        for (int i = x1 + 1; i <= x2; i++) {
            int cur = arr[i][y2];
            arr[i][y2] = prev;
            prev = cur;
            
            if (min > cur) {
                min = cur;
            }
        }
        
        for (int j = y2 - 1; j >= y1; j--) {
            int cur = arr[x2][j];
            arr[x2][j] = prev;
            prev = cur;
            
            if (min > cur) {
                min = cur;
            }
        }
        
        for (int i = x2 - 1; i >= x1; i--) {
            int cur = arr[i][y1];
            arr[i][y1] = prev;
            prev = cur;
            
            if (min > cur) {
                min = cur;
            }
        }
        
        answer[answerIdx++] = min;
    }
}