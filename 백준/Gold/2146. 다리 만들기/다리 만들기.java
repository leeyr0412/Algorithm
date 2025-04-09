import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node implements Comparable<Node> {
    int r, c;
    int count;

    public Node(int r, int c) {
        this.r = r;
        this.c = c;
    }

    public Node(int r, int c, int count) {
        this.r = r;
        this.c = c;
        this.count = count;
    }

    @Override
    public int compareTo(Node o) {
        return this.count - o.count;
    }
}

public class Main {
    static int N;
    static int[][] map;
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    static int answer = 10000;

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

        int islandCount = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c] > 0) {
                    islandCount++;
                    islandCheck(r, c, (-1) * islandCount);
                }
            }
        }

        boolean[] islandStartCheck = new boolean[islandCount + 1];
        islandStartCheck[0] = true;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (!islandStartCheck[-1 * map[r][c]]) {
                    islandStartCheck[-1 * map[r][c]] = true;
                    bfs(r, c, map[r][c]);
                }
            }
        }
        System.out.println(answer);
    }

    private static void bfs(int r, int c, int startLand) {
        boolean[][] visited = new boolean[N][N];
        PriorityQueue<Node> que = new PriorityQueue<>();
        que.add(new Node(r, c));
        visited[r][c] = true;
        while (!que.isEmpty()) {
            Node now = que.poll();
            if (now.count > answer) {
                return;
            }
            for (int[] dir : dirs) {
                int nextR = now.r + dir[0];
                int nextC = now.c + dir[1];
                if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= N || visited[nextR][nextC]) continue;
                if (map[nextR][nextC] == startLand) {   // 같은 섬
                    visited[nextR][nextC] = true;
                    que.add(new Node(nextR, nextC, 0));
                } else if (map[nextR][nextC] < 0) {   // 다른 섬
                    answer = Math.min(answer, now.count);
                    return;
                } else {//바다
                    visited[nextR][nextC] = true;
                    que.add(new Node(nextR, nextC, now.count + 1));
                }
            }
        }
    }

    private static void islandCheck(int r, int c, int islandNum) {
        Deque<Node> que = new ArrayDeque<>();
        que.add(new Node(r, c));
        map[r][c] = islandNum;
        while (!que.isEmpty()) {
            Node now = que.poll();
            for (int[] dir : dirs) {
                int nextR = now.r + dir[0];
                int nextC = now.c + dir[1];
                if (nextR < 0 || nextC < 0 || nextR >= N || nextC >= N) continue;
                if (map[nextR][nextC] > 0) {
                    map[nextR][nextC] = islandNum;
                    que.add(new Node(nextR, nextC));
                }
            }
        }
    }
}