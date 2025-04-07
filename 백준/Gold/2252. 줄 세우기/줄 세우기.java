import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new List[N + 1];
        int[] inDegree = new int[N + 1];    // 진입 차수
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int front = Integer.parseInt(st.nextToken());
            int back = Integer.parseInt(st.nextToken());
            inDegree[back]++;
            if (graph[front] == null) {
                graph[front] = new ArrayList<>();
            }
            graph[front].add(back);
        }

        Queue<Integer> que = new ArrayDeque<>();
        for (int i = 1; i <= N; i++) {
            if (inDegree[i] == 0) {
                que.add(i);
            }
        }

        while (!que.isEmpty()) {
            int now = que.poll();
            sb.append(now).append(" ");

            if (graph[now] == null) {
                continue;
            }
            for (Integer next : graph[now]) {
                inDegree[next]--;
                if (inDegree[next] == 0) {
                    que.add(next);
                }
            }
        }

        System.out.println(sb.toString());
    }
}