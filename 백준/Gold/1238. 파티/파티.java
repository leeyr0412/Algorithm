import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 파티 / 골드3 / 걸린시간 / 4월 3일
 */
class Node implements Comparable<Node> {
    private int from;
    private int to;
    private int cost;

    public Node(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public int compareTo(Node o) {
        return this.cost - o.cost;
    }
}

public class Main {
    static int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());       //마을 수
        int M = Integer.parseInt(st.nextToken());       //도로 수
        int X = Integer.parseInt(st.nextToken());       //파티가 열리는 마을

        int[][] graph = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++) {
            Arrays.fill(graph[i], INF);
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            graph[from][to] = cost;
        }

//        dijkstra(1, graph, N);


//        que.add(new Node(1))
//        while ()

        for (int use = 1; use <= N; use++) {
            for (int from = 1; from <= N; from++) {
                for (int to = 1; to <= N; to++) {
                    if (from == to) {
                        continue;
                    }
                    if (from == use || to == use) {
                        continue;
                    }
                    graph[from][to] = Math.min(graph[from][to], graph[from][use] + graph[use][to]);
                }
            }
        }

        int answer = 0;
        for (int i = 1; i <= N; i++) {
            if (i == X)
                continue;
            answer = Math.max(answer, graph[i][X] + graph[X][i]);
        }
        System.out.println(answer);
    }

//    private static void dijkstra(int start, int[][] graph, int N) {
//        PriorityQueue<Node> que = new PriorityQueue<>();
//        for(int to = 2; to <=N; to++) {
//            if(graph[start][to]<INF) {
//                que.add(new Node(start, to, graph[start][to]));
//            }
//        }
//    }
}