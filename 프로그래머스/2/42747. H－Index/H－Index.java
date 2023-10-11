import java.util.*;

class Solution {
    public int solution(int[] citations) {
        int answer = 0;
        
        Arrays.sort(citations);
        
        int length = citations.length;
        
        int h = length;
        while(h != 0){
            if(h <= citations[length-h]){
                if(h <= citations[length-h]){
                    if(length-h+1 <=h){
                        break;
                    }
                }
            }
            h--;
        }
        return h;
    }
}