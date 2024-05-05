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

        int[] lis = new int[N];
        int top = 0;
        for (int num : A) {
            if (top == 0 || lis[top - 1] < num) {
                lis[top++] = num;
                continue;
            }
            lis[binarySearch(lis, top, num)] = num;
        }
        System.out.println(top);
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