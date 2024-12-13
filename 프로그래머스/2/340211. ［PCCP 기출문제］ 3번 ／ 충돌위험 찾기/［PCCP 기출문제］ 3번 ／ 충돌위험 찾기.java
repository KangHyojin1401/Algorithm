import java.util.*;

class Solution {
    int[] dr = {-1, 1, 0, 0}; //상하좌우
    int[] dc = {0, 0, -1, 1};
    
    public int solution(int[][] points, int[][] routes) {
        // int[][] map = new int[100][100];
        // for (int i = 0; i < points.length; i++) {
        //     map[points[i][0] - 1][points[i][1] - 1] = i + 1;
        // }
        
        int robotNum = routes.length; //로봇 개수
        int m = routes[0].length; //운송 경로 (포인트 개수)
        
        int[] startPointIdx = new int[robotNum]; //i번째 로봇의 현재 출발(startPointIdx[i]),도착(startPointIdx[i + 1]이 로봇별로 다르므로 인덱스 관리해줌
        
        int[][] robotCur = new int[robotNum][2]; //i번째 로봇의 현재 위치
        for (int i = 0; i < robotNum; i++) {
            robotCur[i][0] = points[routes[i][0] - 1][0] - 1;
            robotCur[i][1] = points[routes[i][0] - 1][1] - 1;
        }
        
        int[][] robotCurPath = new int[robotNum][2];
        
        int danger = 0;
        
        // int x = 0;
        while (true) {
            int[][] visited = new int[100][100]; //이미 다른 로봇이 점령하고 있는지 여부
            boolean isAllRobotFinished = true;
            
            for (int i = 0; i < robotNum; i++) {
                if (startPointIdx[i] == m) { //운송 마치고 벗어남
                    continue;
                }
                
                isAllRobotFinished = false;
                
                
                //충돌 가능성 체크
                if (visited[robotCur[i][0]][robotCur[i][1]] == 1) {
                    // System.out.println("충돌 발생 지점: " + Arrays.toString(robotCur[i]));
                    
                    danger++;
                }
                
                //방문 처리
                visited[robotCur[i][0]][robotCur[i][1]]++;
                
                
                if (startPointIdx[i] == m - 1) { //운송 마치고 벗어남
                    startPointIdx[i]++;
                    continue;
                }
                
                    
                if (robotCur[i][0] == points[routes[i][startPointIdx[i]] - 1][0] - 1 &&
                    robotCur[i][1] == points[routes[i][startPointIdx[i]] - 1][1] - 1) { //시작점인지
                    //앞으로의 이동 경로 계산
                    int destPointIdx = startPointIdx[i] + 1;
                    robotCurPath[i][0] = points[routes[i][destPointIdx] - 1][0] - points[routes[i][startPointIdx[i]] - 1][0];
                    robotCurPath[i][1] = points[routes[i][destPointIdx] - 1][1] - points[routes[i][startPointIdx[i]] - 1][1];
                    
                    
                    // System.out.println(i + "번째 로봇의 CurPath: " + Arrays.toString(robotCurPath[i]));
                    
                } 
                
                //이동 경로 대로 감
                if (robotCurPath[i][0]!= 0) {
                    if (robotCurPath[i][0] > 0) {
                        robotCurPath[i][0]--;
                        robotCur[i][0]++;
                    } else {
                        robotCurPath[i][0]++;
                        robotCur[i][0]--;
                    }
                } else {
                    if (robotCurPath[i][1] > 0) {
                        robotCurPath[i][1]--;
                        robotCur[i][1]++;
                    } else {
                        robotCurPath[i][1]++;
                        robotCur[i][1]--;
                    }
                }
                
                //도착점인지 체크
                if (robotCur[i][0] == points[routes[i][startPointIdx[i] + 1] - 1][0] - 1 && 
                    robotCur[i][1] == points[routes[i][startPointIdx[i] + 1] - 1][1] - 1) { //도착점이면 startPointIdx바꿔줌
                    startPointIdx[i]++;
                }

            }
            
            if (isAllRobotFinished) {
                break; 
            }
            
            
            for (int i = 0; i < robotNum; i++) {
                // System.out.println(i + "번째 로봇의 Cur: " + Arrays.toString(robotCur[i]));
            }
        }
        
        return danger;
    }
}