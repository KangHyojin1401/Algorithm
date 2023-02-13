import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		String[] line = br.readLine().split(" ");
		int N = Integer.parseInt(line[0]); // 회전 초밥 벨트에 놓인 접시의 수
		int d = Integer.parseInt(line[1]); // 초밥의 가짓수 
		int k = Integer.parseInt(line[2]); // 연속해서 먹는 접시의 수
		int c = Integer.parseInt(line[3]); // 쿠폰 번호
		
		int[] sushi = new int[N];
		for (int i = 0; i < N; i++) {
			sushi[i] = Integer.parseInt(br.readLine());
		}

		
		//알고리즘
		HashMap<Integer, Integer> map = new HashMap<Integer, Integer>();
		PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> Integer.compare(o2, o1)); // int[] : 인덱스, 초밥 가짓수
		
		//첫번째 경우 초기화
		for (int j = 0; j < k; j++) {
			map.put(sushi[j], map.getOrDefault(sushi[j], 0) + 1);
		}
		
		map.put(c, map.getOrDefault(c, 0) + 1); //쿠폰 추가
		
		pq.add(map.size());
		
		//두번째 경우부터
		for (int i = 1; i < N; i++) {
			//이전 턴의 첫번째 원소 뺌
			int tmp = map.get(sushi[i - 1]);
			if (tmp == 1) {
				map.remove(sushi[i - 1]);
			} else {
				map.put(sushi[i - 1], tmp - 1);
			}
			
			//현재 + k 원소 추가
			map.put(sushi[(i + k - 1) % N], map.getOrDefault(sushi[(i + k - 1) % N], 0) + 1);
			
			pq.add(map.size());
		}
		
			
		//출력
		bw.write(pq.poll() + "\n");
		bw.flush();
		bw.close();
		
	}

}