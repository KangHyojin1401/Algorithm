import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	
	static int N; 
	static int[][] player;
	static int maxScore = Integer.MIN_VALUE; //최대 점수
	static boolean[] base = new boolean[3]; //1루, 2루, 3루
	
	static int[] selected = new int[9]; //순열 뽑을 때

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		N = Integer.parseInt(br.readLine()); //이닝 수
		player = new int[N][];
		
		for (int i = 0; i < N; i++) {
			player[i] = new int[9];
			String[] line = br.readLine().split(" ");

			for (int j = 0; j < 9; j++) {
				player[i][j] = Integer.parseInt(line[j]);
			}
		}
		 
		perm(0, 1<<0); //1번 선수 자리 고정
		
		
		//출력
		bw.write(maxScore + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void perm(int cnt, int flag) {
		if (cnt == 9) { //타순 정해졌으면
			int score = 0; //총 점수
			int j = 0;
				
			for (int i = 0; i < N; i++) { //N이닝동안
				int outCount = 0; //이닝 당 아웃카운트
				base = new boolean[3];
				
				while (outCount != 3) {
					int result = player[i][selected[j]]; //정해진 공격 실행
					
					if (result == 0) { //아웃일 경우
						outCount++;
					} else { //1, 2, 3루타, 홈런
						//주자
						for (int k = 2; k >= 0; k--) {
							if (base[k]) { //루상에 주자가 있어야 하고 
								if ((k + 1) + result > 3) { //주자가 위치한 루 + 타자의 루타 > 3 이면 득점
									score++;
								} else { //아니면 그냥 진루
									base[k + result] = true;
								}		
								base[k] = false;
							} 
						}
						
						//타자
						if (result == 4) {
							score++;
						} else {
							base[result - 1] = true;
						}
					}
					
					if (++j == 9) { //한바퀴 돌면 0으로 다시 돌려줌
						j = 0; 
					}
				}
			}
			
			if (maxScore < score) {
				maxScore = score;
			}
			
			return;
		}
		
		if (cnt == 3) { //1번 선수 -> 4번 타순
			selected[cnt] = 0;
			perm(cnt + 1, flag);
		}
	
		
		for (int i = 0; i < 9; i++) {
			if ((flag & 1<<i) == 0) {
				selected[cnt] = i;
				perm(cnt + 1, flag | 1<<i);
			}
		}
	}
	
	static void print() {
		
	}

}