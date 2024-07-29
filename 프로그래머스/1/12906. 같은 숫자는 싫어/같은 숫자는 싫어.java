import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int[] answer = {};
        
        List<Integer> list = new ArrayList<>();
        int top = -1;
        for(int num : arr){
            if(top >= 0){
                if(list.get(top)==num){
                    continue;
                }
            }
            list.add(num);
            top++;
        }
        top++;
        answer = new int[top];
        for(int i = 0; i < top; i++){
            answer[i] = list.get(i);
        }

        return answer;
    }
}