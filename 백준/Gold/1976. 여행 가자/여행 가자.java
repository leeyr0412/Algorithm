import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 여행가자 / 골드4 / 걸린시간 / 4월 3일
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int INF = (int) 1e9;
        int city = Integer.parseInt(br.readLine());
        int plan = Integer.parseInt(br.readLine());
        int[][] graph = new int[city + 1][city + 1];
        for (int i = 1; i <= city; i++) {
            Arrays.fill(graph[i],INF);
        }
        for (int r = 1; r <= city; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= city; c++) {
                int i = Integer.parseInt(st.nextToken());
                if (r == c) {
                    graph[r][c] = 0;
                    continue;
                }
                if (i != 0) {
                    graph[r][c] = i;
                    graph[c][r] = i;
                }
            }
        }

        for (int use = 1; use <= city; use++) {
            for (int from = 1; from <= city; from++) {
                for (int to = 1; to <= city; to++) {
                    if (from == to) {
                        continue;
                    }
                    graph[from][to] = Math.min(graph[from][to], graph[from][use] + graph[use][to]);
                }
            }
        }
        st = new StringTokenizer(br.readLine());
        int [] list = new int[plan];
        for(int i = 0; i < plan; i++){
            list[i] = Integer.parseInt(st.nextToken());
        }
        boolean answer = true;
        for (int p = 1; p < plan; p++) {
            if(graph[list[p-1]][list[p]]==INF){
                answer=false;
                break;
            }
        }
        if(answer){
            System.out.println("YES");
        }else{
            System.out.println("NO");
        }
    }
}