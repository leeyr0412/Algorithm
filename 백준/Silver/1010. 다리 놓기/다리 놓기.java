import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 다리놓기_이예리 / 실버5 / 걸린시간 / 3월 29일
 */
public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();
        int [][] memo = new int[30][30];

        for(int i = 1; i < 30; i++){
            memo[i][0]=1;
            memo[i][i]=1;
        }
        for(int n = 2; n < 30; n++){
            for(int r = 1; r < n;r++){
                memo[n][r] = memo[n-1][r-1]+memo[n-1][r];
            }
        }
        int t = Integer.parseInt(br.readLine());
        for(int i = 0; i < t; i++){
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());
            System.out.println(memo[n][r]);
        }
    }
}