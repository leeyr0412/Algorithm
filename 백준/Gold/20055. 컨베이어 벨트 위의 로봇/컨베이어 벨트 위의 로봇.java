import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int [] A = new int[N*2];
        boolean[] robot = new boolean[2*N];

        int count = 0;
        int result = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 2 * N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        while(count<K){
            //회전
            int temp = A[2*N-1];
            for(int i = 2*N-2; i >=0; i--){
                A[i+1] = A[i];
                robot[i+1] = robot[i];
            }
            A[0] = temp;
            robot[0] = false;
            
            //로봇 이동
            robot[N-1]= false;
            for(int i = N-1; i > 0;i--){
                if(robot[i-1]&&!robot[i]&&A[i]!=0){
                    robot[i] = true;
                    robot[i-1] = false;
                    A[i]--;
                    if(A[i]<=0)
                        count++;
                }
            }
            if(A[0]!=0){
                robot[0] = true;
                A[0]--;
                if(A[0]<=0)
                    count++;
            }
            result++;
        }
        System.out.println(result);
    }
}