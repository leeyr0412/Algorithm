class Solution {
    public static int TG;
    public static int SIZE;
    public static int answer;
    
    public int solution(int[] numbers, int target) {
        answer = 0;
        TG = target;
        SIZE = numbers.length;
        
        dfs(0,numbers, 0);
        
        return answer;
    }
    
    public void dfs(int depth,int[] numbers,int curr){
        if(depth == SIZE){
            if(curr == TG){
                answer++;
                return ;
            }else{
                return ;
            }
        }
        
        dfs(depth+1, numbers, curr+numbers[depth]);
        dfs(depth+1, numbers, curr-numbers[depth]);
        
    }
}