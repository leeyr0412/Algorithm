import java.util.*;
class Solution {
    public static int[] supo1 = {1,2,3,4,5};
    public static int[] supo2 = {2, 1, 2, 3, 2, 4, 2, 5};
    public static int[] supo3 = {3, 3, 1, 1, 2, 2, 4, 4, 5, 5};
    public int[] solution(int[] answers) {
        int[] answer = {};
        int count1 = 0;
        int count2 = 0;
        int count3 = 0;
        int total = answers.length;
        int max = 0;
        for(int i = 0; i < total; i++){
            if(answers[i] == supo1[i%5]){
                count1++;
                max = Math.max(max, count1);
            }
            if(answers[i] == supo2[i%8]){
                count2++;
                max = Math.max(max, count2);
            
            }
            if(answers[i] == supo3[i%10]){
                count3++;
                max = Math.max(max, count3);
            
            }
        }
        List<Integer> temp = new ArrayList<>();
        if(max == count1){
           temp.add(1);
       }
        if(max == count2){
           temp.add(2);
       }
        if(max == count3){
           temp.add(3);
       }
    
        answer = new int[temp.size()];
        for(int i = 0; i < temp.size(); i++){
            answer[i] = temp.get(i);
        }
        
        
        return answer;
    }
}