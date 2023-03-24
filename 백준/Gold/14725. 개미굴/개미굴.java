import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 개미굴 / 골드3 / 걸린시간 / 3월 24일
 */

class Node implements Comparable<Node> {
    private String curr;
    private List<Node> child;

    public Node(String curr, List<Node> child) {
        this.curr = curr;
        this.child = child;
    }

    public String getCurr() {
        return curr;
    }

    public List<Node> getChild() {
        return child;
    }

    @Override
    public int compareTo(Node o) {
        return this.curr.compareTo(o.curr);
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;// = new StringTokenizer(br.readLine());
        StringBuffer sb = new StringBuffer();

        int N = Integer.parseInt(br.readLine());
        Node root = new Node("curr", new ArrayList<Node>());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());   //음식 수
            Node curr = root;                           //현재 음식
            for (int j = 0; j < K; j++) {
                boolean flag = false;
                String now = st.nextToken();
                for (Node node : curr.getChild()) {     //
                    if (node.getCurr().equals(now)) {    //이미 들어간노드
                        curr = node;
                        flag = true;
                        break;
                    }
                }
                //새로 넣을 노드
                if (!flag) {
                    curr.getChild().add(new Node(now, new ArrayList<Node>()));
                    for (Node node : curr.getChild()) {     //
                        if (node.getCurr().equals(now)) {    //이미 들어간노드
                            curr = node;
                            break;
                        }
                    }
                }
            }
        }

        Collections.sort(root.getChild());
        for (Node node : root.getChild()) {
            print(node, 0);
        }
    }

    private static void print(Node node, int cnt) {
        for (int i = 0; i < cnt; i++) {
            System.out.print("--");
        }
        System.out.println(node.getCurr());
        if (node.getChild().size() == 0) {
            return;
        } else {
            Collections.sort(node.getChild());
            for (Node child : node.getChild()) {
                print(child, cnt + 1);
            }
        }
    }

}