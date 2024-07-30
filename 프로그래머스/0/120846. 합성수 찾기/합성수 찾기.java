class Solution {
    public int solution(int n) {
        int answer = 0;
        boolean[] memo = new boolean[n+1];
        for(int i = 2; i <=n; i++){
            if(memo[i] == false){
                for(int j = 2; j * i <= n; j++){
                    memo[i*j] = true;
                }
            }
        }
        for(int i = 4; i <=n; i++){
            if(memo[i] == true){
                answer++;
            }
        }
        return answer;
    }
}