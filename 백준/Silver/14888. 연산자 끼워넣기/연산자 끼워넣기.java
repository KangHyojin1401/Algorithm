import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	
	static int N;
	static int[] nums;
	static int[] operatorCnt;
	
	static int min = Integer.MAX_VALUE;
	static int max = Integer.MIN_VALUE;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		N = Integer.parseInt(br.readLine()); // 수의 개수 N(2 ≤ N ≤ 11)
		
		nums = new int[N]; // 수열
		
		String[] line = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			nums[i] = Integer.parseInt(line[i]);
		}
		
		operatorCnt = new int[4]; // 연산자 개수 (+ - * / 순서)
		
		line = br.readLine().split(" ");
		for (int i = 0; i < 4; i++) {
			operatorCnt[i] = Integer.parseInt(line[i]);
		}
		
		
		//알고리즘
		calc(nums[0], 1);
		
		//출력
		bw.write(max + "\n");
		bw.write(min + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void calc(int num, int depth) {
		if (depth == N) { //추후 변경
			if (max < num) {
				max = num;
			}
			if (min > num) {
				min = num;
			}
		}
		
		int o1 = num;
		int result = -1;
		
		for (int i = 0; i < 4; i++) {
			if (operatorCnt[i] != 0) {
				operatorCnt[i]--;
				result = operate(o1, nums[depth], i);
				calc(result, depth + 1);
				operatorCnt[i]++;
			}
		}
	}
	
	static int operate(int o1, int o2, int operator) {
		switch (operator) {
			case 0:
				return o1 + o2;
			case 1:
				return o1 - o2;
			case 2:
				return o1 * o2;
			case 3:
				return o1 / o2;
			default:
				return o2;
		}
	}

}