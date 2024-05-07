import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int[] memo;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringBuilder sb = new StringBuilder();
        StringTokenizer st;// = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N];
        int[] arr = new int[N];
        int[] answer = new int[N];
        memo = new int[N];
        int top = -1;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int key = Integer.parseInt(st.nextToken());
            arr[i] = key;
            if (top < 0 || dp[top] < key) {
                dp[++top] = key;
                memo[i] = top;
                continue;
            }
            int index = binarySearch(dp, top, key);
            dp[index] = key;
            memo[i] = index;
        }
        System.out.println(top + 1);
        int t = 0;
        for (int i = N - 1; i >= 0; i--) {
            if (memo[i] == top) {
                answer[t++] = arr[i];
                top--;
            }
        }
        for (int i = t - 1; i >= 0; i--) {
            System.out.print(answer[i] + " ");
        }
    }

    private static int binarySearch(int[] dp, int top, int key) {
        int mid = 0;
        int start = 0;
        while (start <= top) {
            mid = (start + top) >> 1;
            if (dp[mid] == key) {
                return mid;
            } else if (dp[mid] > key) {
                top = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        if (dp[mid] > key) {
            return mid;
        } else {
            return mid + 1;
        }
    }
}