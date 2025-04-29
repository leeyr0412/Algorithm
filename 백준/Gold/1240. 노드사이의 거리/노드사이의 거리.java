import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Integer>[] tree;
    static int[][] dist;
    static boolean[] visited;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        tree = new ArrayList[N + 1];
        dist = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 0; i < N - 1; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());
            tree[a].add(b);
            tree[b].add(a);
            dist[a][b] = distance;
            dist[b][a] = distance;
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            visited = new boolean[N + 1];
            visited[start] = true;
            answer = 0;
            dfs(start, end, 0);
            System.out.println(answer);
        }
    }

    private static void dfs(int now, int find, int count) {
        if (now == find) {
            answer = count;
            return;
        }
        for (Integer next : tree[now]) {
            if (!visited[next]) {
                visited[next] = true;
                dfs(next, find, count + dist[now][next]);
            }
        }
    }
}