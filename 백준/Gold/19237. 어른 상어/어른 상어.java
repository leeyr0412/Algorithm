import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 어른상어 / 골드2 / 2시간 반 / 4월 4일
 */
class Smell {
    int shark;
    int smell;

    public Smell(int shark, int smell) {
        this.shark = shark;
        this.smell = smell;
    }

    public int getShark() {
        return shark;
    }

    public int getSmell() {
        return smell;
    }
}

public class Main {
    static int N;
    static int smell;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        int sharkNum = Integer.parseInt(st.nextToken());
         smell = Integer.parseInt(st.nextToken());

        Smell[][] map = new Smell[N + 1][N + 1];
        int[][] sharks = new int[sharkNum + 1][3];       //상어 방향, 위치 저장 배열
        for (int r = 1; r <= N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= N; c++) {
                int a = Integer.parseInt(st.nextToken());
                if (a > 0) {
                    sharks[a][1] = r;
                    sharks[a][2] = c;
                    map[r][c] = new Smell(a, smell);
                }
            }
        }

        boolean[] die = new boolean[sharkNum + 1];   //수조에 상어가 살아있는지 확인 배열
        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= sharkNum; i++) {
            sharks[i][0] = Integer.parseInt(st.nextToken()) - 1;
        }

        int[][][] sharkDr = new int[sharkNum + 1][4][4];    //상어 번호, 바라보는 방향, 이동방향우선순위
        for (int num = 1; num <= sharkNum; num++) {
            for (int d = 0; d < 4; d++) {
                st = new StringTokenizer(br.readLine());
                for (int dr = 0; dr < 4; dr++) {
                    sharkDr[num][d][dr] = Integer.parseInt(st.nextToken()) - 1;
                }
            }
        }
        int time = 0;
        while (time <= 1000) {
            if (countShark(die) == 1) { //상어가 1마리 남으면 종료
                break;
            }
            time++;

            //상어 이동
            moveShark(map, die, sharks, sharkDr, sharkNum);

            //냄새 줄이기
            smellMinus(map);
        }
        if (time > 1000)
            time = -1;
        System.out.println(time);
    }

    /**
     * 상어 이동
     * @param map
     * @param die      수조에 있는 상어 표시
     * @param sharks   방향, 위치
     * @param sharkDr  상어 번호, 바라보는 방향, 이동방향우선순위
     * @param sharkNum 전체 상어 수
     */
    private static void moveShark(Smell[][] map, boolean[] die, int[][] sharks, int[][][] sharkDr, int sharkNum) {
        int[][] dr = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        int[][] newMap = new int[N + 1][N + 1]; //상어 번호 넣기
        for (int num = 1; num <= sharkNum; num++) {
            if (die[num])
                continue;
            boolean find = false;
            for (int d : sharkDr[num][sharks[num][0]]) {
                int newR = sharks[num][1] + dr[d][0];
                int newC = sharks[num][2] + dr[d][1];
                if (newR > 0 && newR <= N && newC > 0 && newC <= N) {//범위 안
                    if (newMap[newR][newC] > 0) {
                        find = true;
                        die[num] = true;
                        break;
                    }
                    if (map[newR][newC] == null) {
                        map[newR][newC] = new Smell(num, smell+1);
                        newMap[newR][newC] = num;
                        find = true;
                        sharks[num][0] = d;
                        sharks[num][1] = newR;
                        sharks[num][2] = newC;
                        break;
                    }
                }
            }
            if(!find){
                for (int d : sharkDr[num][sharks[num][0]]) {
                    int newR = sharks[num][1] + dr[d][0];
                    int newC = sharks[num][2] + dr[d][1];
                    if (newR > 0 && newR <= N && newC > 0 && newC <= N) {
                        if (map[newR][newC].getShark() == num) {
                            map[newR][newC] = new Smell(num, smell+1);
                            sharks[num][0] = d;
                            sharks[num][1] = newR;
                            sharks[num][2] = newC;
                            break;
                        }
                    }
                }
            }
        }
    }

    private static void smellMinus(Smell[][] map) {
        for (int r = 1; r <= N; r++) {
            for (int c = 1; c <= N; c++) {
                if (map[r][c] != null) {
                    int k = map[r][c].getSmell() - 1;
                    if (k == 0) {
                        map[r][c] = null;
                    } else {
                        map[r][c] = new Smell(map[r][c].getShark(), k);
                    }
                }
            }
        }
    }

    private static int countShark(boolean[] die) {
        int cnt = 0;
        for (int i = 1; i < die.length; i++) {
            if (!die[i])
                cnt++;
        }
        return cnt;
    }
}