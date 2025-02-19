import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	
	static int N;
	static char[][] arr;
	static boolean[][] visited;
	static int normalCnt = 0;
	static int weakCnt = 0;
	
	static int[] dr = {-1, 0, 1, 0}; //상우하좌
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		N = Integer.parseInt(br.readLine());
		
		arr = new char[N][N];
		for (int i = 0; i < N; i++) {
			arr[i] = br.readLine().toCharArray();
		}
		
		
		//알고리즘
		visited = new boolean[N][N];		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					dfs(i, j, arr[i][j]);
					normalCnt++;
				}
			}
		}
		
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (!visited[i][j]) {
					dfsForWeak(i, j);
					weakCnt++;
				}
			}
		}
		
		
		//출력
		bw.write(normalCnt + " " + weakCnt + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void dfs(int r, int c, char character) {
		visited[r][c] = true;
	
		for (int i = 0; i < 4; i++) {
			int newR = r + dr[i];
			int newC = c + dc[i];
			
			if (newR >= 0 && newR < N && newC >= 0 && newC < N
					&& !visited[newR][newC]
					&& arr[newR][newC] == character) {
				dfs(newR, newC, character);
			}
		}
	}
	
	static void dfsForWeak(int r, int c) {
		visited[r][c] = true;
	
		for (int i = 0; i < 4; i++) {
			int newR = r + dr[i];
			int newC = c + dc[i];
			
			if (newR >= 0 && newR < N && newC >= 0 && newC < N
					&& !visited[newR][newC]) {
				if (arr[r][c] == 'B') {
					if (arr[newR][newC] == 'B') {
						dfsForWeak(newR, newC);
					}
				} else {
					if (arr[newR][newC] != 'B') {
						dfsForWeak(newR, newC);
					}
				}
			}
		}
	}

}