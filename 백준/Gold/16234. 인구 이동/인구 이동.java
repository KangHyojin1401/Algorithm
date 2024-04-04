import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {
	
	static int N, L, R;
	static int[][] population; //인구수
	static int[][] visited; //방문체크 & 연합 번호 표시
	
	static int[][] populationSum; //연합 별 인구 수 합, 연합의 크기
	
	static int unionNum = 1;
	
	static int[] dr = {-1, 0, 1, 0}; //상우하좌
	static int[] dc = {0, 1, 0, -1};
	
	static int day = 0; //인구 이동이 며칠 동안 발생하는지

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		String[] line = br.readLine().split(" ");
		
		N = Integer.parseInt(line[0]);
		L = Integer.parseInt(line[1]);
		R = Integer.parseInt(line[2]);
		
		population = new int[N][N];
		for(int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				population[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		
		//알고리즘
		while(true) {
			boolean isMoved = false;
			visited = new int[N][N];
			populationSum = new int[2500][2];
			unionNum = 1;
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i][j] == 0) { //방문 안 했으면
						dfs(i, j, population[i][j]);
						unionNum++;
					}
				}
			}
			
			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if (visited[i][j] != -1 && populationSum[visited[i][j] - 1][1] != 1) { //방문 안 했고, 고립된 상태 아니면
						int unionNum = visited[i][j];
						changePopulation(i, j, unionNum, populationSum[unionNum - 1][0] / populationSum[unionNum - 1][1]);
						isMoved = true;
					}
				}
			}
		
			if (!isMoved) {
				break;
			}
			
			day++;
		}
		
		
		//출력
		bw.write(day + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}

	private static void dfs(int r, int c, int p) {
		populationSum[unionNum - 1][0] += p;
		populationSum[unionNum - 1][1]++; //나라 추가 (size +1)
		visited[r][c] = unionNum;
		
		for (int i = 0; i < 4; i++) {
			int newR = r + dr[i];
			int newC = c + dc[i];
			
			if (newR >= 0 && newR < N && newC >= 0 && newC < N && visited[newR][newC] == 0) {
				int newP = population[newR][newC];
				int diff = Math.abs(p - newP); //인구 수차이
				
				if (diff >= L && diff <= R) {
					dfs(newR, newC, newP);
				}
			}
		}
	}
	
	private static void changePopulation(int r, int c, int unionNum, int newPopluation) {
		visited[r][c] = - 1;
		population[r][c] = newPopluation;

		for (int i = 0; i < 4; i++) {
			int newR = r + dr[i];
			int newC = c + dc[i];
			
			if (newR >= 0 && newR < N && newC >= 0 && newC < N && visited[newR][newC] == unionNum) {
				changePopulation(newR, newC, unionNum, newPopluation);
			}
		}
	}

}