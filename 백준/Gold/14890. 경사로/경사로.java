import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static int N, L;
	static int[][] map;
	static int road = 0;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		String[] line = br.readLine().split(" ");
		
		N = Integer.parseInt(line[0]);
		L = Integer.parseInt(line[1]);
		
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		//알고리즘
		for (int i = 0; i < N; i++) {
			int prev = -1;
			int same = 1;
			int j;
			boolean down = false; 
			
			for (j = 0; j < N; j++) {
				int cur = map[i][j];
				
				if (prev == -1) { //맨 처음 원소
					prev = cur;
					continue;
				}
				
				if (same == 0) { //우하향 끝
					if (prev < cur) {
						break;
					} 
				}
				
				if (prev == cur) {
					same++;
				} else if (down && same < L) {
					break;
				} else if (prev == cur - 1) { //우상향 경사로
					if (same >= L) {
						prev = cur;
						same = 1;
					} else { // (2번, 3번, 4번 예제)
						break; 
					}
				} else if (prev == cur + 1) { //우하향 경사로
					down = true;
					prev = cur;
					same = 1;
				} else { //높이 차이가 1 초과 (1번 예제)
					break;
				}
				
				if (down && same == L) {
					down = false;
					prev = cur;
					same = 0;
				}
			}
			
			if (j == N && !down) {
				road++;
			}
		}
		
		for (int j = 0; j < N; j++) {
			int prev = -1;
			int same = 1;
			int i;
			boolean down = false; 
			
			for (i = 0; i < N; i++) {
				int cur = map[i][j];
				
				if (prev == -1) { //맨 처음 원소
					prev = cur;
					continue;
				}
				
				if (prev == cur) {
					same++;
				} else if (down && same < L) {
					break;
				} else if (prev == cur - 1) { //우상향 경사로
					if (same >= L) {
						prev = cur;
						same = 1;
					} else { // (2번, 3번, 4번 예제)
						break; 
					}
				} else if (prev == cur + 1) { //우하향 경사로
					down = true;
					prev = cur;
					same = 1;
				} else { //높이 차이가 1 초과 (1번 예제)
					break;
				}
				
				if (down && same == L) {
					down = false;
					prev = cur;
					same = 0;
				}
			}
			
			if (i == N && !down) {
				road++;
			}
		}
		
		
		//출력
		bw.write(road + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}

}