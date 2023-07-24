import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        //StringBuilder sb = new StringBuilder();
        StringTokenizer st;// = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(br.readLine());
        int[] nums = new int[N];
        for (int i = 0; i < N; i++) {
            nums[i] = Integer.parseInt(br.readLine());
        }

        int[] list = new int[N];
        int top = 0;

        list[0] = nums[0];
        for (int i = 1; i < N; i++) {
            if (list[top] < nums[i]) {
                top++;
                list[top] = nums[i];
            } else {
                int index = binarySearch(list, nums[i], top);
                list[index] = nums[i];
            }
        }
        int answer = N - top - 1;
        System.out.println(answer);

    }

    private static int binarySearch(int[] list, int key, int top) {
        int start = 0;
        int end = top;
        int mid = 0;
        while (start <= end) {
            mid = (start + end) >> 1;
            if (list[mid] == key) {
                return mid;
            } else if (list[mid] < key) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }
        if (list[mid] > key) {
            return mid;
        } else {
            return mid + 1;
        }
    }
}