import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 멍멍이 쓰다듬기 / 골드5 / 걸린시간 / 3월 30일
 */

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int k = b - a;

        int temp = (int) Math.sqrt(k);

        if(k==0){
            System.out.println(0);
        } else if (k<4) {
            System.out.println(1);
        } else if(temp*temp == k){
            System.out.println(temp*2-1);
        }else {
            if(temp*temp+temp<k){
                System.out.println((temp+1)*2-1);
            }else{
                System.out.println(temp*2);
            }
        }
//
//
//        if(k >= temp*temp+temp){
//            System.out.println(temp*2+1);
//        }else{
//            System.out.println(temp*2+1);
//        }
    }
}