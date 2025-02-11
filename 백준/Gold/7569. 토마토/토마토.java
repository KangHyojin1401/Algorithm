import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	
	static int M, N, H;
	static int[][][] box;
	
	static int[] dm = {-1, 1, 0, 0, 0, 0}; //위 아래 왼쪽 오른쪽 앞 뒤
	static int[] dn = {0, 0, -1, 1, 0, 0};
	static int[] dh = {0, 0, 0, 0, 1, -1};
	
	static int days = 0; //토마토가 모두 익을 때까지 최소한의 day
	static int unripenedTomato = 0; //안 익은 토마토 개수

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		String[] line = br.readLine().split(" ");
		M = Integer.parseInt(line[0]);
		N = Integer.parseInt(line[1]);
		H = Integer.parseInt(line[2]);
		
		box = new int[M][N][H];
		Queue<int[]> queue = new ArrayDeque<>();
		
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < N; j++) {
				line = br.readLine().split(" ");
				for (int k = 0; k < M; k++) {
					box[k][j][i] = Integer.parseInt(line[k]);
					
					if (box[k][j][i] == 0) {
						unripenedTomato++;
					} if (box[k][j][i] == 1) {
						queue.offer(new int[] {k, j, i, 1});
					}
				}
			}
		}
		
		
		//알고리즘
		//1-1. 모든 토마토가 이미 익어있는지 체크
		if (unripenedTomato == 0) {
			System.out.println("0");
			return;
		}
		
		while(!queue.isEmpty()) {
			//2. 탐색, 토마토 익히기
			int[] cur = queue.poll();
			
			for (int a = 0; a < 6; a++) {
				int newM = cur[0] + dm[a];
				int newN = cur[1] + dn[a];
				int newH = cur[2] + dh[a];
				
				if (newM >= 0 && newM < M && newN >=0 && newN < N && newH >= 0 && newH < H
						&& box[newM][newN][newH] == 0) {
					queue.offer(new int[] {newM, newN, newH, cur[3] + 1});
					box[newM][newN][newH] = 1;
					unripenedTomato--;
				}
			}
			
			days = cur[3] - 1;
		}
		
		//1-2.토마토가 모두 익지는 못하는 상황
		if (unripenedTomato != 0) {
			System.out.println("-1");
			return;
		}
		
		
		//출력
		bw.write(days + "\n");
		bw.flush();
		bw.close();
		br.close();
	}
	
}