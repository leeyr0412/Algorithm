import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int answer = 0;
        Arrays.sort(routes, (a, b) -> Integer.compare(a[1], b[1]));
        int cctv = -30001;
        for (int[] route : routes) {
            if (route[0] <= cctv) {
                continue;
            } else {
                cctv = route[1];
                answer++;
            }
        }
        return answer;
    }
}