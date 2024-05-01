import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;// = new StringTokenizer(br.readLine());
        int T = 10;
        for (int tc = 1; tc <= T; tc++) {
            int answer = 0;
            int N = Integer.parseInt(br.readLine());
            int[] buildings = new int[N];
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                buildings[i] = Integer.parseInt(st.nextToken());
            }
            for (int i = 2; i < N - 2; i++) {
                int max = 0;
                max = Math.max(Math.max(buildings[i - 2], buildings[i - 1]), Math.max(buildings[i + 2], buildings[i + 1]));
                if (max < buildings[i]) {
                    answer += (buildings[i] - max);
                }
            }
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}