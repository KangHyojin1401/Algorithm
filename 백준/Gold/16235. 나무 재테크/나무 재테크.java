import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

public class Main {
	
	static int N, M, K;
	static int[][] ground;
	static int[][] A;
	static int alive;
	static LinkedList<Tree>[][] trees;
	
	static int[] dr = {-1, -1, -1, 0, 1, 1, 1, 0};
	static int[] dc = {-1, 0, 1, 1, 1, 0, -1, -1};
	
	static class Tree {
		int age;
		
		public Tree(int startAge) {
			this.age = startAge;
		}

//		@Override
//		public String toString() {
//			return age + "";
//		}
	}

	public static void main(String[] args) throws Exception{

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		//초기화
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		K = Integer.parseInt(line[2]);
		
		alive = M;
		
		ground = new int[N][N]; //땅
		A = new int[N][N]; //겨울에 추가되는 양분
		
		for (int i = 0; i < N; i++) {
			Arrays.fill(ground[i], 5);
			
			line = br.readLine().split(" ");
			for (int j = 0; j < N; j++) {
				A[i][j] = Integer.parseInt(line[j]);
			}
		}
		
		trees = new LinkedList[N][N];

		for (int i = 0; i < M; i++) {
			line = br.readLine().split(" ");
			int r = Integer.parseInt(line[0]) - 1;
			int c = Integer.parseInt(line[1]) - 1;
			int age = Integer.parseInt(line[2]);
			
			trees[r][c] = new LinkedList<Tree>();
			trees[r][c].add(new Tree(age));
		}
		
		
		//알고리즘
		for (int i = 1; i <= K; i++) {
			springAndSummer();
			fall();
			winter();
		}
		
		
		//출력
		bw.write(alive + "\n");
		bw.flush();
		bw.close();
		br.close();
		
	}
	
	static void springAndSummer() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (trees[i][j] != null) { //그 칸에 나무가 있으면
					int dieIndex = -1;
					
					for (int k = 0, size = trees[i][j].size(); k < size; k++) {
						Tree cur = trees[i][j].get(k); 
						
						if (dieIndex == -1 && ground[i][j] >= cur.age) {
							ground[i][j] -= cur.age; //자신의 나이만큼 양분을 먹고
							cur.age++; //나이가 1 증가
						} else {
							ground[i][j] += cur.age / 2; //여름에는 봄에 죽은 나무가 양분으로

							if (dieIndex == -1) {
								dieIndex = k;
							}
							
							alive--;
						}
					}
					
					if (dieIndex != -1) {
						for (int k = dieIndex, size = trees[i][j].size(); k < size; k++) {
							trees[i][j].removeLast();
						}
					}
				}
			}
		}
	}
	
	static void fall() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if (trees[i][j] != null) { //그 칸에 나무가 있으면
					for (int k = 0, size = trees[i][j].size(); k < size; k++) {
						Tree cur = trees[i][j].get(k);
						
						if (cur.age % 5 == 0) {
							for (int a = 0; a < 8; a++) {
								int newR = i + dr[a];
								int newC = j + dc[a];
								
								if (newR >= 0 && newR < N && newC >= 0 && newC < N) {
									if (trees[newR][newC] == null) {
										trees[newR][newC] = new LinkedList<Tree>();
									}
									
									trees[newR][newC].addFirst(new Tree(1));
									
									alive++;									
								}
							}
						}
					}
				}
			}
		}
	}
	
	static void winter() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				ground[i][j] += A[i][j];
			}
		}
	}

}