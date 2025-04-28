import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[][] cost = new int[3][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int color = 0; color < 3; color++) {
                cost[color][i] = Integer.parseInt(st.nextToken());
            }
        }
        int[][] dp = new int[3][N];
        int red = 0;
        int green = 1;
        int blue = 2;
        dp[red][0] = cost[red][0];
        dp[green][0] = cost[green][0];
        dp[blue][0] = cost[blue][0];
        for (int i = 1; i < N; i++) {
            dp[red][i] = Math.min(dp[green][i - 1], dp[blue][i - 1]) + cost[red][i];
            dp[green][i] = Math.min(dp[red][i - 1], dp[blue][i - 1]) + cost[green][i];
            dp[blue][i] = Math.min(dp[green][i - 1], dp[red][i - 1]) + cost[blue][i];
        }
        System.out.println(Math.min(Math.min(dp[red][N - 1], dp[blue][N - 1]), dp[green][N - 1]));
    }
}