import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }
        int[] index = new int[N];

        int[] lis = new int[N];
        int top = 0;
        for (int i = 0; i < N; i++) {
            if (top == 0 || lis[top - 1] < A[i]) {
                index[i] = top;
                lis[top++] = A[i];
                continue;
            }
            int idx = binarySearch(lis, top, A[i]);
            index[i] = idx;
            lis[idx] = A[i];
        }
        System.out.println(top);
        int[] answer = new int[top];
        top--;
        for (int i = N - 1; i >= 0 && top >= 0; i--) {
            if (index[i] == top) {
                answer[top--] = A[i];
            }
        }
        for (int num : answer) {
            System.out.print(num + " ");
        }
    }

    private static int binarySearch(int[] lis, int top, int num) {
        int start = 0;
        int mid = 0;
        int end = top;
        while (start <= end) {
            mid = (start + end) / 2;
            if (lis[mid] == num) {
                return mid;
            } else if (lis[mid] < num) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        if (lis[mid] < num) {
            return mid + 1;
        } else {
            return mid;
        }
    }
}