import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 플로이드 / 골드4 / 시간 / 4월 19일
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int city = Integer.parseInt(br.readLine());
        int busNum = Integer.parseInt(br.readLine());
        int INF = (int) 1e9;

        int[][] costs = new int[city + 1][city + 1];
        for (int i = 1; i <= city; i++) {
            Arrays.fill(costs[i], INF);
        }

        for (int i = 0; i < busNum; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            costs[start][end] = Math.min(cost, costs[start][end]);
        }

        for (int use = 1; use <= city; use++) {
            for (int start = 1; start <= city; start++) {
                for (int end = 1; end <= city; end++) {
                    costs[start][end] = Math.min(costs[start][end], costs[start][use] + costs[use][end]);
                }
            }
        }

        for (int start = 1; start <= city; start++) {
            for (int end = 1; end <= city; end++) {
                if(start==end){
                    costs[start][end]=0;
                }
                if (costs[start][end]>=INF){
                    System.out.print(0+" ");
                }else{
                    System.out.print(costs[start][end]+" ");
                }
            }
            System.out.println();
        }
    }
}