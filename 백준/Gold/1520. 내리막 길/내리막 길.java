import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int R;
    static int C;
    static int[][] map;
    static int[][] dp;
    static int[][] dr = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        map = new int[R][C];
        dp = new int[R][C];
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            Arrays.fill(dp[r], -1);
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(dfs(0, 0));
    }

    private static int dfs(int r, int c) {
        if (r == R - 1 && c == C - 1) {
            return 1;
        }
        if (dp[r][c] != -1) {
            return dp[r][c];
        }
        dp[r][c] = 0;
        for (int[] d : dr) {
            int nextR = r + d[0];
            int nextC = c + d[1];
            if (nextR < 0 || nextC < 0 || nextR >= R || nextC >= C) {
                continue;
            }
            if (map[r][c] > map[nextR][nextC]) {
                if (dp[nextR][nextC] != -1) {
                    dp[r][c] += dp[nextR][nextC];
                    continue;
                }
                dp[r][c] += dfs(nextR, nextC);
            }
        }
        return dp[r][c];
    }
}