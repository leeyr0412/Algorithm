import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 수영장만들기 / 골드1 / 시간 / 4월 27일
 */
class Pos {
    private int r;
    private int c;

    public Pos(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }
}

public class Main {
    private static int[][] map;
    private static int R, C;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        map = new int[R][C];
        PriorityQueue<Integer> h = new PriorityQueue<>();

        for (int r = 0; r < R; r++) {
            String line = br.readLine();
            for (int c = 0; c < C; c++) {
                map[r][c] = line.charAt(c) - '0';
                if (!h.contains(map[r][c])) {
                    h.add(map[r][c]);
                }
            }
        }
        int answer = 0;

        while (!h.isEmpty()) {
            int currH = h.poll();
            if (h.isEmpty()) {
                break;
            }
            int nextH = h.peek();
            for (int r = 0; r < R; r++) {
                for (int c = 0; c < C; c++) {
                    if (map[r][c] == currH) {
                        answer += bfs(r, c, nextH, currH);
                    }
                }
            }
        }
        System.out.println(answer);
    }

    private static int bfs(int r, int c, int nextH, int currH) {
        int[][] dr = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int water = 0;
        Deque<Pos> queue = new ArrayDeque<>();
        queue.offer(new Pos(r, c));
        map[r][c] = nextH;
        boolean check = false;
        while (!queue.isEmpty()) {
            Pos curr = queue.poll();
            water += nextH - currH;
            if (curr.getR() == 0 || curr.getR() == R - 1 || curr.getC() == 0 || curr.getC() == C - 1) {
                check = true;
            }
            for (int[] d : dr) {
                int nr = curr.getR() + d[0];
                int nc = curr.getC() + d[1];
                if (nr >= 0 && nr < R && nc < C && nc >= 0) {
                    if(map[nr][nc]==currH){
                        map[nr][nc] = nextH;
                        queue.offer(new Pos(nr,nc));
                    }
                }
            }
        }
        if(check){
            return 0;
        }
        return water;
    }
}