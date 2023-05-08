import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 이모티콘 / 골드1 / - / 4월 28일
 */
class Node {
    private int count;
    private int time;
    private int copy;

    public Node(int count, int time, int copy) {
        this.count = count;
        this.time = time;
        this.copy = copy;
    }

    public int getCount() {
        return count;
    }

    public int getTime() {
        return time;
    }

    public int getCopy() {
        return copy;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Deque<Node> queue = new ArrayDeque<>();

        queue.offer(new Node(1, 0, 0));

        int answer = 0;
        boolean[][] visited = new boolean[10000][10000];
        visited[1][0] = true;
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            int count = now.getCount();
            int time = now.getTime();
            int copy = now.getCopy();
            if (count == N) {
                answer = time;
                break;
            }
            time++;
            //copy
            if (!visited[count][count]) {
                queue.offer(new Node(count, time, count));
                visited[count][count] = true;
            }

            //붙여넣기
            if (!visited[count + copy][copy]) {
                if (copy > 0) {
                    queue.offer(new Node(count + copy, time, copy));
                    visited[count + copy][copy] = true;
                }
            }

            //빼기
            if (!visited[count - 1][copy]) {
                if (count > 1) {
                    queue.offer(new Node(count - 1, time, copy));
                }
                visited[count - 1][copy] = true;
            }
        }
        System.out.println(answer);
    }
}