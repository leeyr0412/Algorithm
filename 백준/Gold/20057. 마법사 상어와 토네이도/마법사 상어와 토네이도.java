import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Tornado {
        int r, c, dir;
        int moveLength; // 지금 방향으로 이동할 수 있는 거
        int moveCount;  // 지금 방향으로 이동한 거리
        int dirChangeCount; // 방향 바꾼 횟수

        public Tornado(int startPos) {
            this.r = startPos;
            this.c = startPos;
            this.dir = 0;
            this.moveLength = 1;
            this.moveCount = 0;
            this.dirChangeCount = 0;
        }

        public void changeDir() {
            this.dir = (this.dir + 1) % 4;
            moveCount = 0;
            dirChangeCount++;
            if (dirChangeCount == 2) {  // 방향을 2번 바꿀때마다 이동 거리가 증가
                moveLength++;
                dirChangeCount = 0;
            }
        }

        public void move() {
            this.r += dirs[dir][0];
            this.c += dirs[dir][1];
            this.moveCount++;
        }

        public boolean alive() {
            return this.r != 0 || this.c != 0;
        }

        public boolean isTurn() {   // 이동할 수 있는 거리만큼 이동하면 회전
            return moveCount == moveLength;
        }
    }

    static int[][] dirs = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};
    static int[][][] SPREAD_PATTERN = { // 방향에 따른 모래 이동 좌표와 퍼센트 [r, c, 퍼센트]
            {{0, -2, 5}, {-1, -1, 10}, {1, -1, 10}, {-1, 0, 7}, {1, 0, 7}, {-2, 0, 2}, {2, 0, 2}, {-1, 1, 1}, {1, 1, 1}},
            {{2, 0, 5}, {1, -1, 10}, {1, 1, 10}, {0, -1, 7}, {0, 1, 7}, {0, -2, 2}, {0, 2, 2}, {-1, 1, 1}, {-1, -1, 1}},
            {{0, 2, 5}, {-1, 1, 10}, {1, 1, 10}, {-1, 0, 7}, {1, 0, 7}, {-2, 0, 2}, {2, 0, 2}, {-1, -1, 1}, {1, -1, 1}},
            {{-2, 0, 5}, {-1, -1, 10}, {-1, 1, 10}, {0, -1, 7}, {0, 1, 7}, {0, -2, 2}, {0, 2, 2}, {1, 1, 1}, {1, -1, 1}}
    };
    static int[][] map;
    static int N, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        Tornado tornado = new Tornado(N / 2);

        while (tornado.alive()) {
            tornado.move();
            sand(tornado.r, tornado.c, tornado.dir);
            if (tornado.isTurn()) {
                tornado.changeDir();
            }
        }
        System.out.println(answer);
    }

    // 모래 날리기
    private static void sand(int r, int c, int dir) {
        int total = map[r][c];
        int remain = total;
        map[r][c] = 0;

        for (int[] pattern : SPREAD_PATTERN[dir]) {
            int nextR = r + pattern[0];
            int nextC = c + pattern[1];
            int sand = (total * pattern[2]) / 100;
            if (isValid(nextR, nextC)) {
                map[nextR][nextC] += sand;
            } else {
                answer += sand;
            }
            remain -= sand;
        }

        int nextR = r + dirs[dir][0];
        int nextC = c + dirs[dir][1];
        if (isValid(nextR, nextC)) {
            map[nextR][nextC] += remain;
        } else {
            answer += remain;
        }
    }

    private static boolean isValid(int nextR, int nextC) {
        return !(nextR < 0 || nextC < 0 || nextR >= N || nextC >= N);
    }
}