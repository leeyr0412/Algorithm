import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Tomato {
    private int r;
    private int c;
    private int count;

    public Tomato(int r, int c, int count) {
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
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        // 토마토 상태 및 방문 배열
        int[][] box = new int[N][M];
        // 익지 않은 토마토 수
        int remain = 0;

        Deque<Tomato> que = new ArrayDeque<>();
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < M; c++) {
                box[r][c] = Integer.parseInt(st.nextToken());
                if (box[r][c] == 1) {   // 익은 토마토는 바로 큐에 삽입
                    que.add(new Tomato(r, c, 0));
                } else if (box[r][c] == 0) { // 익지 않은 토마토 수를 카운트
                    remain++;
                }
            }
        }

        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int answer = 0;
        // BFS 탐색
        while (!que.isEmpty()) {
            Tomato now = que.pop();
            answer = Math.max(answer, now.getCount());
            for (int[] dir : dirs) {
                int nextR = now.getR() + dir[0];
                int nextC = now.getC() + dir[1];
                if (nextR < 0 || nextR >= N || nextC < 0 || nextC >= M) {
                    continue;
                }
                if (box[nextR][nextC] == 0) {
                    box[nextR][nextC] = 1;
                    remain--; // 익지 않은 토마토 수 감소
                    que.add(new Tomato(nextR, nextC, now.getCount() + 1));
                }
            }
        }
        
        // 남은 토마토가 없을 때 정답 출력
        if (remain == 0) {
            System.out.println(answer);
        } else {
            System.out.println(-1);
        }
    }
}