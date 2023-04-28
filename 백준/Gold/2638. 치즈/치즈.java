import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 치즈 / 골드3 / - / 4월 28일
 */
public class Main {
    static int[][] cheese;
    static int R;
    static int C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        cheese = new int[R][C];
        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                cheese[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = 0;
        while (true) {
            int[][] temp = new int[R][C];
            bfs(temp);
            if(remove(temp)==0)
                break;
            answer++;
        }
        System.out.println(answer);
    }

    private static int remove(int[][] temp) {
        int count = 0;
        for(int r= 1; r<R; r++ ){
            for(int c=1;c<C;c++){
                if(temp[r][c]>1){
                    cheese[r][c]=0;
                    count++;
                }
            }
        }
        return count;
    }

    private static void bfs(int[][] temp) {
        int[][] dr = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean[][] visited = new boolean[R][C];
        visited[0][0] = true;
        Deque<int[]> que = new ArrayDeque<>();
        que.offer(new int[]{0, 0});
        while (!que.isEmpty()) {
            int[] now = que.poll();
            for (int[] d : dr) {
                int newR = now[0] + d[0];
                int newC = now[1] + d[1];
                if (newR >= 0 && newR < R && newC >= 0 && newC < C) {
                    if(visited[newR][newC])
                        continue;
                    if (cheese[newR][newC] == 0) {
                        visited[newR][newC] = true;
                        que.offer(new int[]{newR, newC});
                    } else {
                        temp[newR][newC]++;
                    }
                }
            }
        }
    }
}