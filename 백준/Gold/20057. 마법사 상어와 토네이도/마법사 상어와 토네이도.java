import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 마법사상어와토네이도 / 골드3 / 걸린시간 / 3월 22일
 */
public class Main {
    static int answer = 0;
    static int N = 0;
    static int[][] dr = {{0, -1}, {1, 0}, {0, 1}, {-1, 0}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        // 맵 입력받음
        N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];
        for (int r = 0; r < N; r++) {
            st = new StringTokenizer(br.readLine());
            for (int c = 0; c < N; c++) {
                map[r][c] = Integer.parseInt(st.nextToken());
            }
        }

        //현재 좌표
        int currR = N / 2;
        int currC = currR;

        int len = 1;

        int d = 0;

        //토네이도 이동
        while (currR >= 0 && currC >= 0) {
            for (int i = 0; i < len; i++) {
                currR = currR + dr[d][0];
                currC = currC + dr[d][1];

                if(!scopeCheck(currR,currC)){
                    break;
                }
                //모래있음
                if (map[currR][currC] > 0) {
                    //모래 퍼지기
                    spreadingSand(currR, currC, d, map);
                }
            }


            if (d == 1) {
                len++;
            } else if (d == 3) {
                len++;
            }
            d = (d + 1) % 4;
        }
        System.out.println(answer);
    }

    private static void spreadingSand(int currR, int currC, int d, int[][] map) {
        int newR, newC;
        int a = map[currR][currC];
        map[currR][currC] = 0;
        int temp;   //임시 모래양
        int tempA= a;

        //5%
        newR = currR + dr[d][0] * 2;
        newC = currC + dr[d][1] * 2;
        temp = (int) (tempA * 0.05);
        if (scopeCheck(newR, newC)) {
            map[newR][newC] += temp;
        } else {
            answer += temp;
        }
        a -= temp;

        //10%
        newR = currR + dr[d][0] + dr[(d + 1) % 4][0];
        newC = currC + dr[d][1] + dr[(d + 1) % 4][1];
        temp = (int) (tempA * 0.1);
        if (scopeCheck(newR, newC)) {
            map[newR][newC] += temp;
        } else {
            answer += temp;
        }
        a -= temp;
        newR = currR + dr[d][0] + dr[(d != 0) ? d - 1 : 3][0];
        newC = currC + dr[d][1] + dr[(d != 0) ? d - 1 : 3][1];
        if (scopeCheck(newR, newC)) {
            map[newR][newC] += temp;
        } else {
            answer += temp;
        }
        a -= temp;

        //7%
        newR = currR + dr[(d + 1) % 4][0];
        newC = currC + dr[(d + 1) % 4][1];
        temp = (int) (tempA * 0.07);
        if (scopeCheck(newR, newC)) {
            map[newR][newC] += temp;
        } else {
            answer += temp;
        }
        a -= temp;
        newR = currR + dr[(d != 0) ? d - 1 : 3][0];
        newC = currC + dr[(d != 0) ? d - 1 : 3][1];
        if (scopeCheck(newR, newC)) {
            map[newR][newC] += temp;
        } else {
            answer += temp;
        }
        a -= temp;


        //2%
        newR = currR + dr[(d + 1) % 4][0]*2;
        newC = currC + dr[(d + 1) % 4][1]*2;
        temp = (int) (tempA * 0.02);
        if (scopeCheck(newR, newC)) {
            map[newR][newC] += temp;
        } else {
            answer += temp;
        }
        a -= temp;
        newR = currR + dr[(d != 0) ? d - 1 : 3][0]*2;
        newC = currC + dr[(d != 0) ? d - 1 : 3][1]*2;
        if (scopeCheck(newR, newC)) {
            map[newR][newC] += temp;
        } else {
            answer += temp;
        }
        a -= temp;


        newR = currR + dr[(d+2)%4][0] + dr[(d + 1) % 4][0];
        newC = currC + dr[(d+2)%4][1] + dr[(d + 1) % 4][1];
        temp = (int) (tempA * 0.01);
        if (scopeCheck(newR, newC)) {
            map[newR][newC] += temp;
        } else {
            answer += temp;
        }
        a -= temp;
        newR = currR + dr[(d+2)%4][0] + dr[(d != 0) ? d - 1 : 3][0];
        newC = currC + dr[(d+2)%4][1] + dr[(d != 0) ? d - 1 : 3][1];
        if (scopeCheck(newR, newC)) {
            map[newR][newC] += temp;
        } else {
            answer += temp;
        }
        a -= temp;

        //a
        newR = currR + dr[d][0] ;
        newC = currC + dr[d][1] ;
        if (scopeCheck(newR, newC)) {
            map[newR][newC] += a;
        } else {
            answer += a;
        }
    }

    private static boolean scopeCheck(int newR, int newC) {
        if (newR < N && newC < N && newR >= 0 && newC >= 0) {
            return true;
        }
        return false;
    }
}