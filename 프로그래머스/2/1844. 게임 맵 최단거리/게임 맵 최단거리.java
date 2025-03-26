import java.util.*;

class Solution {
    public int solution(int[][] maps) {
        int[][] dr = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
        int goalR = maps.length;
        int goalC = maps[0].length;
        boolean[][] visited = new boolean[goalR][goalC];
        goalR--;
        goalC--;

        Deque<Node> que = new ArrayDeque<>();
        que.add(new Node(0, 0, 1));
        visited[0][0] = true;

        while (!que.isEmpty()) {
            Node now = que.pop();
            if (now.getR() == goalR && now.getC() == goalC) {
                return now.getCount();
            }
            for (int[] d : dr) {
                int nextR = now.getR() + d[0];
                int nextC = now.getC() + d[1];
                if (nextR >= 0 && nextR <= goalR && nextC >= 0 && nextC <= goalC) {
                    if (maps[nextR][nextC] == 0 || visited[nextR][nextC]) {
                        continue;
                    }
                    visited[nextR][nextC] = true;
                    que.add(new Node(nextR, nextC, now.getCount() + 1));
                }
            }
        }
        return -1;
    }
}

class Node {
    private int r;
    private int c;
    private int count;

    public Node(int r, int c, int count) {
        this.r = r;
        this.c = c;
        this.count = count;
    }

    public int getR() {
        return r;
    }

    public int getC() {
        return c;
    }

    public int getCount() {
        return count;
    }
}