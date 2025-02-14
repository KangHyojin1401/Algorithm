import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static int N, S;
	static int[] arr;
	static int cnt = 0;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		S = Integer.parseInt(line[1]);
		
		line = br.readLine().split(" ");
		arr = new int[N];
		for (int i = 0; i < N; i++) {
			arr[i] = Integer.parseInt(line[i]);
		}
		
		
		//알고리즘
		cnt = 0;
		part(0, 0, false);
		
		
		//출력
		bw.write(cnt + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void part(int sum, int depth, boolean notBlank) {
		if (depth == N) {
			if (notBlank && sum == S) {
				cnt++;
			}
			return;
		}
		
		part(sum + arr[depth], depth + 1, true);
		part(sum, depth + 1, notBlank);
	}

}