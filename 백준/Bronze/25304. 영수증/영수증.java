import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        long total = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        long sum = 0;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int money = Integer.parseInt(st.nextToken());
            sum += money*(Integer.parseInt(st.nextToken()));

        }
        if(sum==total){
            System.out.println("Yes");
        }else {
            System.out.println("No");
        }

    }
}
