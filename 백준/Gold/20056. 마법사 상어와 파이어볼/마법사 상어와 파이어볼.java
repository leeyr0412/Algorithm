import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Fireball {
    int r, c, m, d, s;

    public Fireball(int r, int c, int m, int d, int s) {
        this.r = r;
        this.c = c;
        this.m = m;
        this.d = d;
        this.s = s;
    }

    public Fireball(Fireball fireball) {
        this.r = fireball.r;
        this.c = fireball.c;
        this.m = fireball.m;
        this.d = fireball.d;
        this.s = fireball.s;
    }

    public void move(int N) {
        int[][] dirs = {{-1, 0}, {-1, 1}, {0, 1}, {1, 1}, {1, 0}, {1, -1}, {0, -1}, {-1, -1}};
        r = (r + dirs[d][0] * s) % N;
        if (r < 0) {
            r += N;
        }
        //if (dirs[d][0] < 0) {
        //    r = (r + dirs[d][0] * s) % N + N;
        //} else {
        //    r = (r + dirs[d][0] * s) % N;
        //}
        c = (c + dirs[d][1] * s) % N;
        if (c < 0) {
            c += N;
        }
        //if (dirs[d][1] < 0) {
        //    c = (c + dirs[d][1] * s) % N+ N;
        //} else {
        //    c = (c + dirs[d][1] * s) % N;
        //}
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Queue<Fireball> fireballs = new ArrayDeque<>();
        for (int i = 0; i < M; i++) {
            initFireball(br, fireballs);
        }
        List<Fireball>[][] map = new List[N][N];
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                map[r][c] = new ArrayList<>();
            }
        }

        // K번 명령
        for (int i = 0; i < K; i++) {
            // 이동
            while (!fireballs.isEmpty()) {
                Fireball fireball = fireballs.poll();
                fireball.move(N);
                map[fireball.r][fireball.c].add(new Fireball(fireball));
            }

            // 합치기
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    if (map[r][c].size() > 1) { // 파이어볼이 여러개.
                        int m = 0;  // 질량
                        int s = 0;  // 속도
                        int evenCount = 0;  // 방향 정하기 위함
                        int oddCount = 0;
                        for (Fireball fireball : map[r][c]) {
                            m += fireball.m;
                            s += fireball.s;
                            if (fireball.d % 2 == 0) {
                                evenCount++;
                            } else {
                                oddCount++;
                            }
                        }
                        m = m / 5;
                        s = s / (evenCount + oddCount);
                        if (m > 0) {   // 질량이 0이면 사라지므로 0이상일때만 나누기
                            splitFireball(fireballs, map, r, c, m, s, evenCount, oddCount);
                        }
                    } else if (map[r][c].size() == 1) { // 칸에 파이어볼 1개
                        fireballs.add(map[r][c].get(0));
                    }
                    map[r][c].clear();
                }
            }
        }

        //파이어볼 질량 합
        int answer = 0;
        for (Fireball fireball : fireballs) {
            answer += fireball.m;
        }
        System.out.println(answer);
    }

    private static void splitFireball(Queue<Fireball> fireballs, List<Fireball>[][] map, int r, int c, int m, int s, int evenCount, int oddCount) {
        if (evenCount * oddCount == 0) {// 합쳐지는 파이어볼 방향이 모두 홀수거나 짝수
            fireballs.add(new Fireball(r, c, m, 0, s));
            fireballs.add(new Fireball(r, c, m, 2, s));
            fireballs.add(new Fireball(r, c, m, 4, s));
            fireballs.add(new Fireball(r, c, m, 6, s));
        } else {
            fireballs.add(new Fireball(r, c, m, 1, s));
            fireballs.add(new Fireball(r, c, m, 3, s));
            fireballs.add(new Fireball(r, c, m, 5, s));
            fireballs.add(new Fireball(r, c, m, 7, s));
        }
    }

    private static void initFireball(BufferedReader br, Queue<Fireball> fireballs) throws IOException {
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        int r = Integer.parseInt(st.nextToken()) - 1;
        int c = Integer.parseInt(st.nextToken()) - 1;
        int m = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        fireballs.add(new Fireball(r, c, m, d, s));
    }
}