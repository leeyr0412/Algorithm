import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 외판원순회2 / 실버2 / 걸린시간 / 3월 29일
 */
public class Main {
    static int n;
    static int[][] W;
    static int answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;//= new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        n = Integer.parseInt(br.readLine());
        W = new int[n + 1][n + 1];
        for (int r = 1; r <= n; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= n; c++) {
                W[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        answer = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            boolean[] visited = new boolean[n + 1];
            visited[i] = true;
            answer = Math.min(dfs(i, i, visited, 0, 1), answer);
        }
        System.out.println(answer);
    }

    private static int dfs(int start, int curr, boolean[] visited, int cost, int cnt) {
        if (cnt == n) {
            if(W[curr][start]>0)
                return cost + W[curr][start];
            else return Integer.MAX_VALUE;
        }
        if (cost > answer) {
            return Integer.MAX_VALUE;
        }
        int temp = Integer.MAX_VALUE;
        for (int i = 1; i <= n; i++) {
            if(curr==i)
                continue;
            if (!visited[i] && W[curr][i] > 0) {
                visited[i] = true;
                temp = Math.min(dfs(start, i, visited, cost + W[curr][i], cnt + 1),temp);
                visited[i]=false;
            }
        }
        return temp;
    }
}