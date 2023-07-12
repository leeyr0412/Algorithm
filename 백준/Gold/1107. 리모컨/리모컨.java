import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static char[] no;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringBuilder sb = new StringBuilder();
        String N = br.readLine();
        int M = Integer.parseInt(br.readLine());
        no = new char[M];
        if (M > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                no[i] = st.nextToken().charAt(0);
            }
        }
        int answer = 0;
        String begin = "";
        int goal = Integer.parseInt(N);
        int min = goal;
        int max = goal;
        int curr = 100;
        int plus;
        if (goal > 100) {
            plus = 1;
        } else {
            plus = -1;
        }
        while (true) {
            if (min >= 0 && numCheck(min)) {
                begin = min + "";
                break;
            }
            if (numCheck(max)) {
                begin = max + "";
                break;
            }
            if (curr == goal) {
                break;
            }
            min--;
            max++;
            curr += plus;
            answer++;
        }
        if (Math.abs(curr - goal) < begin.length()) {
            answer += Math.abs(curr - goal);
            begin = "";
        }
        System.out.println(answer + begin.length());
    }

    private static boolean numCheck(int num) {
        String curr = num + "";
        for (char c : no) {
            if (curr.indexOf(c) != -1) {
                return false;
            }
        }
        return true;
    }
}