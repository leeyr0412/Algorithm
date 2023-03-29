import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * 말이되고픈원숭이 / 골드3 / 걸린시간 / 3월 29일
 */

class Point {
    private int r;
    private int c;
    private int k;
    private int count;

    public Point(int r, int c, int k, int count) {
        this.r = r;
        this.c = c;
        this.k = k;
        this.count = count;
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }

    public int getK() {
        return k;
    }

    public int getCount() {
        return count;
    }
}

public class Main {
    static int count = -1;
    static int[][] map;
    static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        int k = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        C = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        map = new int[R + 1][C + 1];
        for (int i = 1; i <= R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= C; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs(k);
        System.out.println(count);
    }

    private static void bfs(int k) {
        int[][] dr1 = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};       //인접한 칸 이동
        int[][] dr2 = {{-2, -1}, {-2, 1}, {-1, -2}, {-1, 2},    //말처럼 이동
                {1, -2}, {1, 2}, {2, -1}, {2, 1}};
        int[][] visited = new int[R + 1][C + 1];
        for (int i = 1; i <= R; i++) {
            Arrays.fill(visited[i], -1);
        }
        visited[1][1] = k;
        Deque<Point> que = new ArrayDeque<>();
        que.add(new Point(1, 1, k, 0));
        while (!que.isEmpty()) {
            Point curr = que.poll();
            if (curr.getC() == C && curr.getR() == R) {
                count = curr.getCount();
                return;
            }
            for (int[] d : dr1) {
                int newR = curr.getR() + d[0];
                int newC = curr.getC() + d[1];
                if (check(newR, newC)) {
                    if (visited[newR][newC] < curr.getK()) {
                        visited[newR][newC] = curr.getK();
                        que.add(new Point(newR, newC, curr.getK(), curr.getCount() + 1));
                    }
                }
            }

            if (curr.getK() > 0) {
                for (int[] d : dr2) {
                    int newR = curr.getR() + d[0];
                    int newC = curr.getC() + d[1];
                    if (check(newR, newC)) {
                        if (visited[newR][newC] < curr.getK() -1) {
                            visited[newR][newC] = curr.getK()  -1;
                            que.add(new Point(newR, newC, curr.getK() - 1, curr.getCount() + 1));
                        }
                    }
                }
            }
        }
    }

    private static boolean check(int newR, int newC) {
        //범위 탐색
        if (newR > 0 && newR <= R && newC > 0 && newC <= C) {
            return map[newR][newC] == 0;
        }
        return false;
    }
}

/*
1
4 4
0 1 1 1
0 0 1 1
1 0 1 1
1 1 1 0
 */