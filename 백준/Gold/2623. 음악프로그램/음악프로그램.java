import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 음악프로그램 / 골드3
 */
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nodeNum = Integer.parseInt(st.nextToken());
        int ruleNum = Integer.parseInt(st.nextToken());

        List<Integer>[] graph = new ArrayList[nodeNum + 1];
        for(int i = 1; i <= nodeNum; i++){
            graph[i] = new ArrayList<>();
        }

        int[] indegree = new int[nodeNum + 1];

        for (int i = 0; i < ruleNum; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int from = Integer.parseInt(st.nextToken());
            for (int j = 1; j < n; j++) {
                int to = Integer.parseInt(st.nextToken());
                graph[from].add(to);
                indegree[to]++;
                from = to;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for (int i = 1; i <= nodeNum; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
                indegree[i]--;
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            int now = queue.poll();
            sb.append(now).append("\n");

            for (Integer i : graph[now]) {
                indegree[i]--;
            }

            for (int i = 1; i <= nodeNum; i++) {
                if (indegree[i] == 0) {
                    queue.add(i);
                    indegree[i]--;
                }
            }
        }
        for (int i = 1; i <= nodeNum; i++) {
            if (indegree[i] > 0) {
                sb = new StringBuilder();
                sb.append("0");
                break;
            }
        }
        System.out.println(sb);
    }
}