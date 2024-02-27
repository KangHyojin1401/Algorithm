/*
인접리스트 + BFS
*/

import java.util.*;
import java.io.*;

class Solution {
    public class Node {
        int n;
        Node next;
        int depth;
        
        public Node() {}
        
        public Node(int n) {
            this.n = n;
            this.next = null;
            this.depth = 0;
        }
        
        public Node(int n, Node next) {
            this.n = n;
            this.next = next;
            this.depth = 0;
        }
    }
    
    public int solution(int n, int[][] edge) {
        Node[] nodes = new Node[n];
        
        for (int[] e : edge) {
            int from = e[0] - 1;
            int to = e[1] - 1;
            
            nodes[from] = new Node(to, nodes[from]);
            nodes[to] = new Node(from, nodes[to]);
        }
        
        Queue<Node> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n];
        
        queue.offer(new Node(0));
        visited[0] = true;
        
        int answer = 0;
        
        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            
            //depth가 달라질 때마다 초기화
            Node peek = queue.peek();
            if (peek != null && cur.depth != peek.depth) {
                answer = 0;
            } else {
                answer++;
            }
            
            for (Node temp = nodes[cur.n]; temp != null; temp = temp.next) {
                if (!visited[temp.n]) {
                    temp.depth = cur.depth + 1;
                    queue.offer(temp);
                    visited[temp.n] = true;
                }
            }
        }
        
        return answer;
    }
}