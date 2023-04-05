import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 활주로건설 / 모의 / 1시간 / 4월 5일
 */
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int answer = 0;
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int X = Integer.parseInt(st.nextToken());
            int[][] map = new int[N][N];
            for (int r = 0; r < N; r++) {
                st = new StringTokenizer(br.readLine());
                for (int c = 0; c < N; c++) {
                    map[r][c] = Integer.parseInt(st.nextToken());
                }
            }

            //행
            int[] line = new int[N];
            for (int r = 0; r < N; r++) {
                int pre = map[r][0];
                for (int c = 1; c < N; c++) {
                    line[c] = map[r][c] - pre;
                    pre = map[r][c];
                    if (Math.abs(line[c]) > 1) {
                        break;
                    }
                }
                if (can(line, N, X)) {
                    answer++;
                }
            }

            //열
            for (int c = 0; c < N; c++) {
                int pre = map[0][c];
                for (int r = 1; r < N; r++) {
                    line[r] = map[r][c] - pre;
                    pre = map[r][c];
                    if (Math.abs(line[r]) > 1) {
                        break;
                    }
                }
                if (can(line, N, X)) {
                    answer++;
                }
            }
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }

    private static boolean can(int[] line, int n, int x) {
        boolean can = true;
        boolean[] use = new boolean[line.length];
        for (int i = 0; i < n; i++) {
            if (line[i] != 0) {
                if (Math.abs(line[i]) > 1) {
                    return false;
                }
                if (line[i] == 1) {
                    if (i - x >= 0) {
                        for (int c = i - x; c <= i - 1; c++) {
                            if (line[c] != 0 || use[c]) {
                                return false;
                            }
                            use[c] = true;
                        }
                        line[i] = 0;
                    } else {
                        return false;
                    }
                } else {
                    if (i + x > n) {
                        return false;
                    } else {
                        for (int c = i + 1; c < i + x; c++) {

                            if (line[c] != 0 || use[c])
                                return false;
                            use[c] = true;
                        }
                        line[i] = 0;
                    }
                }
            }
        }
        return true;
    }
}