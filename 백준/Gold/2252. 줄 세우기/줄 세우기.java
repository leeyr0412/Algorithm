import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
* 줄세우기 / 난이도 / 걸린시간 / 3월21일
*/
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int v = Integer.parseInt(st.nextToken());
        int e = Integer.parseInt(st.nextToken());
        int [] indegree = new int[v+1];
        List<Integer>[]graph = new List[v+1];
        for(int i = 1; i <= v; i++){
            graph[i] = new ArrayList<Integer>();
        }
        for(int i = 0; i < e; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            graph[from].add(to);
            indegree[to]++;
        }
        StringBuilder sb = new StringBuilder();
        Queue<Integer> que = new ArrayDeque<>();
        for (int i =1; i <=v;i++ ) {
            //진입 차수 = 0
            if(indegree[i]==0){
                que.add(i);
            }
        }
        while (!que.isEmpty()){
            int now = que.poll();
            sb.append(now).append(" ");
            for (Integer to : graph[now]) {
                indegree[to]--;
                if(indegree[to]==0){
                    que.add(to);
                }
            }
        }
        System.out.println(sb);
    }
}