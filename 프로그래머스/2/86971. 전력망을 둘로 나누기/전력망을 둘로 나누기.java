import java.util.*;

class Solution {
    public int solution(int n, int[][] wires) {
        int answer = 100;
        int[][] indegree = new int[n + 1][2];
        Set<Integer>[] graph = new HashSet[n + 1];
        for (int i = 1; i <= n; i++) {
            graph[i] = new HashSet<>();
            indegree[i][1] = 1;
        }
        for (int[] wire : wires) {
            indegree[wire[0]][0]++;
            indegree[wire[1]][0]++;
            graph[wire[0]].add(wire[1]);
            graph[wire[1]].add(wire[0]);
        }

        Queue<Integer> que = new ArrayDeque<>();
        for (int i = 1; i <= n; i++) {
            if (indegree[i][0] == 1) {
                que.add(i);
            }
        }
        boolean[] visited = new boolean[n + 1];
        while (!que.isEmpty()) {
            int nowNode = que.poll();
            visited[nowNode] = true;
            // 연결 노드 찾기
            // int linkNode = graph[nowNode].get(0);
            int linkNode = -1;// = graph[nowNode].get(0);
            for (int node : graph[nowNode]) {
                if (!visited[node]) {
                    linkNode = node;
                    break;
                }
            }
            if(linkNode == -1){
                break;
            }

            graph[nowNode].remove(Integer.valueOf(linkNode));
            graph[linkNode].remove(Integer.valueOf(nowNode));
            answer = Math.min(getAnswer(nowNode, indegree, n), answer);
            if (answer == 0) {
                break;
            }
            indegree[linkNode][1] += indegree[nowNode][1];
            indegree[linkNode][0]--;
            if (indegree[linkNode][0] == 1) {
                que.add(linkNode);
            }
            System.out.println();
        }

        return answer;
    }
    
    public int getAnswer(int nowNode, int[][] indegree, int n){        
        return Math.abs(n - 2 * indegree[nowNode][1]);
    }
}