import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	
	static int N;
	static int[][] meeting;
	static int max = 0;
	
	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		N = Integer.parseInt(br.readLine()); //회의의 수
		meeting = new int[N][2];
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			meeting[i][0] = Integer.parseInt(line[0]);
			meeting[i][1] = Integer.parseInt(line[1]);
		}
		
		
		//알고리즘
		//1. 정렬
		
		/* 1차 시도: 시작 시간, 끝나는 시간 순으로 오름차순 */
//		Arrays.sort(meeting, (o1, o2) -> {
//			if (o1[0] == o2[0]) {
//				return o1[1] - o2[1];
//			}
//			return o1[0] - o2[0];
//		});
		
		/* 2차 시도: 끝나는 시간-시작 시간 으로 오름차순 */
//		Arrays.sort(meeting, (o1, o2) -> {
//			return (o1[1] - o1[0]) - (o2[1] - o2[0]);
//		});
		
		/* 끝나는 시간, 시작 시간 순으로 오름차순 */
		Arrays.sort(meeting, (o1, o2) -> {
			if (o1[1] == o2[1]) {
				return o1[0] - o2[1];
			}
			return o1[1] - o2[1];
		});
		
		//2. 회의의 최대 개수 greedy로 세기
		int prevEndTime = 0;
		for (int i = 0; i < N; i++) {
			if (prevEndTime <= meeting[i][0]) { //이전 회의의 끝나는 시간 <= 현재 회의의 시작 시간
				max++;
				prevEndTime = meeting[i][1];
			}
		}
		
		
		//출력
		bw.write(max + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}

}