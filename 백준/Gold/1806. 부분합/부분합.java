import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] num = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        int answer = N + 1;
        int sum = num[0];
        int start = 0;
        int end = 0;
        while (start < N) {
            int length = 1;
            if (sum < M && end < N - 1) {
                end++;
                sum += num[end];
            } else if (sum >= M) {
                length += end - start;
                answer = Math.min(answer, length);
                sum -= num[start];
                start++;
            } else {
                break;
            }
        }
        if (answer > N) {
            answer = 0;
        }
        System.out.println(answer);
    }
}