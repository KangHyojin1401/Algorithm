import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Queue;

public class Main {
	
	static int N, M; //연구소의 크기
	static int[][] lab; //연구소
	static int[][] virusPos; //바이러스 위치
	static int virusIdx = 0;
	
	static int[] picked;
	
	static int[] dr = {-1, 0, 1, 0}; //상우하좌
	static int[] dc = {0, 1, 0, -1}; //상우하좌
	
	static int blank = 0;
	static int minTime = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]); //연구소의 크기
		M = Integer.parseInt(line[1]); //놓을 수 있는 바이러스의 개수
		
		lab = new int[N][N];
		virusPos = new int[N * N][2];
		
		for (int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				int n = Integer.parseInt(line[j]);
				
				lab[i][j] = -n;
			
				if (n == 0) {
					blank++;
				} else if (n == 2) {
					virusPos[virusIdx][0] = i; //바이러스 위치 저장
					virusPos[virusIdx++][1] = j;
				}
			}
		}
		
		virusPos = Arrays.copyOf(virusPos, virusIdx);
		
		
		//알고리즘
		if (blank == 0) {
			bw.write("0\n");
			bw.flush();
			bw.close();
			br.close();
			return;
		}
		
		picked = new int[M];
		comb(0, 0);
		
		
		//출력
		if (minTime == Integer.MAX_VALUE) {
			minTime = -1;
		}
		bw.write(minTime + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void comb(int start, int cnt) {
		if (cnt == M) {
			//1. copy
			int[][] copied = new int[N][N];
			for (int i = 0; i < N; i++) {
				copied[i] = Arrays.copyOf(lab[i], N);
			}
			
			//2. 전파 시작 (bfs)
			int curBlank = blank; 
			
			Queue<int[]> queue = new ArrayDeque<>();
			for (int i = 0; i < M; i++) {
				queue.offer(new int[] {virusPos[picked[i]][0], virusPos[picked[i]][1], 0});
			}
			
			while (!queue.isEmpty()) {
				int[] cur = queue.poll();
				
				for (int i = 0; i < 4; i++) {
					int newR = cur[0] + dr[i];
					int newC = cur[1] + dc[i];
					
					if (newR >= 0 && newR < N && newC >= 0 && newC < N 
							&& (copied[newR][newC] == 0 || copied[newR][newC] == -2)) {
						if (copied[newR][newC] == 0) {
							curBlank--;
						}
						
						copied[newR][newC] = -3;
						
						if (curBlank == 0) {
							minTime = Math.min(minTime, cur[2] + 1); 
							break;
						}
						
						queue.offer(new int[] {newR, newC, cur[2] + 1});
					}
				}
				
				if (curBlank == 0) {
					break;
				}
			}
			return;
		}
		
		for (int i = start; i < virusIdx; i++) {
			picked[cnt] = i;
			
			//pick된 활성화 바이러스를 -3으로 바꾸기
			lab[virusPos[i][0]][virusPos[i][1]] = -3;
			
			comb(i + 1, cnt + 1);
			
			//되돌려놓기
			lab[virusPos[i][0]][virusPos[i][1]] = -2;
		}
	}

}
