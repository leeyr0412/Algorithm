import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Node {
    int r;
    int c;
    int boom;   // 부수기 가능 횟수
    int count;  // 이동 수

    public Node(int r, int c, int boom, int count) {
        this.r = r;
        this.c = c;
        this.boom = boom;
        this.count = count;
    }
}

public class Main {
    static int N, M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        // 지도 입력
        char[][] map = new char[N + 1][M + 1];
        for (int r = 1; r <= N; r++) {
            String line = br.readLine();
            for (int c = 1; c <= M; c++) {
                map[r][c] = line.charAt(c - 1);
            }
        }
        System.out.println(bfs(map));
    }

    private static int bfs(char[][] map) {
        // 벽 부수기 가능 횟수를 포함하여 3차원 배열로 방문 표시
        boolean[][][] visited = new boolean[N + 1][M + 1][2];
        int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        visited[1][1][1] = true;
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(new Node(1, 1, 1, 1));
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            if (now.r == N && now.c == M) {
                // 목표좌표 도달
                return now.count;
            }
            for (int[] dir : dirs) {
                int nextR = now.r + dir[0];
                int nextC = now.c + dir[1];
                if (nextR == 0 || nextC == 0 || nextR > N || nextC > M) {
                    continue;
                }
                if (map[nextR][nextC] == '1') {// 벽이면
                    if (now.boom == 1 && !visited[nextR][nextC][0]) {// 부수기 가능
                        visited[nextR][nextC][0] = true;
                        queue.add(new Node(nextR, nextC, 0, now.count + 1));
                    }
                } else {
                    if (!visited[nextR][nextC][now.boom]) {
                        visited[nextR][nextC][now.boom] = true;
                        queue.add(new Node(nextR, nextC, now.boom, now.count + 1));
                    }
                }
            }
        }
        return -1;
    }
}