import java.util.*;

class Node implements Comparable<Node> {
    int index;
    int num;

    public Node(int index, int num) {
        this.index = index;
        this.num = num;
    }

    @Override
    public int compareTo(Node o) {
        return this.num - o.num;
    }
}

class Solution {
    public int solution(int[] a) {
        int answer = 0;
        PriorityQueue<Node> queue = new PriorityQueue<>();
        for (int i = 0; i < a.length; i++) {
            queue.add(new Node(i, a[i]));
        }

        int left = a.length;
        int right = -1;
        while (!queue.isEmpty()) {
            Node node = queue.poll();
            if (node.index > left && node.index < right) {
                left = Math.min(node.index, left);
                right = Math.max(node.index, right);
                continue;
            }
            left = Math.min(node.index, left);
            right = Math.max(node.index, right);
            answer++;
        }

        return answer;
    }
}