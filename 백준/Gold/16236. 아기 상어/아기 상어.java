import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Point implements Comparable<Point> {
    private int r, c, count;

    public Point(int r, int c, int count) {
        this.r = r;
        this.c = c;
        this.count = count;
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }

    public int getCount() {
        return count;
    }

    @Override
    public int compareTo(Point o) {
        if (this.count == o.count) {
            if (this.r == o.r) {
                return this.c - o.c;
            }
            return this.r - o.r;
        }
        return this.count - o.count;
    }
}

public class Main {
    static int nowSize, time, eatCount;
    static int[][] map;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        int[] now = {0, 0};
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
                if (map[r][c] == 9) {
                    now[0] = r;
                    now[1] = c;
                    map[r][c] = 0;
                }
            }
        }

        nowSize = 2;
        time = 0;
        eatCount = 0;
        while ((now = getNextPoint(now)) != null) {     // 이동 가능한 좌표가 있으면 반복
            eatCount++; //물고기 먹기
            map[now[0]][now[1]] = 0;
            if (eatCount == nowSize) {  // 사이즈 만큼 먹었을 경우 크기 자람
                nowSize++;
                eatCount = 0;
            }
        }
        System.out.println(time);
    }

    /**
     * 다음 이동할 좌표(먹어야하는 물고기가 있는 위치)를 구하는 함수
     *
     * @param now 현재 좌표
     * @return 다음 이동 좌표
     */
    private static int[] getNextPoint(int[] now) {
        boolean[][] visited = new boolean[N][N];
        // 거리순, 거리가 같다면 위쪽, 왼쪽 우선순위를 위해 우선순위 큐 사용
        PriorityQueue<Point> que = new PriorityQueue<>();
        que.add(new Point(now[0], now[1], 0));
        visited[now[0]][now[1]] = true;
        int[][] dirs = {{-1, 0}, {0, -1}, {0, 1}, {1, 0}};
        while (!que.isEmpty()) {
            Point nowPoint = que.poll();

            // 잡아 먹을 수 있는 물고기가 있는 좌표면 좌표 반환
            if (map[nowPoint.getR()][nowPoint.getC()] > 0
                    && map[nowPoint.getR()][nowPoint.getC()] < nowSize) {
                time += nowPoint.getCount();    // 물고기까지 거리(이동시간) 더하기
                return new int[]{nowPoint.getR(), nowPoint.getC()};
            }
            for (int[] dir : dirs) {
                int nextR = nowPoint.getR() + dir[0];
                int nextC = nowPoint.getC() + dir[1];
                if (canMove(nextR, nextC) && !visited[nextR][nextC]) {    // 이동 가능하면 큐에 삽입
                    que.add(new Point(nextR, nextC, nowPoint.getCount() + 1));
                    visited[nextR][nextC] = true;
                }
            }
        }
        return null;
    }

    /**
     * 이동 가능한지 확인(범위 내 빈 곳이거나, 작거나 같은 사이즈의 물고기가 있는 곳)
     *
     * @param nextR
     * @param nextC
     * @return
     */
    private static boolean canMove(int nextR, int nextC) {
        if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= N
                || map[nextR][nextC] > nowSize) {
            return false;
        }
        return true;
    }

}