import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        int goal = (1 << 10) - 1;
        for (int tc = 1; tc <= T; tc++) {
            int answer = 0;
            int visited = 0;
            int now = Integer.parseInt(br.readLine());
            while (visited != goal) {
                answer++;
                char[] temp = String.valueOf(now * answer).toCharArray();
                for (char c : temp) {
                    int num = c - '0';
                    visited = visited | 1 << num;
                }
            }
            sb.append("#").append(tc).append(" ").append(answer * now).append("\n");
        }
        System.out.println(sb);
    }
}