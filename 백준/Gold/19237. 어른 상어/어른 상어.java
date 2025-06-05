import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static class Shark {
        int r, c, num, nowDir;
        boolean isAlive;
        int[][] dirPriority;

        public Shark(int r, int c, int num) {
            this.r = r;
            this.c = c;
            this.num = num;
            this.isAlive = true;
            this.dirPriority = new int[4][4];
        }

        public void setNowDir(int nowDir) {
            this.nowDir = nowDir;
        }

        public void setDirPriority(int dir, int[] priority) {
            this.dirPriority[dir] = priority;
        }
    }

    static int N, M, k;
    static int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    static int SHARK_NUM = 0, SMELL_REMAIN = 1, SMELL_SHARK = 2;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        int[][][] map = new int[N][N][3];   // [r][c][상어 번호, 냄새, 냄새 남긴 상어]
        Shark[] sharks = new Shark[M + 1];
        int sharkCount = M;

        // 지도 입력
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c][SHARK_NUM] = Integer.parseInt(st.nextToken());
                if (map[r][c][SHARK_NUM] != 0) {    // 상어일 경우
                    map[r][c][SMELL_REMAIN] = k;
                    map[r][c][SMELL_SHARK] = map[r][c][SHARK_NUM];
                    sharks[map[r][c][SHARK_NUM]] = new Shark(r, c, map[r][c][SHARK_NUM]);
                }
            }
        }

        // 상어 방향
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= M; i++) {
            sharks[i].setNowDir(Integer.parseInt(st.nextToken()) - 1);
        }

        // 상어 이동 우선순위 입력
        for (int shark = 1; shark <= M; shark++) {
            for (int dir = 0; dir < 4; dir++) {
                st = new StringTokenizer(br.readLine());
                int[] priority = new int[4];
                for (int i = 0; i < 4; i++) {
                    priority[i] = Integer.parseInt(st.nextToken()) - 1;
                }
                sharks[shark].setDirPriority(dir, priority);
            }
        }

        int answer = 0;
        while (sharkCount != 1) {
            answer++;
            if (answer > 1000) {
                answer = -1;
                break;
            }

            // 상어 움직임
            for (Shark shark : sharks) {    // 작은 번호의 상어만 남기기 위해 1번부터 이동
                if (shark == null) {
                    continue;
                }
                if (!shark.isAlive) {
                    continue;
                }
                int[] dirPriority = shark.dirPriority[shark.nowDir];
                boolean moveCheck = false;
                for (int dir : dirPriority) {   // 인접한 칸에 빈 칸 있음
                    int nextR = shark.r + dirs[dir][0];
                    int nextC = shark.c + dirs[dir][1];
                    if (isOutOfBounds(nextR, nextC)) {
                        continue;
                    }
                    if (map[nextR][nextC][SMELL_REMAIN] == 0) {    // 냄새가 없음
                        if (map[nextR][nextC][SHARK_NUM] != 0) { // 같은 턴에 앞번호 상어가 이동한 칸임
                            shark.isAlive = false;
                            sharkCount--;
                            map[shark.r][shark.c][SHARK_NUM] = 0;
                        } else {
                            sharkMove(map, shark, dir, nextR, nextC);
                        }
                        moveCheck = true;
                        break;
                    }
                }
                if (moveCheck) {
                    continue;
                }
                // 인접한 칸에 빈칸 없었음 -> 내 냄새칸으로
                for (int dir : dirPriority) {
                    int nextR = shark.r + dirs[dir][0];
                    int nextC = shark.c + dirs[dir][1];
                    if (isOutOfBounds(nextR, nextC)) {
                        continue;
                    }
                    if (map[nextR][nextC][SMELL_SHARK] == shark.num) {    // 본인 냄새가 있는 칸
                        sharkMove(map, shark, dir, nextR, nextC);
                        break;
                    }

                }
            }
            // 냄새 감소
            fadeSmell(map);
        }
        System.out.println(answer);
    }

    private static boolean isOutOfBounds(int nextR, int nextC) {
        return nextR < 0 || nextC < 0 || nextR >= N || nextC >= N;
    }

    private static void fadeSmell(int[][][] map) {
        for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                if (map[r][c][SHARK_NUM] != 0) {    // 지금 상어가 있는 칸임
                    map[r][c][SMELL_REMAIN] = k;
                } else if (map[r][c][SMELL_REMAIN] > 0) {
                    map[r][c][SMELL_REMAIN]--;
                    if (map[r][c][SMELL_REMAIN] == 0) {
                        map[r][c][SMELL_SHARK] = 0;
                    }
                }
            }
        }
    }

    private static void sharkMove(int[][][] map, Shark shark, int dir, int nextR, int nextC) {
        // 기존 위치 비우기
        map[shark.r][shark.c][SHARK_NUM] = 0;
        shark.r = nextR;
        shark.c = nextC;
        //새 위치에 상어번호 넣기
        map[nextR][nextC][SHARK_NUM] = shark.num;
        map[nextR][nextC][SMELL_SHARK] = shark.num;
        shark.nowDir = dir;
    }
}