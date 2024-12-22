import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    static class Cloud {
        int r, c;

        public Cloud(int r, int c) {
            this.r = r;
            this.c = c;
        }

        public void move(int d, int s, int N) {
            r = ((r + (dirs[d][0] * s)) % N + N) % N;
            c = ((c + (dirs[d][1] * s)) % N + N) % N;
        }
    }

    static int[][] dirs = {{0, -1}, {-1, -1}, {-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}};
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        Queue<Cloud> clouds = new ArrayDeque<>();
        clouds.add(new Cloud(N - 1, 0));
        clouds.add(new Cloud(N - 1, 1));
        clouds.add(new Cloud(N - 2, 0));
        clouds.add(new Cloud(N - 2, 1));
        for (int m = 0; m < M; m++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken()) - 1;
            int s = Integer.parseInt(st.nextToken());
            List<int[]> copyList = new ArrayList<>();
            while (!clouds.isEmpty()) {
                Cloud cloud = clouds.poll();
                // 구름 이동
                cloud.move(d, s, N);
                // 물 증가
                map[cloud.r][cloud.c]++;
                // 복사마법 쓸 칸
                copyList.add(new int[]{cloud.r, cloud.c});
            }
            for (int[] pos : copyList) {
                waterCopy(pos[0], pos[1], N);
            }
            // 구름 생성. 구름이 사라진 칸이 아니여야함.
            for (int[] pos : copyList) {
                map[pos[0]][pos[1]] *= -1;  // 구름 없어진 칸 표시를 위해 음수로 바꿈
            }
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (map[r][c] < 0) {
                        map[r][c] *= -1;
                    } else if (map[r][c] >= 2) {
                        clouds.add(new Cloud(r, c));
                        map[r][c] -= 2;
                    }
                }
            }
        }

        // 정답
        int answer = 0;
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                answer += map[r][c];
            }
        }
        System.out.println(answer);
    }

    private static void waterCopy(int r, int c, int N) {
        int d = 1;
        int count = 0;
        for (int i = 0; i < 4; i++) {
            int newR = r + dirs[d][0];
            int newC = c + dirs[d][1];
            if (newR < 0 || newC < 0 || newR >= N || newC >= N) {
                d += 2;
                continue;
            }
            if (map[newR][newC] > 0) {
                count++;
            }
            d += 2;
        }
        map[r][c] += count;
    }
}