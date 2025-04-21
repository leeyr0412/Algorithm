import java.util.*;

class Solution {
    public int solution(int n, int[][] computers) {
        int answer = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }
            visited[i] = true;
            Deque<Integer> que = new ArrayDeque<>();
            que.add(i);
            answer++;
            while (!que.isEmpty()) {
                int now = que.poll();
                for (int link = 0; link < n; link++) {
                    if (computers[now][link] == 0) {
                        continue;
                    }
                    if (visited[link]) {
                        continue;
                    }
                    visited[link] = true;
                    que.add(link);
                }
            }
        }
        return answer;
    }
}