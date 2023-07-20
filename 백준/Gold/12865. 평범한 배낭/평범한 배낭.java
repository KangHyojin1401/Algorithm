import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]); //물품의 수
		int K = Integer.parseInt(line[1]); //버틸 수 있는 무게 
		
		int[][] bag = new int[N][2];
		
		for (int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			
			bag[i][0] = Integer.parseInt(line[0]); //물건의 무게
			bag[i][1] = Integer.parseInt(line[1]); //물건의 가치
		}
		
		
		//알고리즘
		/* ver.1 2차원 배열 */
		
		/* ver.2 1차원 배열 */
		int[] dp = new int[K + 1];
		
		for (int j = 0; j < N; j++) {
			int[] curBag = bag[j]; //현재 물건
			int curW = curBag[0]; //현재 물건의 무게
			int curV = curBag[1]; //현재 물건의 가치
			
			for (int i = K; i >= curW && i >= 1; i--) {
				dp[i] = Math.max(curV + dp[i - curW], dp[i]);
			}
		}
		
		
		//출력
		bw.write(dp[K] + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}

}