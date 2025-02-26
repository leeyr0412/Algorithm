import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<int[]>[] tree;
    static boolean[] visited;
    static int maxWeight;
    static int farthest;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        if (N == 1) {
            System.out.println(0);
            return;
        }
        tree = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            tree[parent].add(new int[]{child, weight});
            tree[child].add(new int[]{parent, weight});
        }
        visited = new boolean[N + 1];
        maxWeight = 0;
        farthest = 0;
        dfs(1, 0);
        Arrays.fill(visited, false);

        dfs(farthest, 0);
        System.out.println(maxWeight);
    }

    private static void dfs(int node, int count) {
        visited[node] = true;
        if (count > maxWeight) {
            farthest = node;
            maxWeight = count;
        }
        for (int[] next : tree[node]) {
            if (!visited[next[0]]) {
                dfs(next[0], count + next[1]);
            }
        }
    }
}