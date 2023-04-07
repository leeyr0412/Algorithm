import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 달리기 / 플래티넘3 / 걸린시간 / 4월 7일
 */
class Pos {
    private int r;
    private int c;
    private int time;

    public Pos(int r, int c, int time) {
        this.r = r;
        this.c = c;
        this.time = time;
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }

    public int getTime() {
        return time;
    }
}

public class Main {
    static int R, C, K;
    static char[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new char[R + 1][C + 1];

        for (int r = 1; r <= R; r++) {
            String line = br.readLine();
            for (int c = 1; c <= C; c++) {
                map[r][c] = line.charAt(c - 1);
            }
        }
        int[] start = new int[2];
        int[] end = new int[2];
        st = new StringTokenizer(br.readLine());
        start[0] = Integer.parseInt(st.nextToken());
        start[1] = Integer.parseInt(st.nextToken());
        end[0] = Integer.parseInt(st.nextToken());
        end[1] = Integer.parseInt(st.nextToken());

        System.out.println(bfs(start, end));
    }

    private static int bfs(int[] start, int[] end) {
        int[][] de = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        Deque<Pos> q = new ArrayDeque<>();
        int[][] visited = new int[R + 1][C + 1];
        for(int r = 1; r<=R;r++) Arrays.fill(visited[r],Integer.MAX_VALUE);
//        boolean[][] visited = new boolean[R + 1][C + 1];
        q.offer(new Pos(start[0], start[1], 0));
//        visited[start[0]][start[1]] = true;
        visited[start[0]][start[1]] = 0;
        while (!q.isEmpty()) {
            Pos curr = q.poll();
            int currR = curr.getR();
            int currC = curr.getC();
            int currTime = curr.getTime();
            if (currR == end[0] && currC == end[1]) {
                return currTime;
            }
            for (int[] d : de) {
                int nr, nc;
                Stack<Pos> stack = new Stack<>();
                for (int i = 1; i <= K; i++) {
                    nr = currR + d[0] * i;
                    nc = currC + d[1] * i;
                    if (nr > 0 && nc > 0 && nr <= R && nc <= C) {
                        if (map[nr][nc] == '#') break;
                        if(visited[nr][nc]<visited[currR][currC]+1) break;
//                        if(visited[nr][nc]) continue;
                        if (map[nr][nc] == '.'&&visited[nr][nc]==Integer.MAX_VALUE) {
                            visited[nr][nc] = visited[currR][currC]+1;
//                            visited[nr][nc] = true;
                            stack.add(new Pos(nr, nc, currTime + 1));
                        }
                    }else {
                        break;
                    }
                }
                while (!stack.isEmpty()){
                    q.offer(stack.pop());
                }
            }
        }
        return -1;
    }
}