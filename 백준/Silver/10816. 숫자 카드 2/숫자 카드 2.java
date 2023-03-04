import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int[] plus = new int [10000001];
        int[] minus = new int [10000001];
        int N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N;n++){
            int temp = Integer.parseInt(st.nextToken());
            if(temp <0){
                minus[-temp]++;
            }else {
                plus[temp]++;
            }
        }
        N = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int n = 0; n < N; n++){
            int temp = Integer.parseInt(st.nextToken());
            if(temp <0){
                sb.append(minus[-temp]).append(" ");
            }else {
                sb.append(plus[temp]).append(" ");
            }
        }
        System.out.println(sb);
    }
}