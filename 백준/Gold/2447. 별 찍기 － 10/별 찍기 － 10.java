import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static int N;
	static char[][] arr;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		N = Integer.parseInt(br.readLine());
		arr = new char[N][N];
		
		
		//알고리즘
		int cnt = 0;
		int n = N;
		while (n > 1) {
			n /= 3;
			cnt++;
		}
		
		print(0, 0, cnt);
		
		
		//출력
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (arr[i][j] == '*') {
					bw.write(arr[i][j]);
				} else {
					bw.write(" ");
				}
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void print(int startR, int startC, int cnt) {
		int power = (int) Math.pow(3, cnt);
		int delta = (int) Math.pow(3, cnt - 1);
		
		for (int i = startR; i < startR + power; i += delta) {
			for (int j = startC; j < startC + power; j += delta) {
				if (!((i == startR + delta) && (j == startC + delta))) {
					if (cnt == 1) {
						arr[i][j] = '*';
					} else {
						print(i, j, cnt - 1);
					}
				}
			}
		}
	}

}
