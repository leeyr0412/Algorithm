import java.util.*;

class Solution {
    public int[] solution(int n, int s) {
        if (n > s) {
            return new int[]{-1};
        }
        int r = s % n;
        int[] answer = new int[n];
        Arrays.fill(answer, s / n);
        for (int i = n - r; i < n; i++) {
            answer[i]++;
        }
        return answer;
    }
}