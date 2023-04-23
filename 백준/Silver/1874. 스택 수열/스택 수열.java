import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

public class Main {

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		//초기화
		int n = Integer.parseInt(br.readLine());
	
		Stack<Integer> stack = new Stack<>();
		int cnt = 0;
		int pop = 0;
		
		for (int i = 0; i < n; i++) {
			int num = Integer.parseInt(br.readLine());
			
			if (num > cnt) {
				for (int j = cnt; j < num; j++) {
					stack.push(++cnt);
					sb.append("+\n");
//					bw.write("+\n");
				}
				
				pop = stack.pop();
				sb.append("-\n");
//				bw.write("-\n");
			} else {
				pop = stack.pop();
				
				if (pop != num) {
					System.out.println("NO");
					
//					bw.close();
					br.close();
					return;
				}

				sb.append("-\n");
//				bw.write("-\n");
			}
		}
		
		
		//출력
		System.out.println(sb.toString());
//		bw.flush();
//		bw.close();
		br.close();
		
	}

}