import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static int N, M, K;
	static int[][] arr;
	static int[][] rotateInfo;
	static int min = Integer.MAX_VALUE;
	
	static int[] picked;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		String[] line = br.readLine().split(" "); 
		N = Integer.parseInt(line[0]); //행
		M = Integer.parseInt(line[1]); //열
		K = Integer.parseInt(line[2]); //회전 연산의 개수

		arr = new int[N][M];
		for (int i = 0; i < N; i++) {
			line = br.readLine().split(" ");
			for (int j = 0; j < M; j++) {
				arr[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		rotateInfo = new int[K][3];
		for (int i = 0; i < K; i++) {
			line = br.readLine().split(" ");
			rotateInfo[i][0] = Integer.parseInt(line[0]) - 1; //회전 중심축 좌표
			rotateInfo[i][1] = Integer.parseInt(line[1]) - 1; //회전 중심축 좌표
			rotateInfo[i][2] = Integer.parseInt(line[2]); //회전 반경
		}
		
		picked = new int[K];
		
		
		//알고리즘
		perm(0, 0); //순열
		
		
		//출력
		bw.write(min + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}

	private static void perm(int cnt, int bitMasking) {
		if (cnt == K) {
			int[][] copiedArray = copyArray(arr);
		
			for (int i = 0 ; i < cnt; i++) {
				int idx = picked[i];
				rotate(copiedArray, rotateInfo[idx][0], rotateInfo[idx][1], rotateInfo[idx][2]);
			}
			
			int tmp = findMin(copiedArray);
			if (min > tmp) {
				min = tmp;
			}
			
			return;
		}
		
		for (int i = 0; i < K; i++) {
			if ((bitMasking & 1<<i) == 0) {
				picked[cnt] = i;
				perm(cnt + 1, bitMasking | 1<<i);
			}
		}
	}
	
	private static int[][] copyArray(int[][] origin) {
		int[][] copy = new int[N][M];
		
		for (int a = 0; a < origin.length; a++) {
			for (int b = 0; b < origin[0].length; b++) {
				copy[a][b] = origin[a][b];
			}
		}
		
		return copy;
	}

	private static void rotate(int[][] array, int centerR, int centerC, int len) {
		if (len == 0) {
			return;
		}
		
		int prev = array[centerR - len][centerC - len];
		
		for (int j = centerC - len + 1; j <= centerC + len; j++) {
			int cur = array[centerR - len][j];
			array[centerR - len][j] = prev;
			prev = cur;
		}
		
		for (int i = centerR - len + 1; i <= centerR + len; i++) {
			int cur = array[i][centerC + len];
			array[i][centerC + len] = prev;
			prev = cur;
		}
		
		for (int j = centerC + len - 1; j >= centerC - len; j--) {
			int cur = array[centerR + len][j];
			array[centerR + len][j] = prev;
			prev = cur;
		}
		
		for (int i = centerR + len - 1; i >= centerR - len; i--) {
			int cur = array[i][centerC - len];
			array[i][centerC - len] = prev;
			prev = cur;
		}
			
		rotate(array, centerR, centerC, len - 1);
	}
	
	static int findMin(int[][] array) {
		int min = Integer.MAX_VALUE;
		int sum = 0;
		
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array[0].length; j++) {
				sum += array[i][j];
			}
			
			if (min > sum) {
				min = sum;
			}
				
			sum = 0;
		}
		
		return min;
	}

}