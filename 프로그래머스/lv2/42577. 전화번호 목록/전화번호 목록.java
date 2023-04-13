class Solution {
    public boolean solution(String[] phone_book) {
        boolean answer = true;
        Node root = new Node(-1, new Node[10],false);
        for (String s : phone_book) {
            Node now = root;
            int len = s.length();
            for(int i = 0; i < len; i++){
                if (now.isEnd()) {
                    answer = false;
                    break;
                }
                int c = s.charAt(i) - '0';
                if(now.getChild()[c]==null){
                    now.getChild()[c] = new Node(c,new Node[10],false);
                }
                now = now.getChild()[c];
            }
            if (now.isEnd()) {
                answer = false;
                break;
            }
            for (Node node : now.getChild()) {
                if(node!=null) {
                    answer = false;
                    break;
                }
            }
            if(answer)
                now.setEnd(true);
        }
        return answer;
    }
}

class Node{
    public Node(int curr, Node[] child, boolean end) {
        this.curr = curr;
        this.child = child;
        this.end = end;
    }

    private int curr;
    private Node[] child ;
    private boolean end;

    public int getCurr() {
        return curr;
    }

    public Node[] getChild() {
        return child;
    }

    public boolean isEnd() {
        return end;
    }

    public void setEnd(boolean end) {
        this.end = end;
    }
}