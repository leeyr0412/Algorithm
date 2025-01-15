import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int start = 0;
        int sum = 0;
        int answer = N + 1;

        // end 포인터가 배열의 끝까지 이동
        for (int end = 0; end < N; end++) {
            sum += arr[end];
            while (sum >= S) {
                answer = Math.min(answer, end - start + 1);
                sum -= arr[start];
                start++;
            }
        }
        if (answer == N + 1) {
            System.out.println(0);
        } else {
            System.out.println(answer);
        }
    }
}