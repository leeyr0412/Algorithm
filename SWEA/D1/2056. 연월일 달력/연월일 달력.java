import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int[] monthList = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

        int T = Integer.parseInt(br.readLine());
        for (int tc = 1; tc <= T; tc++) {
            String answer = "";

            String str = br.readLine();
            if (str.length() != 8) {
                answer = "-1";
            } else {
                String day = str.substring(6);
                String month = str.substring(4, 6);
                String year = str.substring(0, 4);

                int dayInt = Integer.parseInt(day);
                int monthInt = Integer.parseInt(month);
                if ((monthInt > 12) || (monthInt < 1) ||
                        (monthList[monthInt - 1] < dayInt) || (dayInt < 1)) {
                    answer = "-1";
                } else {
                    answer = year + "/" + month + "/" + day;
                }
            }
            sb.append("#").append(tc).append(" ").append(answer).append("\n");
        }
        System.out.println(sb);
    }
}