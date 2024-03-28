import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {
	
	static int N;
	static int[][] ability;
	static ArrayList<Integer> sumList;
	static int[] picked;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		N = Integer.parseInt(br.readLine());
		ability = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				ability[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		
		//알고리즘
		sumList = new ArrayList<>();
		picked = new int[N / 2];
		
		comb(0, 0, 0);
		
		
		//출력
		bw.write(findMin() + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void comb(int start, int sum, int depth) {
		if (depth == N / 2) {
			sumList.add(sum);
			return;
		}
		
		for (int i = start; i < N; i++) {
			picked[depth] = i;
			
			int tmp = 0;
			for (int j = 0; j < depth; j++) {
				tmp += ability[picked[j]][i];
				tmp += ability[i][picked[j]];
			}
			
			comb(i + 1, sum + tmp, depth + 1);
		}
	}
	
	static int findMin() {
		int min = Integer.MAX_VALUE;
		int len = sumList.size();
		
		for (int i = 0; i < len; i++) {
			int result = Math.abs(sumList.get(i) - sumList.get(len - i - 1));
			if (result < min) {
				min = result;
			}
		}
		
		return min;
	}

}