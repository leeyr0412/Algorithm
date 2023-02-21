package hw.hw0217;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BJ_3040_백설공주와일곱난쟁이_이예리 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int total = 0;
        int [] arr = new int[9];
        for(int i = 0; i < 9; i++){
            arr[i]= Integer.parseInt(br.readLine());
            total += arr[i];
        }
//        Collections.sort(arr);
        for(int i = 0; i <8; i++){
            for(int j = i+1; j<9; j++){
                int temp = arr[i] + arr[j];
                if(total - temp == 100){
                    arr[i] = 0; arr[j] = 0;
                    for(int index = 0; index < 9; index++){
                        if(arr[index]!=0)
                            System.out.println(arr[index]);
                    }
                    return;
                }
            }
        }
    }
}
