import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	
	static int N;
	static int[][] map;
	static int shark[];
	static int sharkEat = 0;
	static int fishCnt = 0;
	static int maxSec = 0;
	
	static int[] min = {21, 21, 0, Integer.MAX_VALUE}; //r, c, 크기, 거리
	static int minDist = Integer.MIN_VALUE;
	static int minR = 21;
	static int minC = 21;
	
	static boolean[][] visited;
	
	static int[] fishSizeCnt = new int[7];
	static int[] dr = {-1, 0, 0, 1}; //상좌우하
	static int[] dc = {0, -1, 1, 0}; //상좌우하

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		N = Integer.parseInt(br.readLine()); //공간의 크기 N(2 ≤ N ≤ 20)
		
		map = new int[N][N];
		shark = new int[4]; //상어 위치 r, c, 크기, 거리
		shark[2] = 2;
		
		for (int i = 0 ; i < N; i++) {
			String[] line = br.readLine().split(" ");
			
			for (int j = 0; j < N; j++) {
				int n = Integer.parseInt(line[j]);
				
				map[i][j] = n;
				
				if (n == 9) {
					shark[0] = i;
					shark[1] = j;
				} else if (n != 0) {
					fishSizeCnt[n]++;
					fishCnt++; 
				}
 			}
		}
		
		map[shark[0]][shark[1]] = 0;
		
		
		//알고리즘
		while (true) {
			//현재 상어 크기보다 작은 애들(먹을 수 있는 애들 수 구하기)
			int canEat = 0;
			for (int i = 1; i < 7 && i < shark[2]; i++) {
				canEat += fishSizeCnt[i];
			}
			
			if (fishCnt == 0 || canEat == 0) {
				break;
			} else {
				find();
				if (min[0] == 21) {
					break;
				}
			}
			
			eat();
			min = new int[] {21, 21, 0, Integer.MAX_VALUE};
		}
		  
		
		//출력
		bw.write(maxSec + "\n");
		bw.flush();
		bw.close();
		
	}
	
	static void find() {
		//bfs
		Queue<int[]> queue = new ArrayDeque<>();
		visited = new boolean[N][N];
		
		queue.add(shark);
		visited[shark[0]][shark[1]] = true;
		
		while (!queue.isEmpty()) {
			int[] cur = queue.poll();
			
			//자신의 크기보다 작은 물고기만 먹을 수 있다
			if (map[cur[0]][cur[1]] != 0 && cur[2] < shark[2]) { //빈칸이 아닌 물고기이고, 상어보다 크기가 작으면
				//비교 후 갱신
				reset(cur[0], cur[1], cur[3]);
			}	

			//거리가 달라지면 먹기
			if (cur[3] == min[3] + 1) {
//				eat();
				break;
			}
			
			for (int i = 0; i < 4; i++) {
				int newR = cur[0] + dr[i];
				int newC = cur[1] + dc[i];
				
				if (newR >= 0 && newR < N && newC >= 0 && newC < N && 
						//자신의 크기보다 큰 물고기가 있는 칸은 지나갈 수 없고, 나머지 칸은 모두 지나갈 수 있다.
						map[newR][newC] <= shark[2] && !visited[newR][newC]) {
					queue.add(new int[] {newR, newC, map[newR][newC], cur[3] + 1});
					visited[newR][newC] = true;
				}
			}
		}
	}
	
	static void reset(int r, int c, int dist) {
		if (min[3] < dist) {
			return;
		}
		
		if (min[0] > r) { //가장 위에 있는 물고기
			min[0] = r;
			min[1] = c;
			min[3] = dist;
		} else if (min[0] == r) { //그러한 물고기가 여러마리라면, 
			if (min[1] > c) { //가장 왼쪽에 있는 물고기를 먹는다.
				min[1] = c;
				min[3] = dist;
			}
		}
		
		min[2] = map[min[0]][min[1]];
	}
	
	static void eat() {
		map[min[0]][min[1]] = 0; //물고기를 먹으면, 그 칸은 빈 칸이 된다.
		fishCnt--;
		maxSec += min[3];
		sharkEat++;
		fishSizeCnt[min[2]]--;
		
		if (shark[2] == sharkEat) { //아기 상어는 자신의 크기와 같은 수의 물고기를 먹을 때 마다 크기가 1 증가한다.
			sharkEat = 0;
			shark[2]++;
		}
		
		//상어 위치 재설정
		shark[0] = min[0];
		shark[1] = min[1];
	}

}