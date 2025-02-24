import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static int K, N;
	static int[] len;
	static int len_min = Integer.MAX_VALUE;
	static int len_max = Integer.MIN_VALUE;
	static int max;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		String[] line = br.readLine().split(" ");
		K = Integer.parseInt(line[0]); //이미 가지고 있는 랜선의 개수
		N = Integer.parseInt(line[1]); //필요한 랜선의 개수
		
		len = new int[K];
		for (int i = 0; i < K; i++) {
			int n = Integer.parseInt(br.readLine());
			len[i] = n;
			
			len_min = Math.min(len_min, n);
			len_max = Math.max(len_max, n);
		}
		
		
		//알고리즘
		search(1, len_max);
		
		
		//출력
		bw.write(max + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void search(int start, int end) {
		long sToEnd = (long)start + (long)end; 
		int mid = (int) (sToEnd / 2);
		int sum = sumDivide(mid);
		
		if (N <= sum) {
			if (N > sumDivide(mid + 1)) {
				max = mid;
				return;
			}
			search(mid + 1, end);
		} else {
			search(start, mid);  
		} 
	}
	
	static int sumDivide(long n) {
		int sum = 0;
		
		for (int i = 0; i < K; i++) {
			sum += len[i] / n;
		}
		
		return sum;
	}

}