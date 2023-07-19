import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] indegree = new int[N + 1];    // 정점 기준 진입 간선의 수
        List<Integer>[] graph = new ArrayList[N + 1];   // 연결리스트 정보

        // 초기화
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());

            graph[from].add(to);
            indegree[to]++;
        }

        //종료 조건
        int visited = 0;
        while (visited < N) { // 모든 정점 방문 시 종료
            // 각 노드 중 진입차수 0 인 정점을 모두 방문함
            for (int curr = 1; curr <= N; curr++) {
                if (indegree[curr] == 0) {
                    visited++;
                    indegree[curr]--;   // 방문표시 음수로 표현
                    for (Integer to : graph[curr]) {
                        indegree[to]--; //연관된 정점 진입차수 빼기
                    }
                    sb.append(curr).append(" ");
                    break; // 쉬운 문제부터 풀어야함
                }
            }
        }
        System.out.println(sb);
    }
}