import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static boolean[][] graph;
    static boolean[] visited;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        graph = new boolean[N + 1][N + 1];
        visited = new boolean[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = true;
            graph[b][a] = true;
        }
        visited[1] = true;
        System.out.println(dfs(1) - 1);
    }

    private static int dfs(int now) {
        int count = 0;
        for (int next = 1; next <= N; next++) {
            if (now == next || visited[next]) { // 방문체크
                continue;
            }
            if (graph[now][next]) {
                visited[next] = true;
                count += dfs(next);
            }
        }
        return count + 1;
    }
}