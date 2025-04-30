import java.util.*;

class Solution {
    static List<Integer>[] tree;
    static boolean[] visited;

    public int solution(int n, int[][] wires) {
        int answer = 101;
        tree = new List[n + 1];
        for (int i = 1; i <= n; i++) {
            tree[i] = new ArrayList<>();
        }
        for (int[] wire : wires) {
            tree[wire[0]].add(wire[1]);
            tree[wire[1]].add(wire[0]);
        }
        visited = new boolean[n + 1];
        for (int[] wire : wires) {
            Arrays.fill(visited, false);
            visited[wire[0]] = true;
            visited[wire[1]] = true;
            answer = Math.min(Math.abs(dfs(wire[0]) * 2 - n), answer);
            if (answer == 0) {
                break;
            }
        }
        return answer;
    }

    private int dfs(int now) {
        int count = 1;
        for (Integer next : tree[now]) {
            if (visited[next]) {
                continue;
            }
            visited[next] = true;
            count += dfs(next);
        }
        return count;
    }
}