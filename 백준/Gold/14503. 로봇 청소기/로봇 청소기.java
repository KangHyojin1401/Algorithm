import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static int N, M;
	static int robotR;
	static int robotC;
	static int d; //로봇이 바라보는 방향
	static int[][] map;
	
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]); //방의 세로 크기 (3<=N<=50)
		M = Integer.parseInt(line[1]); //방의 가로 크기 (3<=M<=50)
		
		line = br.readLine().split(" ");
		robotR = Integer.parseInt(line[0]) + 1; //로봇의 현재 r값
		robotC = Integer.parseInt(line[1]) + 1; //로봇의 현재 c값
		d = Integer.parseInt(line[2]); //로봇이 바라보는 방향
		
		map = new int[N + 2][M + 2];
		
		for (int i = 1; i < N + 1; i++) {
			line = br.readLine().split(" ");
			
			for (int j = 1; j < M + 1; j++) {
				map[i][j] = Integer.parseInt(line[j - 1]);
			}
		}
		
		for (int j = 0; j < M + 2; j++) {
			map[0][j] = -1;
			map[N + 1][j] = -1;
		}
		
		for (int i = 0; i < N + 2; i++) {
			map[i][0] = -1;
			map[i][M + 1] = -1;
		}
		
		
		//알고리즘
		int cnt = 0;
		
		while(true) {
			//1. 현재 칸이 아직 청소되지 않은 경우, 현재 칸을 청소한다.
			if (map[robotR][robotC] == 0) {
				map[robotR][robotC] = -1;
				cnt++;
			}
			
			boolean flag = false;
			
			for (int i = 0; i < 4; i++) {
				int newR = robotR + dr[i];
				int newC = robotC + dc[i];

				//3. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 있는 경우,
				if (map[newR][newC] == 0) {
					//3-1) 반시계 방향으로 90도 회전한다.
					d = ((d - 1) + 4) % 4;
					
					//3-2) 바라보는 방향을 기준으로 앞쪽 칸이 청소되지 않은 빈 칸인 경우 한 칸 전진한다.
					if (map[robotR + dr[d]][robotC + dc[d]] == 0) {
						robotR = robotR + dr[d];
						robotC = robotC + dc[d];
						
					}
				
					flag = true;
					break;
				}
			}
			
			//3-3) 1번으로 돌아간다.
			if (flag) {
				continue;
			}
			
			//2. 현재 칸의 주변 4칸 중 청소되지 않은 빈 칸이 없는 경우,
			//2-1) 바라보는 방향을 유지한 채로 한 칸 후진할 수 있다면 한 칸 후진하고 1번으로 돌아간다.
			int tmpD = (d + 2) % 4;
			if (map[robotR + dr[tmpD]][robotC + dc[tmpD]] != 1) {
				robotR = robotR + dr[tmpD];
				robotC = robotC + dc[tmpD];
				continue;
			}
			
			//2-2) 바라보는 방향의 뒤쪽 칸이 벽이라 후진할 수 없다면 작동을 멈춘다.
			break;
		}
		
		
		//출력
		bw.write(cnt + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}

}