package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
public class BJ_17298_오큰수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr ;

        int n = Integer.parseInt(br.readLine());
        arr = new int[n];
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        for(int i = 0; i < n; i++) {
            arr[i]=Integer.parseInt(st.nextToken());
        }

        int [] ans = new int[n];
        int [] stack = new int[n];
        int size = 0;

        for(int i = 0; i < n; i++) {
            while(size>0 && arr[i]>arr[stack[size-1]]) {
                size--;
                ans[stack[size]] = arr[i];
            }
            stack[size++] =i;
        }


        for(int i = 0; i < size; i++){
            ans[stack[i]] = -1;
        }

        StringBuilder sb = new StringBuilder();
        for(int i : ans) {
            sb.append(i).append(" ");

        }
        System.out.println(sb);
    }
}
