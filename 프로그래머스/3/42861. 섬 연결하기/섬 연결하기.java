import java.util.*;

class Bridge implements Comparable<Bridge> {
    private int from;
    private int to;
    private int cost;

    public Bridge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }

    public int getFrom() {
        return from;
    }

    public int getTo() {
        return to;
    }

    public int getCost() {
        return cost;
    }

    @Override
    public int compareTo(Bridge o) {
        return this.cost - o.cost;
    }
}

class Solution {
    static int[] parent;

    public int solution(int n, int[][] costs) {
        int answer = 0;
        PriorityQueue<Bridge> que = new PriorityQueue<>();
        for (int[] cost : costs) {
            // 다리 오름차순 정렬
            que.add(new Bridge(cost[0], cost[1], cost[2]));
        }
        parent = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
        }

        int edgeCount = 0;
        while (edgeCount != n - 1) {
            Bridge bridge = que.poll();
            int root1 = find(bridge.getTo());
            int root2 = find(bridge.getFrom());
            if (root1 == root2) {
                // 두 노드의 루트노드가 같음 => 이미 연결 되어 있음
                continue;
            }
            // 두 노드의 루트가 다름
            // 다리 연결
            answer += bridge.getCost();
            if (root1 < root2) {
                parent[root2] = root1;
            } else {
                parent[root1] = root2;
            }   
            edgeCount++;
        }
        return answer;
    }

    /**
     * 지금 노드의 루트 노드 찾기
     *
     * @param node 지금 노드
     * @return 루트 노드
     */
    private int find(int node) {
        if (parent[node] != node) { //루트 노트가 존재함
            parent[node] = find(parent[node]);
        }
        return parent[node];
    }
}