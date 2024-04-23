import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    private static final int INF = (int) 1e9;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int[][] map = new int[v + 1][v + 1];
        for (int r = 1; r <= v; r++) {
            for (int c = 1; c <= v; c++) {
                if(r!=c){
                    map[r][c] = INF;
                }
            }
        }

        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            map[from][to] = Math.min(cost,map[from][to]);
        }
        for (int from = 1; from <= v; from++) {
            for (int to = 1; to <= v; to++) {
                if (from == to)
                    continue;
                for (int use = 1; use <= v; use++) {
                    if(use!=from && use!= to) {
                        map[from][to] = Math.min(map[from][to], map[from][use] + map[use][to]);
                    }
                }
            }
        }
        int result = Integer.MAX_VALUE;
        for (int from = 1; from <= v; from++) {
            for (int to = 1; to <= v; to++) {
                if (from == to)
                    continue;
                result = Math.min(result, map[from][to] + map[to][from]);
            }
        }
        System.out.println(result < INF ? result : -1);
    }
}