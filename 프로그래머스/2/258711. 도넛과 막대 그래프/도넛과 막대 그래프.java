import java.util.*;

class Solution {
    int createdNode = 0;
    int doughnutCnt = 0;
    int stickCnt = 0;
    int eightCnt = 0;

    int nodeCnt = 0; //전체 노드 개수
    Node[] nodes;
    NodeInOut[] inOut;

    class Node {
        int n; //정점 번호
        Node next;

        public Node(int n, Node next) {
            this.n = n;
            this.next = next;
        }
    }

    class NodeInOut {
        public int n; //정점 번호
        public int in; //진입 차수
        public int out; //진출 차수

        public NodeInOut(int n) {
            this.n = n;
            this.in = 0;
            this.out = 0;
        }
    }

    public void dfs(int startNode, int curNode, int depth) {
        if (inOut[curNode].out == 2) {
            eightCnt++;
            return;
        }

        if (inOut[curNode].out == 0) {
            stickCnt++;
            return;
        }

        if (nodes[curNode].n == startNode) {
            doughnutCnt++;
            return;
        }

        dfs(startNode, nodes[curNode].n, depth + 1);
    }

    public int[] solution(int[][] edges) {
        //초기화
        int edgeCnt = edges.length;
        for (int i = 0; i < edgeCnt; i++) {
            for (int j = 0; j < 2; j++) {
                if (nodeCnt < edges[i][j]) {
                    nodeCnt = edges[i][j];
                }
            }
        }

        nodes = new Node[nodeCnt];
        inOut = new NodeInOut[nodeCnt];
        for (int i = 0; i < edgeCnt; i++) {
            int from = edges[i][0] - 1;
            int to = edges[i][1] - 1;

            nodes[from] = new Node(to, nodes[from]);

            if (inOut[from] == null) {
                inOut[from] = new NodeInOut(from);
            }

            if (inOut[to] == null) {
                inOut[to] = new NodeInOut(to);
            }

            inOut[from].out++;
            inOut[to].in++;
        }

        //알고리즘

        //1) 생성한 정점 찾기
        int max = -1;
        for (int i = 0; i < nodeCnt; i++) {
            if (inOut[i].in == 0 && max < inOut[i].out) {
                max = inOut[i].out;
                createdNode = inOut[i].n;
            }
        }

        //2) 생성한 정점으로부터 dfs 돌려 모양 찾기
        for (Node temp = nodes[createdNode]; temp != null; temp = temp.next) {
            dfs(temp.n, temp.n, 0);
        }

        //출력
        int[] answer = {createdNode + 1, doughnutCnt, stickCnt, eightCnt}; //생성한 정점의 번호, 도넛 모양 그래프의 수, 막대 모양 그래프의 수, 8자 모양 그래프의 수
        return answer;
    }
}