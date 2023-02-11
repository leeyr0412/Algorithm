package baekjoon;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;

public class BJ_2309_일곱난쟁이 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int total = 0;
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 0; i < 9; i++){
            arr.add(Integer.parseInt(br.readLine()));
            total += arr.get(i);
        }
        Collections.sort(arr);
        for(int i = 0; i <8; i++){
            for(int j = i+1; j<9; j++){
                int temp = arr.get(i) + arr.get(j);
                if(total - temp == 100){
                    arr.remove(j); arr.remove(i);
                    for(int index = 0; index < arr.size(); index++){
                        System.out.println(arr.get(index));
                    }
                    return;
                }
            }
        }
    }
}
