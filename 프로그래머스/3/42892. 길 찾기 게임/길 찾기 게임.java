import java.util.*;

class Node implements Comparable<Node> {
    int x;
    int y;
    int num;
    Node left;
    Node right;

    public Node(int x, int y, int num) {
        this.x = x;
        this.y = y;
        this.num = num;
    }

    @Override
    public int compareTo(Node o) {
        if (o.y == this.y) {
            return this.x - o.x;
        }
        return o.y - this.y;
    }
}

class Solution {
    static int[][] answer;
    static int answerIndex;

    public int[][] solution(int[][] nodeinfo) {
        answer = new int[2][nodeinfo.length];
        PriorityQueue<Node> nodeQue = new PriorityQueue<>();
        for (int i = 0; i < nodeinfo.length; i++) {
            nodeQue.add(new Node(nodeinfo[i][0], nodeinfo[i][1], i+1));
        }
        Node root = nodeQue.poll();
        while (!nodeQue.isEmpty()) {
            insertNode(root, nodeQue.poll());
        }
        answerIndex = 0;
        preorder(root);
        answerIndex = 0;
        postorder(root);
        return answer;
    }

    private void preorder(Node now) {
        answer[0][answerIndex++] = now.num;
        if (now.left != null) {
            preorder(now.left);
        }
        if (now.right != null) {
            preorder(now.right);
        }
    }

    private void postorder(Node now) {
        if (now.left != null) {
            postorder(now.left);
        }
        if (now.right != null) {
            postorder(now.right);
        }
        answer[1][answerIndex++] = now.num;
    }

    private void insertNode(Node parent, Node child) {
        if (parent.x > child.x) {
            if (parent.left == null) {
                parent.left = child;
            } else {
                insertNode(parent.left, child);
            }
        } else {
            if (parent.right == null) {
                parent.right = child;
            } else {
                insertNode(parent.right, child);
            }
        }
    }
}