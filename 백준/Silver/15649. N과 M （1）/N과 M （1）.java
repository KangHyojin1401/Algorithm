import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static int N, M;
	static int[] picked;
	static StringBuilder sb;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		
		picked = new int[M];
		sb = new StringBuilder();
		
		
		//알고리즘
		perm(0, 0);
		
		
		//출력
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void perm(int cnt, int bitMask) {
		if (cnt == M) {
			for (int a = 0; a < M; a++) {
				sb.append(picked[a]).append(" ");
			}
			sb.append("\n");
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if ((bitMask & (1<<i)) == 0) {
				picked[cnt] = i + 1;
				perm(cnt + 1, bitMask | (1<<i));
			}
		}
	}
	
}