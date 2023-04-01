import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

/**
 * 치즈 / 골드4 / 걸린시간 / 3월 31일
 */
public class Main {
    static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        int[][] map = new int[R][C];

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int answerCount = 0;
        int temp = 0;
        int time = 0;
        while ((temp = countCheese(map)) > 0) {
            answerCount = temp;
            time++;
            bfs(map);
        }
        System.out.println(time);
        System.out.println(answerCount);
    }

    private static void bfs(int[][] map) {
        int[][] dr = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Deque<int[]> que = new LinkedList<>();
        boolean[][] visited = new boolean[R][C];
        que.offer(new int[]{0, 0});

        while (!que.isEmpty()) {
            int[] curr = que.poll();
            for (int[] d : dr) {
                int newR = curr[0] + d[0];
                int newC = curr[1] + d[1];
                if (newR >= 0 && newR < R && newC >= 0 && newC < C) {
                    if (!visited[newR][newC]) {
                        if (map[newR][newC] == 0) {
                            que.offer(new int[]{newR, newC});
                        }
                        else{
                            map[newR][newC] = 0;
                        }
                        visited[newR][newC] = true;
                    }
                }
            }
        }
    }

    private static int countCheese(int[][] map) {
        int count = 0;
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] == 1) {
                    count++;
                }
            }
        }
        return count;
    }
}