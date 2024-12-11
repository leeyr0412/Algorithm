import java.util.*;

class Ladder implements Comparable<Ladder> {
    int size;
    int[] pos;

    public Ladder(int size, int[] pos) {
        this.size = size;
        this.pos = pos;
    }

    @Override
    public int compareTo(Ladder o) {
        return this.size - o.size;
    }
}

class Solution {
    static int[][] dirs = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int solution(int[][] land, int height) {
        int answer = 0;
        boolean[][] visited = new boolean[land.length][land.length];
        int remainCount = land.length * land.length - 1;    // 방문하지 않은 좌표의 수

        visited[0][0] = true;
        // BFS 탐색을 위한 큐
        Queue<int[]> findQueue = new ArrayDeque<>();
        // 사다리 큐(사다리 짧은 순으로 정렬)
        PriorityQueue<Ladder> ladderQueue = new PriorityQueue<>();
        findQueue.add(new int[]{0, 0});
        while (remainCount != 0) {
            // BFS 탐색으로 방문한 좌표 수만큼 남은 좌표수가 빠짐.
            remainCount = bfs(land, height, visited, remainCount, findQueue, ladderQueue);
            // 방문하지 않은 남은 좌표가 있다면
            if (remainCount > 0) {
                while (true) {
                    // 사다리 탐색
                    Ladder ladder = ladderQueue.poll();
                    if (visited[ladder.pos[0]][ladder.pos[1]]) {    // 사다리 놓을 위치에 방문함
                        continue;
                    } else {// 사다리 놓기
                        visited[ladder.pos[0]][ladder.pos[1]] = true;
                        findQueue.add(new int[]{ladder.pos[0], ladder.pos[1]});
                        answer += ladder.size;
                        remainCount--;
                        break;
                    }
                }
            }
        }
        return answer;
    }

    private int bfs(int[][] land, int height, boolean[][] visited, int remainCount, Queue<int[]> findQueue, PriorityQueue<Ladder> ladderQueue) {
        while (!findQueue.isEmpty()) {
            int[] now = findQueue.poll();
            for (int[] dir : dirs) {
                int r = now[0] + dir[0];
                int c = now[1] + dir[1];
                if (isInvalid(r, c, land.length) || visited[r][c]) {
                    continue;
                }
                int diff = Math.abs(land[now[0]][now[1]] - land[r][c]);
                if (diff <= height) {
                    findQueue.add(new int[]{r, c});
                    visited[r][c] = true;
                    remainCount--;
                } else {    // 사다리가 필요할 경우 사다리 큐에 사다리 크기와 방문하지 못한 좌표 넣음
                    ladderQueue.add(new Ladder(diff, new int[]{r, c}));
                }
            }
        }
        return remainCount;
    }

    private boolean isInvalid(int r, int c, int length) {
        return r < 0 || c < 0 || r >= length || c >= length;
    }
}