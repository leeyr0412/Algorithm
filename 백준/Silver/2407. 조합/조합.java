import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

/**
* 조합 / 실버3 / 걸린시간 / 4월 20일
*/
public class Main {
    static BigInteger[][] memo ;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        memo = new BigInteger[n+1][n+1];
        for(int a = 1; a <= n; a++){
            memo[a][0] = BigInteger.valueOf(1);
            memo[a][1]=BigInteger.valueOf(a);
            memo[a][a-1]=BigInteger.valueOf(a);
            memo[a][a]=BigInteger.valueOf(1);
        }
        System.out.println(c(n,m));
    }

    private static BigInteger c(int n, int m) {
        if(memo[n][m]!=null){
            return memo[n][m];
        }
        memo[n][m] =c(n-1,m-1).add(c(n-1,m));
        return memo[n][m];
    }
}