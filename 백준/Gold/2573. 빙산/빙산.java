import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;


/**
 * 빙산 / 골드4 / 걸린시간 / 5월 9일
 */
class Pos {
    private int R;
    private int C;

    public Pos(int r, int c) {
        R = r;
        C = c;
    }

    public int getR() {
        return R;
    }

    public int getC() {
        return C;
    }
}

public class Main {
    static int R;
    static int C;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];

        for (int r = 0; r < R; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < C; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int answer = 0;
        int islands = 0;
        while ((islands = findIslands()) < 2) {
            if (islands == 0) {
                answer = 0;
                break;
            }
            answer++;
            int[][] visited = new int[R][C];
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (map[r][c] > 0 && visited[r][c] == 0) {
                        bfs(r, c, visited);
                    }
                }
            }
        }
        System.out.println(answer);
    }

    /**
     * 섬 수 반환
     *
     * @return
     */
    private static int findIslands() {
        int count = 0;
        int[][] countMap = new int[R][C];
        for (int r = 0; r < R; r++) {
            for (int c = 0; c < C; c++) {
                if (map[r][c] > 0 && countMap[r][c] == 0) {
                    count++;
                    findIslandsBfs(r, c, countMap, count);
                }
            }
        }
        return count;
    }

    /**
     * 섬 수 구하는 bfs
     *
     * @param r
     * @param c
     * @param countMap
     * @param count
     */
    private static void findIslandsBfs(int r, int c, int[][] countMap, int count) {
        int[][] dr = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        countMap[r][c] = count;
        Deque<Pos> que = new ArrayDeque<>();
        que.offer(new Pos(r, c));
        while (!que.isEmpty()) {
            Pos now = que.poll();
            int nowR = now.getR();
            int nowC = now.getC();
            for (int[] d : dr) {
                int newR = nowR + d[0];
                int newC = nowC + d[1];
                if (newR >= 0 && newR < R && newC >= 0 && newC < C) {
                    if (countMap[newR][newC] > 0)
                        continue;
                    if (map[newR][newC] > 0) {
                        countMap[newR][newC] = count;
                        que.offer(new Pos(newR, newC));
                    }
                }
            }
        }
    }

    private static void bfs(int r, int c, int[][] delete) {
        int[][] dr = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        boolean[][] visited = new boolean[R][C];
        visited[r][c] = true;
        Deque<Pos> que = new ArrayDeque<>();
        que.offer(new Pos(r, c));
        while (!que.isEmpty()) {
            Pos now = que.poll();
            int nowR = now.getR();
            int nowC = now.getC();
            delete[nowR][nowC] = count(nowR, nowC);
            if(delete[nowR][nowC]==0)
                delete[nowR][nowC]=-1;
            for (int[] d : dr) {
                int newR = nowR + d[0];
                int newC = nowC + d[1];
                if (newR >= 0 && newR < R && newC >= 0 && newC < C) {
                    if (visited[newR][newC])
                        continue;
                    if (map[newR][newC] > 0) {
                        visited[newR][newC] = true;
                        que.offer(new Pos(newR, newC));
                    }
                }
            }
        }
        for(int row = 0; row < R; row++){
            for(int col = 0; col < C; col++){
                if(delete[row][col]>0){
                    map[row][col] = map[row][col]>=delete[row][col] ? map[row][col]- delete[row][col]:0;
                }
            }
        }
    }

    private static int count(int r, int c) {
        int count = 0;
        if (r > 0) {
            if (map[r - 1][c] == 0) {
                count++;
            }
        }
        if (r != R - 1) {
            if (map[r + 1][c] == 0) {
                count++;
            }
        }
        if (c > 0) {
            if (map[r][c - 1] == 0) {
                count++;
            }
        }
        if (c != C - 1) {
            if (map[r][c + 1] == 0) {
                count++;
            }
        }
        return count;
    }
}