import java.util.*;

class Node {
    private String word;
    private int count;

    public Node(String word, int count) {
        this.word = word;
        this.count = count;
    }

    public String getWord() {
        return word;
    }

    public int getCount() {
        return count;
    }
}

class Solution {
    static Set<String> visited;

    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        visited = new HashSet<>();
        visited.addAll(Arrays.asList(words));
        if (!visited.contains(target)) {
            return answer;
            //return 0;
        }
        answer = bfs(begin, target);
        return answer;
    }

    private int bfs(String begin, String target) {
        if (!visited.contains(target)) {
            //return answer;
            return 0;
        }
        Deque<Node> q = new ArrayDeque<>();
        q.add(new Node(begin, 0));
        while (!q.isEmpty()) {
            Node now = q.pop();
            if (now.getWord().equals(target)) {
                return now.getCount();
            }
            List<String> nextList = getNextList(now.getWord());
            for (String word : nextList) {
                q.add(new Node(word, now.getCount() + 1));
                visited.remove(word);
            }
        }
        return 0;
    }

    private List<String> getNextList(String now) {
        List<String> nextList = new ArrayList<>();
        for (String next : visited) {
            int diffCount = 0;
            for (int i = 0; i < now.length(); i++) {
                if (now.charAt(i) != next.charAt(i)) {
                    diffCount++;
                }
                if (diffCount > 1) {
                    continue;
                }
            }
            if (diffCount == 1) {
                nextList.add(next);
            }
        }
        return nextList;
    }
}