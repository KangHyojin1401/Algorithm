import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Collections;
import java.util.PriorityQueue;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		int N = Integer.parseInt(br.readLine()); //백준이가 외치는 정수의 개수 (1<=N<=100,000)
		
		PriorityQueue<Integer> max = new PriorityQueue<>(Collections.reverseOrder());
		PriorityQueue<Integer> min = new PriorityQueue<>();
		
		for (int i = 0; i < N; i++) {
			int n = Integer.parseInt(br.readLine());
			
			if (max.size() == min.size()) {
				max.add(n);
				
				if (!min.isEmpty() && max.peek() > min.peek()) {
					min.add(max.poll());
					max.add(min.poll());
				}
			} else {
				min.add(n);
				
				if (max.peek() > min.peek()) {
					min.add(max.poll());
					max.add(min.poll());
				}
			}
			
			bw.write(max.peek() + "\n");
		}
		
		
		//출력
		bw.flush();
		bw.close();
		br.close();
		
	}

}