import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {
    static long M = 1234567891;
    static long R = 31;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int L = Integer.parseInt(br.readLine());
        String line = br.readLine().trim();
        long answer = 0;
        for(int i =0; i < L; i++){
            int c = line.charAt(i)-'a'+1;
            answer+=c*rModulo(i);
//            answer = answer
            if(answer > M)
                answer=answer%M;
//            rModulo(i);
        }
        System.out.println(answer%M);
    }

    private static long rModulo(int i) {
        if(i == 0){
            return 1;
        } else if (i==1) {
            return R;
        }
        int mid = i/2;
//        return R%M;
        return (rModulo(mid)*rModulo(i-mid))%M;
    }
}