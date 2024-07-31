class Solution {
    public int solution(int left, int right) {
        int answer = 0;
        int[] memo = new int[right+1];
        for(int i = 1; i <= right; i++){
            for(int j = 1; j * i <= right; j++){
                memo[i*j]++;
            }
            if(i >= left){
                if(memo[i]%2==0){
                    answer += i;
                }
                else{
                    answer -= i;
                }
            }
        }
        return answer;
    }
}