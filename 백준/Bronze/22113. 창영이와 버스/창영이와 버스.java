import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        int n = Integer.parseInt(st.nextToken());
        int [][] arr = new int[n+1][n+1];
        int m = Integer.parseInt(st.nextToken());
        int []bus = new int[m];
        st = new StringTokenizer(br.readLine().trim());
        for(int i = 0; i < m; i++){
            bus[i] = Integer.parseInt(st.nextToken());
        }
        int answer = 0;
        for(int r = 1; r <= n;r++){
        st = new StringTokenizer(br.readLine().trim());
            for(int c = 1; c <=n; c++){
                arr[r][c] = Integer.parseInt(st.nextToken());
            }
        }
        int index = 1;
        while (index<m){
            answer+= arr[bus[index-1]][bus[index]];
            index++;
        }
        System.out.println(answer);
    }
}