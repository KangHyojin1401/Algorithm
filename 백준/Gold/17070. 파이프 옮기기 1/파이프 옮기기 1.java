import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

	static int N;
	static int[][] room;
	static int result = 0;
	
	static int shape = 0; //0: 가로, 1: 세로, 2: 대각선
	static int[][] dr = {
			{0, -1, 1}, //가로 경우의 수 (-1: 세로 없음)
			{-1, 1, 1}, //세로 경우의 수 (-1: 가로 없음)
			{0, 1, 1} //대각선 경우의 수
	}; //0: 가로, 1: 세로, 2: 대각선
	static int[][] dc = {
			{1, -1, 1},
			{-1, 0, 1},
			{1, 0, 1}
	}; //0: 가로, 1: 세로, 2: 대각선
	static int pipeR = 0, pipeC = 1; //파이프의 오른쪽 아래 좌표
	
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		N = Integer.parseInt(br.readLine()); //집의 크기
		room = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				if (Integer.parseInt(line[j]) == 1) {
					room[i][j] = 1;
				}
			}
		}
		
		
		//알고리즘
		find(0, 0, 1);
		
		
		//출력
		bw.write(result + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void find(int shape, int pipeR, int pipeC) {
		if (pipeR == N - 1 && pipeC == N - 1) {
			result++;
			return;
		}
		
		for (int i = 0; i < 3; i++) {
			if (dr[shape][i] == -1) {
				continue;
			}
			
			int newPipeR = pipeR + dr[shape][i];
			int newPipeC = pipeC + dc[shape][i];
			
			if (newPipeR < N && newPipeR >= 0 && newPipeC < N && newPipeC >= 0) {
				boolean isEmpty = true;
				
				for (int x = pipeR; x <= newPipeR; x++) {
					for (int y = pipeC; y <= newPipeC; y++) {
						if (room[x][y] == 1) { //옮겨갈 공간 중 벽이 있으면 멈춤
							isEmpty = false;
							break;
						}
					}
					
					if (!isEmpty) {
						break;
					}
				}
				
				if (isEmpty) {
					find(i, newPipeR, newPipeC);
				}
			}
		}
	}

}