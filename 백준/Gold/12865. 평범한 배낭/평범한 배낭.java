import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] W = new int[N + 1];
        int[] V = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }
        int[][] dp = new int[N + 1][K + 1];
        for (int i = 1; i <= N; i++) {
            for (int weight = 0; weight <= K; weight++) {
                dp[i][weight] = dp[i - 1][weight];
                if (W[i] <= weight) {
                    dp[i][weight] = Math.max(dp[i - 1][weight], dp[i - 1][weight - W[i]] + V[i]);
                }
            }
        }
        System.out.println(dp[N][K]);
    }
}