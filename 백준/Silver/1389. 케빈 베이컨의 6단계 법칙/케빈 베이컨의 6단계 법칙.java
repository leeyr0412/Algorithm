import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        boolean[][] graph = new boolean[v + 1][v + 1];
        for (int i = 0; i < e; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = true;
            graph[b][a] = true;
        }
        int min = Integer.MAX_VALUE;
        int answer = 0;
        for (int i = 1; i <= v; i++) {
            int temp = bfs(i, graph, v);
            if(temp < min){
                min = temp;
                answer = i;
            }
        }
        System.out.println(answer);
    }

    private static int bfs(int v, boolean[][] graph, int num) {
        Deque<int[]> que = new ArrayDeque<>();
        boolean[] visited = new boolean[num+1];
        int answer = 0;
        que.offer(new int [] {v,0});
        visited[v] = true;
        while (!que.isEmpty()){
            int[] now = que.poll();
            answer += now[1];
            for(int i = 1; i <=num; i++){
                if(!visited[i]&&graph[now[0]][i]){
                    que.offer(new int[]{i,now[1]+1});
                    visited[i]=true;
                }
            }
        }
        return answer;
    }
}