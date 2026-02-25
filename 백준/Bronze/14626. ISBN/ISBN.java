import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String isbn = br.readLine();
        int sum = 0;
        int index = 0;
        for (int i = 0; i < 13; i++) {
            char c = isbn.charAt(i);
            if (c == '*') {
                index = i;
            } else {
                if (i % 2 == 0) {
                    sum += (c - '0');
                } else {
                    sum += ((c - '0') * 3);
                }
            }
        }
        sum = sum % 10;
        if(index%2==0){
            System.out.println(10 - sum);
        }else {
            for(int i =0; i <10; i++){
                if((sum+3*i)%10==0){
                    System.out.println(i);
                    return;
                }
            }
        }
    }
}