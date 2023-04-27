import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;
import java.util.StringTokenizer;

/**
 * 문자열폭발 / 골드4 / 시간 / 4월 25일
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();
        String boom = br.readLine();
        int bLen = boom.length();
        int sLen = str.length();

        for (int i = 0; i < sLen; i++) {
            sb.append(str.charAt(i));
            int size = sb.length();
            if (size >= bLen) {
                boolean check = true;
                for (int j = 0; j < bLen; j++) {
                    if(sb.charAt(size-bLen+j)!=boom.charAt(j)){
                        check = false;
                        break;
                    }
                }
                if(check){
                    sb.setLength(size-bLen);
                }
            }
        }
        if(sb.length()==0)
            sb.append("FRULA");
        System.out.println(sb.toString());
    }
}