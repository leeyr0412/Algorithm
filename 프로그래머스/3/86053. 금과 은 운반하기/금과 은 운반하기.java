import java.util.*;

class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long maxTime = ((long)10e9 * 2) * ((long)10e5 * 2) + (long)10e5;
        long minTime = 1;
        long mid;
        long answer = maxTime;
        
        while(minTime <= maxTime){
            mid = (maxTime + minTime) / 2;
            if(check(a,b,g,s,w,t,mid)){
                maxTime = mid - 1;
                answer = Math.min(answer, mid);
            }else{
                minTime = mid+1;
            }
        }
        return answer;
    }
    
    public boolean check(int a, int b, int[] g, int[] s, int[] w, int[] t, long totalTime) {
        long sumSilver = 0;
        long sumGold = 0;
        long total = 0;
        for(int i = 0; i < g.length; i++){
            if(t[i] > totalTime){
                continue;
            }
            long round = (totalTime - t[i]) / (t[i] *2) + 1; //왕복 횟수 + 처음 옮긴거
            long carrySum = round * w[i];
            sumSilver += Math.min(carrySum, s[i]);
            sumGold += Math.min(carrySum, g[i]);
            total += Math.min(carrySum, s[i]+g[i]);
        }
        if(sumSilver >= b && sumGold >= a && total >= a+b){
            return true;
        }
        return false;
    }
}