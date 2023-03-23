import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 동전2 / 골드5 / 걸린시간 / 3월 22일
 */
public class Main {
    static int INF = (int) 1e9;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int [][] dp = new int[n+1][k+1];
        int[] coin  = new int[n+1];
        //동전 입력받기
        for(int i = 1; i <= n; i++){
            coin[i] = Integer.parseInt(br.readLine());
        }
        //dp 초기화
        for(int i = 1; i <= k; i++){
            dp[0][i] = INF;
        }

        Arrays.sort(coin);
        for(int i = 1; i <= n; i++){
            for(int m = 1; m< k+1; m++){
                int money = m;
                if(money<coin[i]){
                    dp[i][m] = dp[i-1][m];
                    continue;
                } else if (money%coin[i]==0) {
                    dp[i][m] = money/coin[i];
                    continue;
                }else{
                    int min = INF;
                    int d = 0;
                    while (money>0){
                        d++;
                        money-=coin[i];
                        if(money<0){
                            break;
                        }
                        min = Math.min(min,dp[i][money]+d);
                        if(money==0)
                            break;
                    }
                    dp[i][m] = Math.min(min,dp[i-1][m]);
                }
            }
        }
        System.out.println(dp[n][k]==INF?-1:dp[n][k]);
    }
}