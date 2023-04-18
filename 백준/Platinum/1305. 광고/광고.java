import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 광고 / 플래4 / 걸린시간 / 4월 18일
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int size = Integer.parseInt(br.readLine());
        String show = br.readLine();

        int j = 0;
        int [] pi = new int[size];
        for(int i = 1; i < size; i++){
            while(j>0&&show.charAt(i)!=show.charAt(j)){
                j=pi[j-1];
            }
            if(show.charAt(i)==show.charAt(j)){
                pi[i] = ++j;
            }
        }
        System.out.println(size-pi[size-1]);
    }
}