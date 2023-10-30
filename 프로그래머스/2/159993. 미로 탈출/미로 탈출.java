import java.util.*;

class Solution {
    class Location {
        public int r; 
        public int c;
        public int depth;
        
        public Location() {
            this.depth = 0;
        }
        
        public Location(int r, int c, int depth) {
            this.r = r;
            this.c = c;
            this.depth = depth;
        }
    }
    
    public int solution(String[] maps) {
        int answer = -1;
        
        //초기화
        int len = maps.length;
        int wid = maps[0].length();
        
        char[][] map = new char[len][wid];
        Location start = new Location();
        // Location lever = new Location();
        
        for (int i = 0; i < len; i++) {
            map[i] = maps[i].toCharArray();
            
            for (int j = 0; j < wid; j++) {
                if (map[i][j] == 'S') {
                    start.r = i;
                    start.c = j;
                }
                
                // if (map[i][j] == 'L') {
                //     lever.r = i;
                //     lever.c = j;
                // }
            }
        }
        
        // System.out.println("start: " + start.r + ", " + start.c);
        // System.out.println("end: " + end.r + ", " + end.c);
        // System.out.println("lever: " + lever.r + ", " + lever.c);

        
        
        //알고리즘
        Queue<Location> queue = new ArrayDeque<>();
        boolean[][] visited = new boolean[len][wid];
        
        int[] dr = {-1, 0, 1, 0};
        int[] dc = {0, 1, 0, -1};
        
        queue.offer(start);
        visited[start.r][start.c] = true;

        
        //레버 찾기
        while (!queue.isEmpty()) {
            Location cur = queue.poll();
            
            if (map[cur.r][cur.c] == 'L') { //레버 나오면 start를 현위치로 바꾸고 break
                start = new Location(cur.r, cur.c, 0);
                answer = cur.depth;
                break;
            }
            
            for (int i = 0; i < 4; i++) {
                int newR = cur.r + dr[i];
                int newC = cur.c + dc[i];
                
                if (newR >= 0 && newR < len && newC >= 0 && newC < wid
                   && !visited[newR][newC] && map[newR][newC] != 'X') {
                    queue.offer(new Location(newR, newC, cur.depth + 1));
                    visited[newR][newC] = true;
                }
            }
        }
        
        
        System.out.println("answer: " + answer);

        if (answer == -1) {
            return -1;
        }
        
        
        //출구 찾기
        queue = new ArrayDeque<>();
        visited = new boolean[len][wid];
        
        queue.offer(start);
        visited[start.r][start.c] = true;
        
        while (!queue.isEmpty()) {
            Location cur = queue.poll();
            visited[cur.r][cur.c] = true;
            
            if (map[cur.r][cur.c] == 'E') { //출구 나오면 return
                return answer + cur.depth;
            }
            
            for (int i = 0; i < 4; i++) {
                int newR = cur.r + dr[i];
                int newC = cur.c + dc[i];
                
                if (newR >= 0 && newR < len && newC >= 0 && newC < wid
                   && !visited[newR][newC] && map[newR][newC] != 'X') {
                    queue.offer(new Location(newR, newC, cur.depth + 1));
                    visited[newR][newC] = true;
                }
            }
        }
        
        return -1;
    }
}