import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 도로포장 / 골드1 / - / 4월 28일
 */
class Node implements Comparable<Node> {
    private int city;
    private long len;
    private int K;

    public Node(int city, long len, int k) {
        this.city = city;
        this.len = len;
        K = k;
    }

    public int getCity() {
        return city;
    }

    public long getLen() {
        return len;
    }

    public int getK() {
        return K;
    }

    @Override
    public int compareTo(Node o) {
        if (this.len < o.len) {
            return -1;
        } else if (this.len == o.len) {
            return o.K - this.K;
        }
        return 1;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        long[][] dp = new long[K + 1][N + 1];
        for (int i = 0; i <= K; i++) {
            Arrays.fill(dp[i], Long.MAX_VALUE);
        }
        List<int[]>[] cost = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            cost[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int len = Integer.parseInt(st.nextToken());
            cost[start].add(new int[]{end, len});
            cost[end].add(new int[]{start, len});
        }

        long answer = Long.MAX_VALUE;

        PriorityQueue<Node> queue = new PriorityQueue<>();

        dp[K][1] = 0;
        for (int[] city : cost[1]) {
            dp[K][city[0]] = city[1];
            queue.offer(new Node(city[0], city[1], K));
            dp[K - 1][city[0]] = 0;
            queue.offer(new Node(city[0], 0, K - 1));
        }
        boolean[][] visited = new boolean[K+1][N+1];
        visited[K][1] = true;

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            int curr = now.getCity();
            int remainK = now.getK();
            if (visited[remainK][curr])
                continue;
            visited[remainK][curr] = true;
            long len = now.getLen();

            for (int[] next : cost[curr]) {
                if (next[1] + len < dp[remainK][next[0]]) {
                    dp[remainK][next[0]] = len + next[1];
                    queue.offer(new Node(next[0], dp[remainK][next[0]] , remainK));
                }
                if(remainK>0){
                    if(len<dp[remainK-1][next[0]]){
                        dp[remainK-1][next[0]] = len;
                        queue.offer(new Node(next[0], dp[remainK-1][next[0]] , remainK-1));
                    }
                }
            }
        }

        for(int i = 0; i <= K; i++){
            answer = Math.min(answer,dp[i][N]);
        }

        System.out.println(answer);

    }
}