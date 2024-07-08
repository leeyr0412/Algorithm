import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n];

        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int answer = 0;
        for (int i = 1; i <= m; i++) {
            st = new StringTokenizer(br.readLine());
            int node1 = Integer.parseInt(st.nextToken());
            int node2 = Integer.parseInt(st.nextToken());
            int parent1 = findParent(node1);
            int parent2 = findParent(node2);
            if (parent1 == parent2) {
                answer = i;
                break;
            }
            if (parent1 < parent2) {
                parent[parent2] = parent1;

            } else {
                parent[parent1] = parent2;
            }
        }
        System.out.println(answer);
    }

    private static void union(int newParent, int oldParent) {
        for (int i = 0; i < parent.length; i++) {
            if (parent[i] == oldParent) {
                parent[i] = newParent;
            }
        }
    }

    private static int findParent(int node) {
        if (node == parent[node]) {
            return node;
        }
        parent[node] = findParent(parent[node]);
        return parent[node];
    }
}