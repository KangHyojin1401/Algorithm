import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static int N;
	static int[] wine;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		N = Integer.parseInt(br.readLine());
		
		wine = new int[N];
		for (int i = 0; i < N; i++) {
			wine[i] = Integer.parseInt(br.readLine());
		}
		
		
		//알고리즘
		int[] dp = new int[N]; 
		
		dp[0] = wine[0];
		if (N == 1) {
			System.out.println(dp[0]);
			return;
		}
		
		dp[1] = wine[0] + wine[1];
		if (N == 2) {
			System.out.println(dp[1]);
			return;
		}
		
		dp[2] = Math.max(Math.max(wine[1] + wine[2], wine[0] + wine[2]), dp[1]);
		
		for (int i = 3; i < N; i++) {
			// i번째 잔을 마셨을 때 
			// (i-1)번째 잔을 마신 경우와 안 마신 경우 중에 최댓값 구하기 
			int temp = Math.max(dp[i - 3] + wine[i - 1] + wine[i], dp[i - 2] + wine[i]);

			// i번째 잔을 마신 경우와 안 마신 경우 중에 최댓값 구해서 저장하기  
			dp[i] = Math.max(temp, dp[i - 1]);
		}
		
				
		//출력
		bw.write(dp[N - 1] + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}

}