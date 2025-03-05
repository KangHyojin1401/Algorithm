import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map.Entry;

public class Main {
	
	static int r, c, k;
	static int[][] arr;
	static int[] numCnt = new int[101];
	static int time = 1;

	public static void main(String[] args) throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		String[] line = br.readLine().split(" ");
		r = Integer.parseInt(line[0]);
		c = Integer.parseInt(line[1]);
		k = Integer.parseInt(line[2]);
		
		arr = new int[3][3];
		for (int i = 0; i < 3; i++) {
			line = br.readLine().split(" ");
			for (int j = 0; j < 3; j++) {
				arr[i][j] = Integer.parseInt(line[j]);
			}
		}

		
		//알고리즘
		if (r - 1 < arr.length && c - 1 < arr[0].length && arr[r - 1][c - 1] == k) {
			System.out.println("0");
			return;
		}
		
		while (time <= 100) {
			int rowLength = arr.length;
			int colLength = arr[0].length;
			
			if (rowLength >= colLength) { //r연산
				int[][] tmp = new int[rowLength][colLength * 2];
				int maxLen = 0;
				
				for (int i = 0; i < rowLength; i++) {
					HashMap<Integer, Integer> map = new HashMap<>();
					
					//1) count (0 제외)
					for (int j = 0; j < colLength; j++) {
						if (arr[i][j] != 0) {
							map.put(arr[i][j], map.getOrDefault(arr[i][j], 0) + 1);
						}
					}
					
					//2) 최대 길이 갱신
					maxLen = Math.max(maxLen, map.size());
					
					//3) map to array
					int[][] t = new int[map.size()][2];
					int idx = 0;
					for (Entry<Integer, Integer> entry : map.entrySet()) {
						t[idx][0] = entry.getKey(); //숫자
						t[idx++][1] = entry.getValue(); //등장 횟수
					}
					
					//4) sort
					Arrays.sort(t, (o1, o2) -> {
						if (o1[1] == o2[1]) {
							return o1[0] - o2[0];
						} 
						
						return o1[1] - o2[1];
					});
					
					//5) 길이 100까지 저장
					for (int a = 0; a < t.length * 2 && a < 100; a += 2) {
						if (a == 0) {
							tmp[i][a] = t[0][0];
							tmp[i][a + 1] = t[0][1];
						} else {
							tmp[i][a] = t[a / 2][0];
							tmp[i][a + 1] = t[a / 2][1];
						}
					}
				}
			
				//모든 행에서 맨 뒤 열의 0 자르기
				for (int i = 0; i < rowLength; i++) {
					tmp[i] = Arrays.copyOf(tmp[i], maxLen * 2);
				}
				
				arr = tmp;
			} else { //c연산
				int[][] tmp = new int[rowLength * 2][colLength];
				int maxLen = 0;
				
				for (int j = 0; j < colLength; j++) {
					HashMap<Integer, Integer> map = new HashMap<>();
					
					//1) count (0 제외)
					for (int i = 0; i < rowLength; i++) {
						if (arr[i][j] != 0) {
							map.put(arr[i][j], map.getOrDefault(arr[i][j], 0) + 1);
						}
					}
					
					//2) 최대 길이 갱신
					maxLen = Math.max(maxLen, map.size());
					
					//3) map to array
					int[][] t = new int[map.size()][2];
					int idx = 0;
					for (Entry<Integer, Integer> entry : map.entrySet()) {
						t[idx][0] = entry.getKey(); //숫자
						t[idx++][1] = entry.getValue(); //등장 횟수
					}
					
					//4) sort
					Arrays.sort(t, (o1, o2) -> {
						if (o1[1] == o2[1]) {
							return o1[0] - o2[0];
						} 
						
						return o1[1] - o2[1];
					});
					
					//5) 길이 100까지 저장
					for (int a = 0; a < t.length * 2 && a < 100; a += 2) {
						if (a == 0) {
							tmp[a][j] = t[0][0];
							tmp[a + 1][j] = t[0][1];
						} else {
							tmp[a][j] = t[a / 2][0];
							tmp[a + 1][j] = t[a / 2][1];
						}
					}
				}
			
				
				//모든 열에서 맨 뒤 행의 0 잘라서 붙여넣기
				arr = Arrays.copyOf(tmp, maxLen * 2);
			}
			
			
			if (r - 1 < arr.length && c - 1 < arr[0].length && arr[r - 1][c - 1] == k) {
				break;
			}
			
			time++;
		}
		
		if (time == 101) {
			time = -1;
		}
		
	
		//출력
		bw.write(time + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}

}
