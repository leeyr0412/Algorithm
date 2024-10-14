import java.util.*;

class Solution {

    static List<int[]>[] graph;
    static int N;
    static int INF = (int) 10e9;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        N = n;
        graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] fare : fares) {
            graph[fare[0]].add(new int[]{fare[1], fare[2]});
            graph[fare[1]].add(new int[]{fare[0], fare[2]});
        }

        int answer = INF;

        int[] sDist = dijkstra(s);
        int[] aDist = dijkstra(a);
        int[] bDist = dijkstra(b);
        for (int i = 1; i <= n; i++) {
            if (sDist[i] == INF || aDist[i] == INF || bDist[i] == INF) {
                continue;
            }
            answer = Math.min(answer, sDist[i] + aDist[i] + bDist[i]);
        }
        return answer;
    }

    static public int[] dijkstra(int from) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[from] = 0;
        pq.add(new Node(0, from));
        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (dist[now.getNow()] != 0 && dist[now.getNow()] != INF) {
                continue;
            }
            dist[now.getNow()] = now.getDist();

            for (int[] nextNode : graph[now.getNow()]) {
                if (dist[nextNode[0]] == INF) {
                    pq.add(new Node(now.getDist() + nextNode[1], nextNode[0]));
                }
            }
        }
        return dist;
    }
}

class Node implements Comparable<Node> {
    private int dist;
    private int now;

    public Node(int dist, int to) {
        this.dist = dist;
        this.now = to;
    }

    public int getDist() {
        return this.dist;
    }

    public int getNow() {
        return this.now;
    }

    @Override
    public int compareTo(Node node) {
        return this.dist - node.getDist();
    }
}