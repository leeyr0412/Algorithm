import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 맥주 마시면서 걸어가기 / 골드5 / 걸린시간 / 4월 3일
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int n = Integer.parseInt(br.readLine());
            int[] home = new int[2];
            int[][] store = new int[n][2];
            int[] festival = new int[2];
            st = new StringTokenizer(br.readLine());
            home[0] = Integer.parseInt(st.nextToken());
            home[1] = Integer.parseInt(st.nextToken());
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                store[i][0] = Integer.parseInt(st.nextToken());
                store[i][1] = Integer.parseInt(st.nextToken());
            }
            st = new StringTokenizer(br.readLine());
            festival[0] = Integer.parseInt(st.nextToken());
            festival[1] = Integer.parseInt(st.nextToken());

            if (bfs(home, store, festival)) {
                System.out.println("happy");
            } else {
                System.out.println("sad");
            }
        }
    }

    private static boolean bfs(int[] home, int[][] store, int[] festival) {
        Deque<int[]> que = new ArrayDeque<>();
        que.offer(home);
        boolean[] visited = new boolean[store.length];
        while (!que.isEmpty()) {
            int[] curr = que.poll();
            if (getDis(curr, festival) <= 1000) {
                return true;
            }
            for (int i = 0; i < store.length; i++) {
                if (visited[i])
                    continue;
                int dis = getDis(curr, store[i]);
                if (dis <= 1000) {
                    visited[i] = true;
                    que.offer(store[i]);
                }
            }
        }
        return false;
    }

    private static int getDis(int[] from, int[] to) {
        return Math.abs(from[0] - to[0]) + Math.abs(from[1] - to[1]);
    }
}