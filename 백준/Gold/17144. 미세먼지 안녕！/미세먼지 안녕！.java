import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	
	static int[] dr = {-1, 0, 1, 0}; //상우하좌
	static int[] dc = {0, 1, 0, -1};
	
	static int R, C, T;
	static int[] airCleaner; //공기청정기 좌표
	static int[][] room;
	static int[][] prev;
	
	static int dustCnt = 0;

	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		String[] line = br.readLine().split(" ");
		R = Integer.parseInt(line[0]);
		C = Integer.parseInt(line[1]);
		T = Integer.parseInt(line[2]);
		
		airCleaner = new int[2];
		airCleaner[0] = -1;
		airCleaner[1] = 0;
		
		room = new int[R][C];
		for (int i = 0; i < R; i++) {
			line = br.readLine().split(" ");
			for (int j = 0; j < C; j++) {
				int n = Integer.parseInt(line[j]);
				room[i][j] = n;
				
				if (n == -1 && airCleaner[0] == -1) {
					airCleaner[0] = i;
				}
			}
		}
		

		//알고리즘
		int second = 0;
		while (second < T) {
			diffuseAir(); //1. 확산
			circulate(); //2. 공기청정기 작동(순환)
			second++;
		}
		
		countDust();
		
		
		//출력
		bw.write(dustCnt + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void diffuseAir() {
		prev = new int[R][C];
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (room[i][j] != 0) {
					int quantity = room[i][j] / 5; //확산된 양
					
					for (int k = 0; k < 4; k++) {
						int newR = i + dr[k];
						int newC = j + dc[k];
						
						if (newR >= 0 && newR < R && newC >= 0 && newC < C && room[newR][newC] != -1) {
							prev[newR][newC] += quantity;
							room[i][j] -= quantity;
						}
					}
				}
			}
		}
		
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				room[i][j] = room[i][j] + prev[i][j];
			}
		}
	}
	
	static void circulate() {
		int prev = room[airCleaner[0]][1];
		room[airCleaner[0]][1] = 0;
		
		for (int j = 2; j < C; j++) {
			int cur = room[airCleaner[0]][j];
			room[airCleaner[0]][j] = prev;
			prev = cur;
		}
		
		for (int i = airCleaner[0] - 1; i >= 0; i--) {
			int cur = room[i][C - 1];
			room[i][C - 1] = prev;
			prev = cur;
		}
		
		for (int j = C - 2; j >= 0; j--) {
			int cur = room[0][j];
			room[0][j] = prev;
			prev = cur;
		}
		
		for (int i = 1; i < airCleaner[0]; i++) {
			int cur = room[i][0];
			room[i][0] = prev;
			prev = cur;
		}
		
		
		prev = room[airCleaner[0] + 1][1];
		room[airCleaner[0] + 1][1] = 0;
		
		for (int j = 2; j < C; j++) {
			int cur = room[airCleaner[0] + 1][j];
			room[airCleaner[0] + 1][j] = prev;
			prev = cur;
		}
		
		for (int i = airCleaner[0] + 2; i < R; i++) {
			int cur = room[i][C - 1];
			room[i][C - 1] = prev;
			prev = cur;
		}
		
		for (int j = C - 2; j >= 0; j--) {
			int cur = room[R - 1][j];
			room[R - 1][j] = prev;
			prev = cur;
		}
		
		for (int i = R - 2; i > airCleaner[0] + 1; i--) {
			int cur = room[i][0];
			room[i][0] = prev;
			prev = cur;
		}
	}
	
	static void countDust() {
		for (int i = 0; i < R; i++) {
			for (int j = 0; j < C; j++) {
				if (room[i][j] != -1) {
					dustCnt += room[i][j];
				}
			}
		}
	}

}