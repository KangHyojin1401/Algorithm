import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static int N, M;
	static int[] picked;
	static BufferedWriter bw;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		
		
		//알고리즘
		picked = new int[M];
		perm(1, 0);
		
		
		//출력
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void perm(int start, int cnt) throws IOException {
		if (cnt == M) {
			for (int i = 0; i < cnt; i++) {
				bw.append(String.valueOf(picked[i])).append(" ");
			}
			bw.append("\n");
			return;
		}
		
		for (int i = start; i <= N; i++) {
			picked[cnt] = i;
			perm(i + 1, cnt + 1);
		}
	}

}