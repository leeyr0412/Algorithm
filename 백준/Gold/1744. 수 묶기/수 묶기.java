import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        List<Integer> positive = new ArrayList<>();
        List<Integer> negative = new ArrayList<>();
        List<Integer> zero = new ArrayList<>();

        for(int i = 0; i < N; i++){
            int t = Integer.parseInt(br.readLine());
            if(t>0)
                positive.add(t);
            else if (t == 0) {
                zero.add(t);
            }else {
                negative.add(t);
            }
        }
        Collections.sort(negative);

        int result = 0;
        while(negative.size()!=0){
            if(negative.size()==1){
                if(zero.size()!=0){
                    negative.remove(0);
                    zero.remove(0);
                }else {
                    result += negative.get(0);
                    negative.remove(0);
                }
                break;
            }
            result+= negative.get(0)* negative.get(1);
            negative.remove(0);
            negative.remove(0);
        }
        Collections.sort(positive,Collections.reverseOrder());
        while (positive.size()>1){
            if(positive.get(0)==1||positive.get(1)==1){
                result+=positive.get(0);
                positive.remove(0);
                result+=positive.get(0);
                positive.remove(0);
                continue;
            }
            result += positive.get(0)*positive.get(1);
            positive.remove(0);
            positive.remove(0);
        }
        if(positive.size()==1)
            result += positive.get(0);
        System.out.println(result);
    }
}