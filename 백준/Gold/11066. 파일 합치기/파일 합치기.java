import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 파일합치기 / 골드3 / 걸린시간 / 4월 5일
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            int K = Integer.parseInt(br.readLine());
            int[][] dp = new int[K + 1][K + 1];

            int[] page = new int[K + 1];
            st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= K; i++) {
                page[i] = Integer.parseInt(st.nextToken());
            }
            for (int r = 1; r < K; r++) {
                dp[r][r + 1] = page[r] + page[r + 1];
            }
            //누적합
            int[] sum = new int[K + 1];
            for (int i = 1; i <= K; i++) {
                sum[i] = sum[i - 1] + page[i];
            }

            for (int end = 3; end <= K; end++) {
                for (int start = end-1; start >0; start--) {
                    dp[start][end] = Integer.MAX_VALUE;
                    for(int index = start; index<end;index++){
                        dp[start][end] = Math.min(dp[start][end], dp[start][index]+dp[index+1][end] + sum[end] - sum[start-1]);
                    }
                }
            }
            System.out.println(dp[1][K]);
        }
    }
}