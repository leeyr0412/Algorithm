import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        int INF = (int) 1e9;
        int[][] weight = new int[n + 1][n + 1];
        for (int r = 1; r <= n; r++) {
            Arrays.fill(weight[r], INF);
        }
        for (int i = 1; i <= n; i++) {
            weight[i][i] = 0;
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            weight[from][to] = Math.min(weight[from][to], cost);
        }

        for (int use = 1; use <= n; use++) {
            for (int from = 1; from <= n; from++) {
                for (int to = 1; to <= n; to++) {
                    weight[from][to] = Math.min(weight[from][to], weight[from][use] + weight[use][to]);
                }
            }
        }

        for (int r = 1; r <= n; r++) {
            for (int c = 1; c <= n; c++) {
                if (weight[r][c] >= INF) {
                    sb.append(0).append(" ");
                } else {
                    sb.append(weight[r][c]).append(" ");
                }
            }
            sb.append("\n");
        }
        System.out.println(sb);
    }
}