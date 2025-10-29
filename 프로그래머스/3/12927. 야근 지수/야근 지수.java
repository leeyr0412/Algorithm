import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> que = new PriorityQueue<>(Collections.reverseOrder());
        for (int work : works) {
            que.add(work);
        }

        while (n > 0 && !que.isEmpty()) {
            n--;
            int work = que.poll();
            work--;
            if (work > 0) {
                que.add(work);
            }
        }
        if (!que.isEmpty()) {
            for (int work : que) {
                answer += work * work;
            }
        }
        return answer;
    }
}