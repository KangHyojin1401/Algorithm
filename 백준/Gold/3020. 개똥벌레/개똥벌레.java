import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

	public static void main(String[] args) throws Exception {

		// 초기화
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]);
		int H = Integer.parseInt(line[1]);
		
		int[] ss = new int[N / 2]; //석순
		int[] jys = new int[N / 2]; //종유석		
		
		int i = 0;
		while (i < N / 2) {
			ss[i] = Integer.parseInt(br.readLine());
			jys[i] = Integer.parseInt(br.readLine());
			i++;
		}
		
		Arrays.sort(ss);
		Arrays.sort(jys);
		
		int[] ssObstcl = new int[H + 1];
		int[] jysObstcl = new int[H + 1];
		
				
		//알고리즘
        int start = 1;
		int finish = 1;
		for (i = N / 2 - 1; i >= 0; i--) {
			start = ss[i];
			finish = i == 0 ? ss[i] : ss[i - 1];
			
			if (i == N / 2 - 1) {
				++ssObstcl[start];
			} else {
				ssObstcl[start] = ssObstcl[ss[i + 1]] + 1;
			}
			
			int cur = ssObstcl[start];
			
			while (start - finish > 1) {
				start--;
				ssObstcl[start] += cur;
			}
		}
        
        int cur = ssObstcl[start];
		while (start != 1) {
			start--;
			ssObstcl[start] += cur;
		}
        
		
		start = 1;
		finish = 1;
		for (i = N / 2 - 1; i >= 0; i--) {
			start = jys[i];
			finish = i == 0 ? jys[i] : jys[i - 1];
			
			if (i == N / 2 - 1) {
				++jysObstcl[H - start + 1];
			} else {
				jysObstcl[H - start + 1] = jysObstcl[H - jys[i + 1] + 1] + 1;
			}
			
			cur = jysObstcl[H - start + 1];
			
			while (start - finish > 1) {
				start--;
				jysObstcl[H - start + 1] += cur;
			}
		}
		
		cur = jysObstcl[H - start + 1];
		while (start != 1) {
			start--;
			jysObstcl[H - start + 1] += cur;
		}

		for (i = 1; i < H + 1; i++) {
			ssObstcl[i] += jysObstcl[i];
		}
		
		Arrays.sort(ssObstcl);
		
		int cnt = 1;
		int min = ssObstcl[1];
		
		i = 2;
		while (min == ssObstcl[i]) {
			i++;
			cnt++;
		}
		
		// 출력
		StringBuilder sb = new StringBuilder();
		sb.append(min).append(" ").append(cnt).append("\n");
		bw.write(sb.toString());
		bw.flush();
		bw.close();

	}

}