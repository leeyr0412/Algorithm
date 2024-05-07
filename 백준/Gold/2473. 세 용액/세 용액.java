import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static long[] answer = {0, 0, 0};
    static long sum = 3000000000L;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);

        for (int i = 0; i < N - 2 && sum != 0; i++) {
            solution(arr, i);
        }
        System.out.println(answer[0] + " " + answer[1] + " " + answer[2]);
    }

    private static void solution(long[] arr, int left) {
        int mid = left + 1;
        int right = arr.length - 1;
        while (mid < right) {
            long temp = arr[left] + arr[mid] + arr[right];
            if (temp == 0) {
                sum = 0;
                answer[0] = arr[left];
                answer[1] = arr[mid];
                answer[2] = arr[right];
                return;
            }
            if (Math.abs(temp) < sum) {
                answer[0] = arr[left];
                answer[1] = arr[mid];
                answer[2] = arr[right];
                sum = Math.abs(temp);
            }
            if (temp < 0) {
                mid++;
            } else {
                right--;
            }
        }
    }
}