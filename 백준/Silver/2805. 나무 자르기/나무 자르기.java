import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;

public class Main {
	
	static int N, M;
	static Queue<Integer> pq;
	static int max;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));   
		
		//초기화
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]); //나무의 수
		M = Integer.parseInt(line[1]); //집으로 가져가려고 하는 나무의 길이
		
		pq = new PriorityQueue<>(Collections.reverseOrder());
		line = br.readLine().split(" ");
		for (int i = 0; i < N; i++) {
			pq.offer(Integer.parseInt(line[i]));
		}

		
		//알고리즘
		int cnt = 1;
		int sum = 0;
		int prev = pq.peek();
		max = pq.peek();
		
		while (!pq.isEmpty()) {
			int cur = pq.poll();
			
			if (sum + (cnt - 1) * (prev - cur) >= M) { //계산 결과 >= M이면
				max = (int) Math.floor(prev - (M - sum) / (double)(cnt - 1));
				break;
			} else {
				if (pq.isEmpty()) {
					if (cnt == 1) {
						max = (int) Math.floor(prev - (M - sum));
					} else {
						max = cur - (int) Math.ceil((M - sum - (cnt - 1) * (prev - cur)) / (double)cnt);
					}
					break;
				}
			}
			
			sum += (cnt - 1) * (prev - cur);
			cnt++;
			prev = cur;
			max = cur;
		}
		
		
		//출력
		bw.write(max + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}

}