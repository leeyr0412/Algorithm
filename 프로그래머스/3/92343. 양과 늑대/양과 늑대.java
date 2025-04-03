import java.util.*;

class State {
    private int sheep;
    private int wolf;
    private List<Integer> nextNodes;

    public State(int sheep, int wolf, List<Integer> nextNodes) {
        this.sheep = sheep;
        this.wolf = wolf;
        this.nextNodes = nextNodes;
    }

    public int getSheep() {
        return sheep;
    }

    public int getWolf() {
        return wolf;
    }

    public List<Integer> getNextNodes() {
        return nextNodes;
    }
}

class Solution {
    public int solution(int[] info, int[][] edges) {
        int answer = 1;

        List<Integer>[] graph = new List[info.length];
        for (int i = 0; i < info.length; i++) {
            graph[i] = new ArrayList<>();
        }
        for (int[] edge : edges) {
            graph[edge[0]].add(edge[1]);
        }

        Set<String> visited = new HashSet<>();
        Deque<State> que = new ArrayDeque<>();
        que.add(new State(1, 0, new ArrayList<>(graph[0])));
        visited.add(generateKey(1, 0, new ArrayList<>(graph[0])));
        while (!que.isEmpty()) {
            State currentState = que.poll();
            for (int i = 0; i < currentState.getNextNodes().size(); i++) {
                int now = currentState.getNextNodes().get(i);

                int nextSheep = currentState.getSheep();
                int nextWolf = currentState.getWolf();

                if (info[now] == 0) {
                    nextSheep++;
                } else {
                    nextWolf++;
                }
                if (nextSheep <= nextWolf) {
                    continue;
                }
                answer = Math.max(answer, nextSheep);
                List<Integer> newNextNodes = new ArrayList<>(currentState.getNextNodes());
                newNextNodes.remove(Integer.valueOf(now));
                newNextNodes.addAll(graph[now]);
                String visitKey = generateKey(nextSheep, nextWolf, newNextNodes);
                if (!visited.contains(visitKey)) {
                    que.add(new State(nextSheep, nextWolf, newNextNodes));
                    visited.add(visitKey);
                }
            }
        }
        return answer;
    }

    private String generateKey(int nextSheep, int nextWolf, List<Integer> newNextNodes) {
        Collections.sort(newNextNodes);
        return nextSheep + ", " + nextWolf + ", " + newNextNodes.toString();
    }
}