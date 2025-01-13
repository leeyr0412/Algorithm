import java.io.*;
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
}

public class Main {
    static int[][] dirs = {
        {-1, 0}, {-1, 1}, {0, 1}, {1, 1},
        {1, 0}, {1, -1}, {0, -1}, {-1, -1}
    };

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Fireball>[][] map = new ArrayList[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                map[i][j] = new ArrayList<>();

        List<Fireball> fireballs = new ArrayList<>();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int m = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            fireballs.add(new Fireball(r, c, m, d, s));
        }

        while (K-- > 0) {
            // 이동
            for (Fireball f : fireballs) {
                int moveR = (f.r + dirs[f.d][0] * f.s % N + N) % N;
                int moveC = (f.c + dirs[f.d][1] * f.s % N + N) % N;
                f.r = moveR;
                f.c = moveC;
                map[moveR][moveC].add(f);
            }

            List<Fireball> next = new ArrayList<>();

            // 합치기
            for (int r = 0; r < N; r++) {
                for (int c = 0; c < N; c++) {
                    List<Fireball> cell = map[r][c];
                    int size = cell.size();
                    if (size == 0) continue;

                    if (size == 1) {
                        next.add(cell.get(0));
                    } else {
                        int sumM = 0, sumS = 0;
                        boolean allEven = true, allOdd = true;

                        for (Fireball f : cell) {
                            sumM += f.m;
                            sumS += f.s;
                            if (f.d % 2 == 0) allOdd = false;
                            else allEven = false;
                        }

                        int newM = sumM / 5;
                        if (newM == 0) {
                            cell.clear();
                            continue;
                        }

                        int newS = sumS / size;
                        int[] newDirs = (allEven || allOdd) ? new int[]{0, 2, 4, 6} : new int[]{1, 3, 5, 7};

                        for (int d : newDirs) {
                            next.add(new Fireball(r, c, newM, d, newS));
                        }
                    }
                    cell.clear(); // 꼭 clear 해줘야 메모리 절약됨
                }
            }

            fireballs = next; // 다음 턴으로 이동
        }

        int totalMass = 0;
        for (Fireball f : fireballs) {
            totalMass += f.m;
        }

        System.out.println(totalMass);
    }
}
