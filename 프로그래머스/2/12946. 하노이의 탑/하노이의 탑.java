import java.util.*;

class Solution {
    static List<int[]> list;
    public int[][] solution(int n) {
        
        list = new ArrayList<>();
        hanoi(n,1,2,3);
        int[][] answer = new int[list.size()][2];
        for(int i = 0; i < list.size(); i++){
            answer[i] = list.get(i);
        }
        return answer;
    }
    
    public void hanoi(int n, int from, int use, int to){
        if(n == 1){
            list.add(new int[]{from, to});
        }else{
            hanoi(n-1, from, to, use);
            hanoi(1, from, use, to);
            hanoi(n-1, use, from, to);
        }
    }
}