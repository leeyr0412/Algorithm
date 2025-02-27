import java.util.*;

class Solution {
    public int solution(int n, int[][] edge) {
        int answer = 0;
        List<Integer>[] graph = new List[n + 1];
        for (int i = 0; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] e : edge) {
            graph[e[0]].add(e[1]);
            graph[e[1]].add(e[0]);
        }

        int[] counts = new int[20005];
        boolean[] visited = new boolean[n + 1];
        Deque<Node> q = new ArrayDeque<>();
        q.add(new Node(1, 0));
        visited[1] = true;
        int weight = 0;
        while (!q.isEmpty()) {
            Node now = q.poll();
            weight = now.count;
            counts[weight]++;
            for (int next : graph[now.num]) {
                if (!visited[next]) {
                    visited[next] = true;
                    q.add(new Node(next, weight + 1));
                }
            }
        }
        return counts[weight];
    }
}

class Node {
    int num;
    int count;

    public Node(int num, int count) {
        this.num = num;
        this.count = count;
    }
}