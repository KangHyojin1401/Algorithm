import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static int N;
	static int[][] rgb, dp;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		N = Integer.parseInt(br.readLine());
		
		rgb = new int[N][3];
		dp = new int[N][3];
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			rgb[i][0] = Integer.parseInt(line[0]);			
			rgb[i][1] = Integer.parseInt(line[1]);
			rgb[i][2] = Integer.parseInt(line[2]);
		}
		
		
		//알고리즘
		for (int j = 0; j < 3; j++) {
			dp[0][j] = rgb[0][j];
		}
		
		for (int i = 1; i < N; i++) {
			//빨강
			dp[i][0] = Math.min(rgb[i][0] + dp[i - 1][1], rgb[i][0] + dp[i - 1][2]);
			//초록
			dp[i][1] = Math.min(rgb[i][1] + dp[i - 1][0], rgb[i][1] + dp[i - 1][2]);
			//파랑
			dp[i][2] = Math.min(rgb[i][2] + dp[i - 1][0], rgb[i][2] + dp[i - 1][1]);
		}
		
		
		//출력
		bw.write(Math.min(Math.min(dp[N - 1][0], dp[N - 1][1]), dp[N - 1][2]) + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}

}