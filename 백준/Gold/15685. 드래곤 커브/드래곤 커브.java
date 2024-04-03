import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static int N;
	static int[] dx = {1, 0, -1, 0};
	static int[] dy = {0, -1, 0, 1};
	
	static boolean[][] arr;
	static int square = 0;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		N = Integer.parseInt(br.readLine());
		arr = new boolean[101][101];
		
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			int x = Integer.parseInt(line[0]); //드래곤 커브의 시작 점
			int y = Integer.parseInt(line[1]); //드래곤 커브의 시작 점
			int d = Integer.parseInt(line[2]); //시작 방향
			int g = Integer.parseInt(line[3]); //세대
			
			rotate(x, y, d, g);
		} 
		
		
		//알고리즘
		countSquare();
		
		
		//출력
		bw.write(square + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void rotate(int x, int y, int d, int g) {
		int lineNum = (int) Math.pow(2, g); //선분의 개수
		int[] directions = new int[lineNum]; //방향 저장
		
		arr[y][x] = true;

		directions[0] = d;
		int prevX = x + dx[d];
		int prevY = y + dy[d];
		arr[prevY][prevX] = true;
		
		for (int i = 1; i < lineNum; i *= 2) {
			for (int j = 0; j < i; j++) {
				directions[i + j] = (directions[i - (j + 1)] + 1) % 4;
				
				int curX = prevX + dx[directions[i + j]];
				int curY = prevY + dy[directions[i + j]];
				arr[curY][curX] = true;
				
				prevX = curX;
				prevY = curY;
			}
		}
	}
	
	static void countSquare() {
		for (int i = 0; i < 100; i++) {
			for (int j = 0; j < 100; j++) {
				if (arr[i][j] && arr[i][j + 1] && arr[i + 1][j] && arr[i + 1][j + 1]) {
					square++;
				}
			}
		}
	}

}