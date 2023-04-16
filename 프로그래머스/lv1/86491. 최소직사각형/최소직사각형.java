class Solution {
    public int solution(int[][] sizes) {
        int answer = 0;
        int hMax = 0;
        int wMax = 0;
        for(int[] card : sizes){
            int a = card[0];
            int b = card[1];
            if(a>=b){
                hMax = Math.max(hMax,a);
                wMax = Math.max(wMax,b);
            }else{
                hMax = Math.max(hMax,b);
                wMax = Math.max(wMax,a);
            }
        }
        answer = hMax * wMax;
        return answer;
    }
}