class Solution {
    int[] dr = {-1, 0, 1, 0}; //상우하좌
    int[] dc = {0, 1, 0, -1};

    int[] oilSum;
    int idx;

    int max = -1;

    public int solution(int[][] land) {
        int size = land.length * land[0].length / 2 + 3; //최대
        oilSum = new int[size]; //2부터 시작
        idx = 2;

        for (int i = 0; i < land.length; i++) {
            for (int j = 0; j < land[0].length; j++) {
                if (land[i][j] == 1) {
                    dfs(land, i, j);
                    idx++;
                }
            }
        }

        for (int j = 0; j < land[0].length; j++) {
            //boolean[] 방식
            boolean[] set = new boolean[idx];
            for (int i = 0; i < land.length; i++) {
                if (land[i][j] > 1) {
                    set[land[i][j]] = true;
                }
            }

            int sum = 0;
            for (int a = 2; a < idx; a++) {
                if (set[a]) {
                    sum += oilSum[a];
                }
            }

            if (max < sum) {
                max = sum;
            }

            //HashSet 방식


        }

        return max;
    }

    public void dfs(int[][] land, int curR, int curC) {
        land[curR][curC] = idx;
        oilSum[idx]++;

        for (int i = 0; i < 4; i++) {
            int newR = curR + dr[i];
            int newC = curC + dc[i];

            if (newR >= 0 && newR < land.length && newC >= 0 && newC < land[0].length
                && land[newR][newC] == 1) {
                dfs(land, newR, newC);
            }
        }
    }
}