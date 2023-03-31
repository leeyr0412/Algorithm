import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 용액 / 골드5 / 걸린시간 / 3월 31일
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;//= new StringTokenizer(br.readLine());

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        int start = 0;
        int end = n - 1;
        int answer = Integer.MAX_VALUE;
        int answerS = start, answerE = end;
        while (start < end) {
            int temp = arr[start] + arr[end];
            if(Math.abs(temp)< answer){
                answer = Math.abs(temp);
                answerS = start; answerE = end;
            }
            if (temp == 0) {
                answerS = start;
                answerE = end;
                break;
            } else if (temp>0) {
                end--;
            }else{
                start++;
            }
        }
        System.out.println(arr[answerS] + " " + arr[answerE]);
    }
}