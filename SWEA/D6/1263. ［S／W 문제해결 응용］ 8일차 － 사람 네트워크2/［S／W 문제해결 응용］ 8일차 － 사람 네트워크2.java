import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 사람네트워크2 / D6 / 걸린시간 / 4월 3일
 */
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        int INF = (int) 1e9;
        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            int answer = INF;
            st = new StringTokenizer(br.readLine());
            int people = Integer.parseInt(st.nextToken());
            int[][] gragh = new int[people + 1][people + 1];
            for (int r = 1; r <= people; r++) {
                Arrays.fill(gragh[r], INF);
            }
            for (int r = 1; r <= people; r++) {
                for (int c = 1; c <= people; c++) {
                    int i = Integer.parseInt(st.nextToken());
                    if (r == c) {
                        gragh[r][c] = 0;
                        continue;
                    }
                    if (i == 0) {
                        continue;
                    }
                    gragh[r][c] = i;
                }
            }

            for (int use = 1; use <= people; use++) {
                for (int from = 1; from <= people; from++) {
                    for (int to = 1; to <= people; to++) {
                        if (from == to)
                            continue;
                        gragh[from][to] = Math.min(gragh[from][to], gragh[from][use] + gragh[use][to]);
                    }
                }
            }

            for (int r = 1; r <= people; r++) {
                int temp = 0;
                for (int c = 1; c <= people; c++) {
                    temp+= gragh[r][c];
                }
                answer=Math.min(temp, answer);
            }


            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}