import java.util.*;

class Ladder implements Comparable<Ladder> {
    int cost;
    int[] pos;

    public Ladder(int cost, int[] pos) {
        this.cost = cost;
        this.pos = pos;
    }

    @Override
    public int compareTo(Ladder o) {
        return this.cost - o.cost;
    }
}

class Solution {
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int solution(int[][] land, int height) {
        int answer = 0;
        boolean[][] visited = new boolean[land.length][land.length];
        int remainCount = land.length * land.length;    // 방문하지 않은 좌표의 수

        PriorityQueue<Ladder> queue = new PriorityQueue<>();
        queue.add(new Ladder(0, new int[]{0, 0}));
        while (!queue.isEmpty()) {
            Ladder now = queue.poll();
            if (visited[now.pos[0]][now.pos[1]]) {    // 방문 한 지점이면 넘어감
                continue;
            }
            visited[now.pos[0]][now.pos[1]] = true;
            remainCount--;
            answer += now.cost; // 사다리 가치 더함.(사다리 필요 없으면 0)
            if (remainCount == 0) {
                break;
            }
            for (int[] dir : dirs) {
                int r = now.pos[0] + dir[0];
                int c = now.pos[1] + dir[1];
                if (isInvalid(r, c, land.length) || visited[r][c]) {
                    continue;
                }
                int diff = Math.abs(land[now.pos[0]][now.pos[1]] - land[r][c]);
                if (diff <= height) {   // 사다리 길이보다 짧으면 사다리 비용 0
                    queue.add(new Ladder(0, new int[]{r, c}));
                } else {
                    queue.add(new Ladder(diff, new int[]{r, c}));
                }
            }
        }
        return answer;
    }

    private boolean isInvalid(int r, int c, int length) {
        return r < 0 || c < 0 || r >= length || c >= length;
    }
}