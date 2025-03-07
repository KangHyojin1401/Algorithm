import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static int N;
	static int[][] arr;
	
	static int cnt = 0;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N][N];
		
		
		//알고리즘
		dfs(0);
		
		
		//출력
		bw.write(cnt + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void dfs(int i) {
		if (i == N) {
			cnt++;
			return;
		}
		
		for (int j = 0; j < N; j++) {
			if (arr[i][j] == 0) {
				//1. 내 번호로 마킹
				arr[i][j] = i + 1;
				
				//왼쪽 대각선
				for (int delta = 1; i + delta < N && j - delta >= 0; delta++) {
					if (arr[i + delta][j - delta] == 0) {
						arr[i + delta][j - delta] = i + 1;
					}
				}
				
				//밑
				for (int delta = 1; i + delta < N; delta++) {
					if (arr[i + delta][j] == 0) {
						arr[i + delta][j] = i + 1;
					}
				}
				
				//오른쪽 대각선
				for (int delta = 1; i + delta < N && j + delta < N; delta++) {
					if (arr[i + delta][j + delta] == 0) {
						arr[i + delta][j + delta] = i + 1;
					}
				}
				
				dfs(i + 1);
				
				//2. 내 번호 지우기
				arr[i][j] = 0;
				
				//왼쪽 대각선
				for (int delta = 1; i + delta < N && j - delta >= 0; delta++) {
					if (arr[i + delta][j - delta] == i + 1) {
						arr[i + delta][j - delta] = 0;
					}
				}
				
				//밑
				for (int delta = 1; i + delta < N; delta++) {
					if (arr[i + delta][j] == i + 1) {
						arr[i + delta][j] = 0;
					}
				}
				
				//오른쪽 대각선
				for (int delta = 1; i + delta < N && j + delta < N; delta++) {
					if (arr[i + delta][j + delta] == i + 1) {
						arr[i + delta][j + delta] = 0;
					}
				}
			}
		}
		
	}

}
