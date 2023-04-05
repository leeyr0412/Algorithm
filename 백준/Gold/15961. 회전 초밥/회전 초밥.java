import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 회전초밥 / 골드4 / 걸린시간 / 4월 5일
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] arr = new int[N * 2];
        int[] use = new int[d + 1];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            arr[i + N] = arr[i];
        }
        use[c] = 1;
        int start = 0, end = k;
        int count = 1;
        int answer = 0;
        for (int i = 0; i < end; i++) {
            if (use[arr[i]] > 0) {
                use[arr[i]]++;
                continue;
            }
            use[arr[i]]++;
            count++;
        }
        answer = count;
        end++;
        for (start = 1; start < N; start++) {
            if (use[arr[start - 1]] == 1) {
                count--;
            }
            use[arr[start - 1]]--;
            end = start+k-1;
            use[arr[end]]++;
            if(use[arr[end]]==1){
                count++;
            }
            answer = Math.max(count, answer);
        }
        System.out.println(answer);
    }
}