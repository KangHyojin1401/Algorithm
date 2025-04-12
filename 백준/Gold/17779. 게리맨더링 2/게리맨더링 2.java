import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	
	static int N;
	static int[][] population;
	static int min = Integer.MAX_VALUE;

	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		N = Integer.parseInt(br.readLine());
		
		population = new int[N][N];
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				population[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		
		//알고리즘
		for (int i = 0; i < N; i++) { //기준점 x
			for (int j = 0; j < N; j++) { //기준점 y
				for (int k = 1; k < N; k++) { //d1
					for (int l = 1; l < N; l++) { //d2
						if (i + k + l < N && j - k >= 0 && j + l < N) {
							int tMax = Integer.MIN_VALUE;
							int tMin = Integer.MAX_VALUE;
							
							int sum1 = sum1(i, j, k, l);
							tMin = Math.min(tMin, sum1);
							tMax = Math.max(tMax, sum1);
							
							int sum2 = sum2(i, j, k, l);
							tMin = Math.min(tMin, sum2);
							tMax = Math.max(tMax, sum2);
							
							int sum3 = sum3(i, j, k, l);
							tMin = Math.min(tMin, sum3);
							tMax = Math.max(tMax, sum3);
							
							int sum4 = sum4(i, j, k, l);
							tMin = Math.min(tMin, sum4);
							tMax = Math.max(tMax, sum4);
							
							int sum5 = sum5(i, j, k, l);
							tMin = Math.min(tMin, sum5);
							tMax = Math.max(tMax, sum5);
							
							min = Math.min(min, tMax - tMin);
						}
					}
				}
			}
		}
		
		
		//출력
		bw.write(min + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static int sum1(int x, int y, int d1, int d2) {
		int sum = 0;
		
		for (int i = 0; i < x; i++) {
			for (int j = 0; j <= y; j++) {
				sum += population[i][j];
			}
		}
		
		for (int i = x; i < x + d1; i++) {
			for (int j = 0; j < y - (i - x); j++) {
				sum += population[i][j];
			}
		}
		
		return sum;
	}
	
	static int sum2(int x, int y, int d1, int d2) {
		int sum = 0;
		
		for (int i = 0; i < x; i++) {
			for (int j = y + 1; j < N; j++) {
				sum += population[i][j];
			}
		}
		
		for (int i = x; i <= x + d2; i++) {
			for (int j = y + 1 + (i - x); j < N; j++) {
				sum += population[i][j];
			}
		}
		
		return sum;
	}
	
	static int sum3(int x, int y, int d1, int d2) {
		int sum = 0;
		
		for (int i = x + d1; i <= x + d1 + d2; i++) {
			for (int j = 0; j < y - d1 + (i - (x + d1)); j++) {
				sum += population[i][j];
			}
		}
		
		for (int i = x + d1 + d2 + 1; i < N; i++) {
			for (int j = 0; j < y - d1 + d2; j++) {
				sum += population[i][j];
			}
		}
		
		return sum;
	}
	
	static int sum4(int x, int y, int d1, int d2) {
		int sum = 0;
		
		for (int i = x + d2 + 1; i <= x + d1 + d2; i++) {
			for (int j = y + d2 - (i - (x + d2 + 1)); j < N; j++) {
				sum += population[i][j];
			}
		}
		
		for (int i = x + d1 + d2 + 1; i < N; i++) {
			for (int j = y - d1 + d2; j < N; j++) {
				sum += population[i][j];
			}
		}
		
		return sum;
	}
	
	static int sum5(int x, int y, int d1, int d2) {
		int sum = 0;
		
		for (int i = 0; i <= d2; i++) {
			for (int j = 0; j <= d1; j++) {
				sum += population[x + i + j][y + i - j];
			}
		}
		
		for (int i = 0; i < d2; i++) {
			for (int j = 0; j < d1; j++) {
				sum += population[x + (i + 1) + j][y + i - j];
			}
		}
		
		return sum;
	}

}
