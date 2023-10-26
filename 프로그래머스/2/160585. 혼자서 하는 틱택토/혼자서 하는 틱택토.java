class Solution {
    public int solution(String[] board) {
        // int answer = -1;
        
        int len = 3;
        char[][] boardCArray = new char[len][len];
        
        //초기화
        for (int i = 0; i < len; i++) {
            boardCArray[i] = board[i].toCharArray();
        }
        
        //알고리즘
        int cntO = 0;
        int cntX = 0;
        
        for (int i = 0; i < len; i++) {
            for (int j = 0; j < len; j++) {
                if (boardCArray[i][j] == 'O') {
                    cntO++;
                } else if (boardCArray[i][j] == 'X') {
                    cntX++;
                } 
            }
        }
        
        //case 1)  O,X 잘못 표기
        //          O가 4개, X가 2개
        //          O가 3개, X가 4개 
        if (cntO < cntX || cntO > cntX + 1) {
            return 0;
        }
            
        //case 2) O가 성공했는데 X가 한 번 더 한 경우
        if (cntO == cntX) {
            // 2-1) 가로줄 성공
            for (int i = 0; i < len; i++) {
                boolean isX = false;
                
                for (int j = 0; j < len; j++) {
                    if (boardCArray[i][j] != 'O') {
                        isX = true;
                        break;
                    }
                }
                
                if (!isX) {
                    return 0;
                }
            }
            
            // 2-2) 세로줄 성공
            for (int i = 0; i < len; i++) {
                boolean isX = false;
                
                for (int j = 0; j < len; j++) {
                    if (boardCArray[j][i] != 'O') {
                        isX = true;
                        break;
                    }
                }
                
                if (!isX) {
                    return 0;
                }
            }
            
            // 2-3) 대각선줄 성공
            // 2-3-1) 오른쪽아래
            boolean isX = false;
            
            for (int i = 0; i < len; i++) {
                if (boardCArray[i][i] != 'O') {
                    isX = true;
                    break;
                }
            }
            
            if (!isX) {
                return 0;
            }
            
            // 2-3-2) 오른쪽위
            if (boardCArray[2][0] == 'O' && boardCArray[1][1] == 'O' && boardCArray[0][2] == 'O') {
                return 0;
            }
        }
        
        //case 3) X가 성공했는데 O가 한 번 더 한 경우
        if (cntO == cntX + 1) {
            // 3-1) 가로줄 성공
            for (int i = 0; i < len; i++) {
                boolean isO = false;
                
                for (int j = 0; j < len; j++) {
                    if (boardCArray[i][j] != 'X') {
                        isO = true;
                        break;
                    }
                }
                
                if (!isO) {
                    return 0;
                }
            }
            
            // 3-2) 세로줄 성공
            for (int i = 0; i < len; i++) {
                boolean isO = false;
                
                for (int j = 0; j < len; j++) {
                    if (boardCArray[j][i] != 'X') {
                        isO = true;
                        break;
                    }
                }
                
                if (!isO) {
                    return 0;
                }
            }
            
            // 3-3) 대각선줄 성공
            // 3-3-1) 오른쪽아래
            boolean isO = false;
            
            for (int i = 0; i < len; i++) {
                if (boardCArray[i][i] != 'X') {
                    isO = true;
                    break;
                }
            }
            
            if (!isO) {
                return 0;
            }
            
            // 3-3-2) 오른쪽위
            if (boardCArray[2][0] == 'X' && boardCArray[1][1] == 'X' && boardCArray[0][2] == 'X') {
                return 0;
            }
        }
        
        return 1;
    }
}